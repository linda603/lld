package meetingscheduler;

import java.util.*;

public class User {
    private String userId;
    private String name;
    private HashMap<LocalDate, HashMap<Duration, List<Meeting>>> meetings;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public HashMap<LocalDate, HashMap<Duration, List<Meeting>>> getMeetings() {
        return this.meetings;
    }
}