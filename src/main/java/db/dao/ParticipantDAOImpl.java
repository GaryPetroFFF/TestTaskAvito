package db.dao;

import db.model.ParticipantEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import db.util.HibernateSessionFactory;

import java.util.List;

public class ParticipantDAOImpl implements ParticipantDAO {
    private Session session_;

    @Override
    public ParticipantEntity findById(Long id) {
        closeSession();
        session_ =  HibernateSessionFactory.getSessionFactory().openSession();
        ParticipantEntity participant = session_.get(ParticipantEntity.class, id);
        return participant;
    }

    @Override
    public void save(ParticipantEntity participant) {
        closeSession();
        session_ = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session_.beginTransaction();
        session_.save(participant);
        transaction.commit();
        session_.close();
    }

    @Override
    public void update(ParticipantEntity participant) {
        closeSession();
        session_ = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session_.beginTransaction();
        session_.update(participant);
        transaction.commit();
        session_.close();
    }

    @Override
    public void delete(ParticipantEntity participant) {
        closeSession();
        session_ = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session_.beginTransaction();
        session_.delete(participant);
        transaction.commit();
        session_.close();
    }

    @Override
    public List<ParticipantEntity> findAll()  {
        closeSession();
        session_ = HibernateSessionFactory.getSessionFactory().openSession();
        List<ParticipantEntity> participantEntityList = session_.createQuery("from ParticipantEntity").list();
        session_.close();
        return participantEntityList;
    }

    @Override
    public void closeSession() {
        if (session_ != null && session_.isOpen()) {
            session_.close();
        }
    }
}
