package db.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "participants")
@JsonIgnoreProperties(value = {"meetingList"})
public class ParticipantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participant_id;
    @Column(name = "firstName")
    private String name_;
    @Column(name = "surname")
    private String surname_;
    @Column(name = "email")
    private String email_;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="participants_meetings",
            joinColumns = @JoinColumn(name = "participantId", referencedColumnName = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "meetingId", referencedColumnName = "meeting_id")
    )
    private Set<MeetingEntity> meetingSet_ = new HashSet<>();

    public ParticipantEntity() {

    }

    public ParticipantEntity(String name, String surname, String email) {
        name_ = name;
        surname_ = surname;
        email_ = email;
    }

    public void setName(String name) { name_ = name; }

    public void setSurname(String surname) { surname_ = surname; }

    public void setEmail(String email) { email_ = email; }

    public void addMeeting(MeetingEntity meeting) {
        meetingSet_.add(meeting);
    }

    public void deleteMeeting(MeetingEntity meeting) { meetingSet_.remove(meeting); }

    public Long getId() { return participant_id; }

    public String getName() { return name_; }

    public String getSurname() { return surname_; }

    public String getEmail() { return email_; }

    public List<MeetingEntity> getMeetingList() { return new ArrayList<MeetingEntity>(meetingSet_); }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.print(e.getMessage());
            return null;
        }
    }
}
