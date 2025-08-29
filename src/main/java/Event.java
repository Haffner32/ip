import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String desc, LocalDateTime start, LocalDateTime end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                DateTimeUtil.formatSmart(start), DateTimeUtil.formatSmart(end));
    }
}
