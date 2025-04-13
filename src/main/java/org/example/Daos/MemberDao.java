package org.example.Daos;


import org.example.Entities.Member;
import org.example.Entities.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.sql.Update;

import java.util.List;

public class MemberDao extends GenericDao<Member, Long> {
    private final Session session;
    private Transaction transaction;

    public MemberDao(Session session) {
        super(session, Member.class);
        this.session = session;
    }


    public Member registerMember(Member member) {
        return save(member);
    }


    public Member updateContactInfo(Long memberId, String newContactInfo) {
        this.transaction = session.beginTransaction();
        try {
            Member member = findById(memberId);
            member.setContactInfo(newContactInfo);
            session.merge(member);
            transaction.commit();
            return member;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    public List<Reservation> getReservationHistory(Long memberId) {
        String hql = "SELECT r FROM Reservation r WHERE r.member.memberId = :memberId";
        Query<Reservation> query = session.createQuery(hql, Reservation.class);
        query.setParameter("memberId", memberId);
        return query.getResultList();
    }
}