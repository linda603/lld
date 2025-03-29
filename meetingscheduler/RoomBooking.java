package meetingscheduler;

import java.util.*;

public class RoomBooking {
    private Room room;
    private TreeSet<Duration> bookings;

    public RoomBooking(Room room) {
        this.room = room;
        this.bookings = new TreeSet<>(Util::compareDuration);
    }

    public synchronized void addDuration(Duration duration) {
        this.bookings.add(duration);
    }

    public synchronized void removeDuration(Duration duration) {
        this.bookings.remove(duration);
    }

    public List<Duration> getBookings() {
        return new ArrayList<>(this.bookings);
    }

    public Room getRoom() {
        return room;
    }
}