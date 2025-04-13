package org.example.config;


import org.example.Entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    public static Session SessionFactory;
    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null){
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Author.class);
            configuration.addAnnotatedClass(Book.class);
            configuration.addAnnotatedClass(Member.class);
            configuration.addAnnotatedClass(Reservation.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return this.sessionFactory;

    }
}
