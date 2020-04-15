package db.dao;

import db.model.ParticipantEntity;

import java.util.List;

public interface ParticipantDAO {
    ParticipantEntity findById(Long id);

    void save(ParticipantEntity participant);

    void update(ParticipantEntity participant);

    void delete(ParticipantEntity participant);

    List<ParticipantEntity> findAll();

    void closeSession();
}
