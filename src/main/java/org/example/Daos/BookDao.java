package org.example.Daos;

import org.example.Entities.Author;
import org.example.Entities.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public class BookDao extends GenericDao<Book,Long> {

    private Session session;
    private Transaction transaction;

    public BookDao(Session session) {
        super(session, Book.class);
        this.session = session;

    }

    public Book save(Book book) {
        this.transaction = session.beginTransaction();
        try {
            Book savedbook = session.merge(book);
            transaction.commit();
            return savedbook;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    protected Book findById(Long aLong) {
        return super.findById(aLong);
    }

    public List<Book> findByBookId(Long bookId) {
        String query = "select a from Book a where a.bookId like : bookId ";
        Query<Book> findByBookIdQuery = session.createQuery(query, Book.class);
        findByBookIdQuery.setParameter("bookId", bookId);
        return findByBookIdQuery.getResultList();
    }

    public List<Book> findByBookTitle(String bookTitle) {
        String query = "select a from Book a where a.bookTitle like : bookTitle";
        Query<Book> findByBookTitleQuery = session.createQuery(query, Book.class);
        findByBookTitleQuery.setParameter("bookTitle", bookTitle);
        return findByBookTitleQuery.getResultList();
    }

    public List<Book> findByAuthorId(Author author) {
        String query = "select a from Book a where a.authorId like : authorid";
        Query<Book> findByAuthorIdQuery = session.createQuery(query, Book.class);
        findByAuthorIdQuery.setParameter("authorId", author);
        return findByAuthorIdQuery.getResultList();
    }

    public List<Book> findByPublishedData(String publishedData) {
        String query = "select a from Book a where a.publishedData like : publishedData";
        Query<Book> findBypublishedDataQuery = session.createQuery(query, Book.class);
        findBypublishedDataQuery.setParameter("publishedData", publishedData);
        return findBypublishedDataQuery.getResultList();
    }

    public List<Book> findByQuantity(BigInteger quantity) {
        String query = "select a from Book a where a.quantity like : quantity";
        Query<Book> findByquantityQuery = session.createQuery(query, Book.class);
        findByquantityQuery.setParameter("quantity", quantity);
        return findByquantityQuery.getResultList();
    }

    public List<Book> findByGenre(String genre) {
        String query = "select a from Book a where a.genre like : quantity";
        Query<Book> findBygenreQuery = session.createQuery(query, Book.class);
        findBygenreQuery.setParameter("genre", genre);
        return findBygenreQuery.getResultList();
    }

    public void delete(Long aLong) {
        this.transaction = session.beginTransaction();
        try {
            Book book = this.findById(aLong);
            session.remove(book);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }
}
