package db.dao;

import db.model.MeetingEntity;

import java.util.List;

public interface MeetingDAO {
    MeetingEntity findById(Long id);

    void save(MeetingEntity meeting);

    void update(MeetingEntity meeting);

    void delete(MeetingEntity meeting);

    List<MeetingEntity> findAll();

    void closeSession();
}
