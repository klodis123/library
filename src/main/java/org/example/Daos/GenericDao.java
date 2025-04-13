package org.example.Daos;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public abstract class GenericDao<T,ID> {

    private final Session session;
    private Transaction transaction;
    private final Class<T> aClass;

    protected GenericDao(Session session, Class<T> aClass) {
        this.session = session;
        this.aClass = aClass;
    }
    public T save(T entity){
        this.transaction = session.beginTransaction();
        try {
            T savedEntity = session.merge(entity);
            transaction.commit();
            return savedEntity;
        }catch (Exception e){
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }
    protected T findById(ID id){
        return session.find(aClass,id);
    }

    protected List<T> findAll() {
        String query = String.format("select e from %s e", aClass.getSimpleName());
        Query<T>findAllQuery = session.createQuery(query,aClass);
        return findAllQuery.getResultList();
    }

    public void delete(ID id){
        this.transaction = session.beginTransaction();
        try {
            T entity = this.findById(id);
            session.remove(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }
}