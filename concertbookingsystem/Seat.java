package concertbookingsystem;

public class Seat {
    private final String seatId;
    private final int row;
    private final int col;
    private final SeatType type;
    private SeatStatus status;
    private final double price;

    public Seat(String seatId, int row, int col, SeatType type, double price) {
        this.seatId = seatId;
        this.row = row;
        this.col = col;
        this.type = type;
        this.price = price;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getId() {
        return seatId;
    }

    public SeatType getType() {
        return type;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public synchronized void book() {
        if (status == SeatStatus.AVAILABLE) {
            status = SeatStatus.BOOKED;
        } else {
            throw new SeatNotAvailableException("Seat is not available. Please choose another seat.");
        }
    }

    public synchronized void release() {
        if (status == SeatStatus.BOOKED) {
            status = SeatStatus.AVAILABLE;
        }
    }
}