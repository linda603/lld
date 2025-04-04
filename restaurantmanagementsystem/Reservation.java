package restaurantmanagementsystem;

import java.time.LocalDateTime;

public class Reservation {
    private String id;
    private User user;
    private int partySize;
    private LocalDateTime reservationTime;

    public Reservation(String id, User user, int partySize, LocalDateTime reservationTime) {
        this.id = id;
        this.user = user;
        this.partySize = partySize;
        this.reservationTime = reservationTime;
    }

    public String getId() {
        return id;
    }
}
