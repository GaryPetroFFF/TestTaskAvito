package db.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "meetings")
public class MeetingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meeting_id;
    @Column(name = "date")
    private String date_;
    @Column(name = "time")
    private String time_;
    @ManyToMany(mappedBy = "meetingSet_")
    private Set<ParticipantEntity> participantSet_ = new HashSet<>();

    public MeetingEntity() {

    }

    public MeetingEntity(String date, String time) {
        date_ = date;
        time_ = time;
    }

    public void setDate(String date) { date_ = date; }

    public void setTime(String time) { time_ = time; }

    public void addParticipant(ParticipantEntity participant) {
        participantSet_.add(participant);
    }

    public void deleteParticipant(ParticipantEntity participant) {
        participantSet_.remove(participant);
    }

    public Long getId() { return meeting_id; }

    public String getDate() { return date_; }

    public String getTime() { return time_; }

    public List<ParticipantEntity> getParticipantList() { return new ArrayList<ParticipantEntity>(participantSet_); }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
