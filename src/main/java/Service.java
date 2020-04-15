import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.model.MeetingEntity;
import db.model.ParticipantEntity;
import db.services.MeetingService;
import db.services.ParticipantService;
import responses.CustomHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class Service extends HttpServlet {
    private ParticipantService participantService_ = new ParticipantService();
    private MeetingService meetingService_ = new MeetingService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        JsonNode JSON = null;
        if (request.getParameter("parameters") != null) {
            JSON = (new ObjectMapper()).readTree(request.getParameter("parameters"));
        }
        switch (action) {
            case "createMeeting":
                String date = JSON.get("date").asText();
                String time = JSON.get("time").asText();
                if (date != null && time != null) {
                    try {
                        createMeeting(date, time);
                        ((CustomHttpServletResponse) response).setParameter("answer", true);
                    } catch (Exception ex) {
                        ((CustomHttpServletResponse) response).setParameter("answer", false);
                    }
                } else {
                    System.out.println("Wrong parameters");
                }
                break;
            case "deleteMeeting":
                String id = JSON.get("id").asText();
                if (id != null) {
                    deleteMeeting(new Long(id));
                } else {
                    System.out.println("Wrong parameters");
                }
                break;
            case "addNewParticipant": {
                String meetingId = JSON.get("id").asText();
                String name = JSON.get("name").asText();
                String surname = JSON.get("surname").asText();
                String email = JSON.get("email").asText();
                if (meetingId != null && name != null && surname != null && email != null) {
                    addNewParticipantToMeeting(new Long(meetingId), name, surname, email);
                } else {
                    System.out.println("Wrong parameters");
                }
                break;
            }
            case "addParticipantById": {
                String meetingId = JSON.get("meetingId").asText();
                String participantId = JSON.get("participantId").asText();
                if (meetingId != null && participantId != null) {
                    addParticipantToMeetingById(new Long(meetingId), new Long(participantId));
                } else {
                    System.out.println("Wrong parameters");
                }
                break;
            }
            case "deleteParticipantFromMeeting": {
                String meetingId = JSON.get("meetingId").asText();
                String participantId = JSON.get("participantId").asText();
                if (meetingId != null && participantId != null) {
                    deleteParticipantFromMeeting(new Long(meetingId), new Long(participantId));
                } else {
                    System.out.println("Wrong parameters");
                }
                break;
            }
            case "showAll":
                showAllMeetingsAndParticipants();
                break;
            default:
                System.out.println("Request not supported");
        }
    }

    private void createMeeting(String date, String time) {
        MeetingEntity meetingEntity = new MeetingEntity(date, time);
        meetingService_.saveMeeting(meetingEntity);
    }

    private void deleteMeeting(Long id) {
        MeetingEntity meetingEntity = meetingService_.findMeeting(id);
        meetingService_.deleteMeeting(meetingEntity);
    }

    private void addNewParticipantToMeeting(Long meetingId, String name, String surname, String email) {
        MeetingEntity meetingEntity = meetingService_.findMeeting(meetingId);
        ParticipantEntity participantEntity = new ParticipantEntity(name, surname, email);
        participantEntity.addMeeting(meetingEntity);
        meetingEntity.addParticipant(participantEntity);
        participantService_.saveParticipant(participantEntity);
        meetingService_.updateMeeting(meetingEntity);
    }

    private void addParticipantToMeetingById(Long meetingId, Long participantId) {
        MeetingEntity meetingEntity = meetingService_.findMeeting(meetingId);
        ParticipantEntity participantEntity = participantService_.findParticipant(participantId);
        meetingEntity.addParticipant(participantEntity);
        participantEntity.addMeeting(meetingEntity);
        participantService_.updateParticipant(participantEntity);
        meetingService_.updateMeeting(meetingEntity);
    }

    private void deleteParticipantFromMeeting(Long meetingId, Long participantId) {
        MeetingEntity meetingEntity = meetingService_.findMeeting(meetingId);
        List<ParticipantEntity> participantEntityList = meetingEntity.getParticipantList();
        for (ParticipantEntity participantEntity: participantEntityList) {
            if (participantEntity.getId() == participantId) {
                meetingEntity.deleteParticipant(participantEntity);
                participantEntity.deleteMeeting(meetingEntity);
                meetingService_.updateMeeting(meetingEntity);
                participantService_.updateParticipant(participantEntity);
                break;
            }
        }
    }

    private void showAllMeetingsAndParticipants() {
        List<MeetingEntity> meetingEntityList = meetingService_.findAllMeetings();
        for (MeetingEntity meetingEntity: meetingEntityList) {
            System.out.println(meetingEntity.toString());
        }
    }
}