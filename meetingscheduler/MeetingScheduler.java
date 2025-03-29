package meetingscheduler;

public class MeetingScheduler {
    private static volatile MeetingScheduler instance;
    private UserService userService;
    private RoomManagementService roomService;

    private MeetingScheduler() {
        this.userService = new UserService();
        this.roomService = new RoomService();
    }

    public static MeetingScheduler getInstance() {
        if (instance == null) {
            synchronized (MeetingScheduler.class) {
                if (instance == null) {
                    instance = new MeetingScheduler();
                }
            }
        }
        return instance;
    }

    public void addUser(User user) {
        userService.addUser(user);
    }

    public void addRoom(Room room) {
        RoomService.addRoom(room);
    }

    public void addMeeting(String roomId, Meeting meeting) {
        roomService.addMeeting(roomId, meeting);
        for (User attendee : meeting.getParticipants()) {
            userService.addMeeting(attendee, meeting);
        }
        System.out.println("Meeting " + meeting.getId() + " scheduled successfully.");
    }
}