package org.example.Daos;


import org.example.Entities.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDao extends GenericDao<Reservation,Long> {
    private final Session session;
    private Transaction transaction;

    public ReservationDao(Session session) {
        super(session, Reservation.class);
        this.session = session;

    }
    public Reservation save (Reservation reservation){
            this.transaction = session.beginTransaction();
            try {
                Reservation savedreservation = session.merge(reservation);
                transaction.commit();
                return savedreservation;
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException(e);
            }
        }

        protected Reservation findById(Long aLong) {
            return super.findById(aLong);
        }

    public List<Reservation> findByReservation(Reservation R) {
        String query = "select a from Reservation a where a.Id like : Id ";
        Query<Reservation> findByReservationQuery = session.createQuery(query, Reservation.class);
        findByReservationQuery.setParameter("Id",R);
        return findByReservationQuery.getResultList();
    }
    public List<Reservation>findByMember(Reservation R){
        String query = "select a from Reservation a where a.member like : member";
        Query<Reservation> findByReservationQuery = session.createQuery(query, Reservation.class);
        findByReservationQuery.setParameter("member",R);
        return findByReservationQuery.getResultList();
    }

    public List<Reservation>findByStatus(Reservation R){
        String query = "select a from Reservation a where a.status like : status";
        Query<Reservation>findByReservationQuery = session.createQuery(query, Reservation.class);
        findByReservationQuery.setParameter("status",R);
        return findByReservationQuery.getResultList();
     }

    public Reservation update (Reservation reservation){
        this.transaction = session.beginTransaction();
        try {
            Reservation updatedReservation = session.merge(reservation);
            transaction.commit();
            return updatedReservation;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }
}

