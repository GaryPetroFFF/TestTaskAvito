package db.services;

import db.dao.ParticipantDAO;
import db.dao.ParticipantDAOImpl;
import db.model.ParticipantEntity;

import java.util.List;

public class ParticipantService {
    private ParticipantDAO participantDAO_ = new ParticipantDAOImpl();

    public ParticipantService() {}

    public ParticipantEntity findParticipant(Long id) {
        return participantDAO_.findById(id);
    }

    public void saveParticipant(ParticipantEntity participant) {
        participantDAO_.save(participant);
    }

    public void updateParticipant(ParticipantEntity participant) {
        participantDAO_.update(participant);
    }

    public void deleteParticipant(ParticipantEntity participant) {
        participantDAO_.delete(participant);
    }

    public List<ParticipantEntity> findAllParticipants() {
        return participantDAO_.findAll();
    }
}
