package concertbookingsystem;

public class Venue {
    private final String venueId;
    private final int row;
    private final int col;

    public Venue(String venueId, int row, int col) {
        this.venueId = venueId;
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}