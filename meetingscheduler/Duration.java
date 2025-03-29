package meetingscheduler;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.*;

public class Duration {
    private LocalDateTime start;
    private LocalDateTime end;

    public Duration(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}