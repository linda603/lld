package meetingscheduler;

import java.util.*;

public class Meeting {
    private String meetingId;
    private String title;
    private String description;
    private Room room;
    private LocalDate meetingDate;
    private Duration duration;
    private User host;
    private List<User> participants;

    public Meeting(String meetingId, String title, String description,
        LocalDate date, Duration duration, User host, List<User> participants) {
        this.meetingId = meetingId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.host = host;
        this.participants = participants;
        }

    public String getId() {
        return meetingId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Duration getDate() {
        return date;
    }

    public Duration getDuration() {
        return duration;
    }

    public User getHost() {
        return host;
    }

    public List<User> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public Room getRoom() {
        return room;
    }

    public synchronized void assignRoom(Room room) {
        this.room = room;
    }

    public List<User> addParticipant(User user) {
        participants.add(user);
    }
}