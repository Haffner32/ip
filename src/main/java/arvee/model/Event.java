package arvee.model;

import java.time.LocalDateTime;
import arvee.util.DateTimeUtil;

public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for the event task
     * @param desc description for the tasks
     * @param start start date/time
     * @param end end date/time
     */
    public Event(String desc, LocalDateTime start, LocalDateTime end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    /**
     * getter for the start time
     * @return date/time for start
     */
    public LocalDateTime getStart() {
        return this.start;
    }

    /**
     * getter for end time
     * @return date/time for end
     */
    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                DateTimeUtil.formatSmart(start), DateTimeUtil.formatSmart(end));
    }
}
