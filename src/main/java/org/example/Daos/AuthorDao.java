package org.example.Daos;

import org.example.Entities.Author;
import org.example.config.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AuthorDao extends GenericDao<Author,Long> {
    private final Session session;
    private Transaction transaction;

    public AuthorDao(Session session){
        super(session,Author.class);
        this.session = session;
    }
    public Author save (Author author){
        this.transaction = session.beginTransaction();
        try{
           Author savedAuthor = session.merge(author);
           transaction.commit();
           return savedAuthor;
        }catch (Exception e){
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    protected Author findById(Long aLong) {
        return super.findById(aLong);
    }

    public List<Author> findByFirstName(String firstname) {
        String query = "select a from Author a where a.firstName like : firstname ";
        Query <Author> findByFirstNameQuery = session.createQuery(query, Author.class);
        findByFirstNameQuery.setParameter("firstname",firstname);
        return findByFirstNameQuery.getResultList();
    }
    public List<Author> findByLastName(String lastname) {
        String query = "select a from Author a where a.lastName like : lastname ";
        Query <Author> findBylastNameQuery = session.createQuery(query, Author.class);
        findBylastNameQuery.setParameter("lastname",lastname);
        return findBylastNameQuery.getResultList();
    }
    public List<Author> AuthorBio(String authorBio) {
        String query = "select a from Author a where a.authorBio like : authorBio ";
        Query<Author> findByAuthorBioQuery = session.createQuery(query, Author.class);
        findByAuthorBioQuery.setParameter("authorBio", authorBio);
        return findByAuthorBioQuery.getResultList();
    }


    public void delete(Long aLong) {
       this.transaction = session.beginTransaction();
       try{
           Author author = this.findById(aLong);
           session.remove(author);
           transaction.commit();
       }catch (RuntimeException e){
           transaction.rollback();
           throw new RuntimeException(e);
       }
    }
    public Author update(Author author) {
        this.transaction = session.beginTransaction();
        try {
            Author updatedAuthor = session.merge(author);
            transaction.commit();
            return updatedAuthor;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

}



