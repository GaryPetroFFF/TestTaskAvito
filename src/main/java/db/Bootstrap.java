package db;

import db.model.MeetingEntity;
import db.model.ParticipantEntity;
import db.services.MeetingService;
import db.services.ParticipantService;

import java.sql.SQLException;

public class Bootstrap {
    public static void main(String[] args) throws SQLException {
        ParticipantService participantService = new ParticipantService();
        ParticipantEntity participant = participantService.findParticipant(new Long(3));
        MeetingService meetingService = new MeetingService();
//        MeetingEntity meeting = new MeetingEntity("15.05.2020", "17:00");
//        meetingService.saveMeeting(meeting);
        MeetingEntity meeting = meetingService.findMeeting(new Long(1));
        System.out.println("IT WORKS");
        System.out.println(meeting.toString());
        System.out.println(participant.toString());
//        System.out.println(participant.toString());
    }
}
