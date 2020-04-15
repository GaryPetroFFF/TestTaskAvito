package db.dao;

import db.model.MeetingEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import db.util.HibernateSessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class MeetingDAOImpl implements MeetingDAO {
    private Session session_;
    @Override
    public MeetingEntity findById(Long id) {
        closeSession();
        session_ =  HibernateSessionFactory.getSessionFactory().openSession();
        MeetingEntity meeting = session_.get(MeetingEntity.class, id);
        Hibernate.initialize(meeting.getParticipantList());
        return meeting;
    }

    @Override
    public void save(MeetingEntity meeting) {
        closeSession();
        session_ = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session_.beginTransaction();
        session_.save(meeting);
        transaction.commit();
        session_.close();
    }

    @Override
    public void update(MeetingEntity meeting) {
        closeSession();
        session_ = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session_.beginTransaction();
        session_.update(meeting);
        transaction.commit();
        session_.close();
    }

    @Override
    public void delete(MeetingEntity meeting) {
        closeSession();
        session_ = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session_.beginTransaction();
        session_.delete(meeting);
        transaction.commit();
        session_.close();
    }

    @Override
    public List<MeetingEntity> findAll() {
        closeSession();
        session_ = HibernateSessionFactory.getSessionFactory().openSession();
        List<MeetingEntity> meetingEntityList = session_.createQuery("from MeetingEntity").list();
        session_.close();
        return meetingEntityList;
    }

    @Override
    public void closeSession() {
        if (session_ != null && session_.isOpen()) {
            session_.close();
        }
    }
}
