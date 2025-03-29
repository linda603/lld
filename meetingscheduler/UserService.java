package meetingscheduler;

import java.util.*;

public class UserService {
    private HashMap<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public HashMap<Duration, List<Meeting>> getMeetingOnDate(User user, LocalDate date) {
        return user.getMeetings().get(date);
    }

    public boolean addMeeting(User user, Meeting meeting) {
        HashMap<Slot, List<Meeting>> meetings = user.getMeetings();
        if (meetings.containsKey(meeting.getMeetingDate())) {
            if (meetings.containsKey(meeting.getMeetingDate().containsKey(meeting.Duration))) {
                System.out.println("A meeting is already schedule on %s - %s.", 
                                meeting.getMeetingDate().toString(), meeting.getDuration().toString());
                return false;
            } else {
                meetings.get(meeting.getMeetingDate()).put(meeting.getDuration(), meeting);
            }
        } else {
            HashMap<Duration, List<Meeting>> meetingData = new HashMap<>();
            meetingData.put(meeting.getDuration(), meeting);
            meetings.put(meeting.getMeetingDate(), meetingData);
        }
        return true;
    }
}