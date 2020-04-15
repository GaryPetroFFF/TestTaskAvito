package db.util;

import db.model.MeetingEntity;
import db.model.ParticipantEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    private static SessionFactory sessionFactory_;

    private HibernateSessionFactory() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory_ == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(ParticipantEntity.class);
                configuration.addAnnotatedClass(MeetingEntity.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory_ = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory_;
    }
}
