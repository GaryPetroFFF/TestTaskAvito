package db.services;

import db.dao.MeetingDAO;
import db.dao.MeetingDAOImpl;
import db.model.MeetingEntity;

import java.util.List;

public class MeetingService {
    private MeetingDAO meetingDAO_ = new MeetingDAOImpl();

    public MeetingService() {}

    public MeetingEntity findMeeting(Long id) { return meetingDAO_.findById(id); }

    public void saveMeeting(MeetingEntity meeting) { meetingDAO_.save(meeting); }

    public void updateMeeting(MeetingEntity meeting) { meetingDAO_.update(meeting); }

    public void deleteMeeting(MeetingEntity meeting) { meetingDAO_.delete(meeting); }

    public List<MeetingEntity> findAllMeetings() {
        return meetingDAO_.findAll();
    }
}
