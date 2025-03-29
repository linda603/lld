package meetingscheduler;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.*;

public class MeetingSchedulerDemo {
    public static void main(string[] args) {
        MeetingScheduler meetingScheduler = MeetingScheduler.getInstance();

        User user1 = new User("U1", "User1");
        User user2 = new User("U2", "user2");
        User user3 = new User("U3", "user3");
        meetingScheduler.addUser(user1);
        meetingScheduler.addUser(user2);
        meetingScheduler.addUser(user3);

        Room room1 = new MeetingRoom("Room1", 5, RoomType.CONFERENCE);
        Room room2 = new MeetingRoom("Room2", 6, RoomType.CONFERENCE);
        meetingScheduler.addRoom(room1);
        meetingScheduler.addRoom(room2);

        // Define meeting times
        LocalDate today = LocalDate.now();
        LocalDateTime startTime1 = today.atTime(LocalTime.now().plusHours(10));
        LocalDateTime endTime1 = today.atTime(LocalTime.now().plusHours(11));

        LocalDate tomorrow = today.plusDays(1);
        LocalDateTime startTime2 = tomorrow.atTime(LocalTime.of(13, 0));
        LocalDateTime endTime2 = tomorrow.atTime(LocalTime.of(14, 0));

        // Create meetings
        List<User> participant1 = Arrays.asList(user1, user2, user3);
        Meeting meeting1 = new Meeting("M1", "meeting1", "description1", room1, today, new Duration(startTime1, endTime1), user1, participant1);
        Meeting meeting2 = new Meeting("M2", "meeting2", "description2", room2, tomorrow, new Duration(startTime2, endTime2), user1, participant1);
    }
}