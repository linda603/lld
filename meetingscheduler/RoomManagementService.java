package meetingscheduler;

import java.util.*;

public class RoomManagementService {
    private ConcurrentHashMap<String, ConcurrentHashMap<LocalDate, ConcurrentHashMap<Duration, Meeting>>> meetings;
    private ConcurrentHashMap<String, RoomBooking> rooms;

    public RoomManagementService() {
        this.meetings = new ConcurrentHashMap<>();
        this.rooms = new ConcurrentHashMap<>();
    }

    public void addRoom(Room room) {
        rooms.put(room.getId(), new RoomBooking(room));
        meetings.put(room, getId(), new ConcurrentHashMap<>());
    }

    public void addMeeting(String roomId, Meeting meeting) {
        if (meetings.get(roomId).containsKey(meeting.getMeetingDate())) {
            if (meetings.get(roomId).containsKey(meeting.getMeetingDate()).containsKey(meeting.getDuration())) {
                throw new RoomAlreadyBookedException("Room is already booked. Please check again.");
            } else {
                meetings.get(RoomId).get(meeting.getMeetingDate()).put(meeting.getDuration(), meeting);
            }
        } else {
            ConcurrentHashMap<Duration, Meeting> meetingData = new ConcurrentHashMap<>();
            meetingData.put(meeting.getDuration(), meeting);
            meetings.get(room).put(meeting.getMeetingDate(), meetingData);
        }
        
    }

    public boolean isRoomAvailable(String roomId, LocalDate date, Duration duration) {
        if (meetings.get(roomId).containsKey(date)) {
            return !meetins.get(roomId).get(date).containsKey(duration);
        }
        return true;
    }

}