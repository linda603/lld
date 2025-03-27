package concertbookingsystem;

import java.util.*;
import java.time.LocalDateTime;

public class Concert {
    private final String concertId;
    private final String performer;
    private final Venue venue;
    private final LocalDateTime dateTime;
    private final Map<String, Seat> seatMap;

    public Concert(String concertId, String performer, Venue venue, LocalDateTime dateTime) {
        this.concertId = concertId;
        this.performer = performer;
        this.venue = venue;
        this.dateTime = dateTime;
        this.seatMap = new HashMap<>();

        for (int r = 0; r < venue.getRow(); r++) {
            for (int c = 0; c < venue.getCol(); c ++) {
                String seatId = String.valueOf(r) + "-" + String.valueOf(c);
                SeatType seatType = (r < 5) ? SeatType.VIP : (r < 30) ? SeatType.PREMIUM : SeatType.STANDARD;
                double price = (seatType == SeatType.VIP) ? 70.0 : (seatType == SeatType.PREMIUM) ? 40.0 : 20.0;
                this.seatMap.put(seatId, new Seat(seatId, r, c, seatType, price));
            }
        }
    }

    public String getId() {
        return concertId;
    }

    public String getPerformer() {
        return performer;
    }

    public Venue getVenue() {
        return venue;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<Seat> getSeats() {
        return new ArrayList<>(seatMap.values());
    }

    public Seat getSeat(String seatId) {
        Seat seat = seatMap.get(seatId);
        return seat;
    }
}