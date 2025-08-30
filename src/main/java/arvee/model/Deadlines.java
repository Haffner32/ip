package arvee.model;

import java.time.LocalDateTime;
import arvee.util.DateTimeUtil;

public class Deadlines extends Task {

    private LocalDateTime deadline;

    /**
     * Constructor for the deadline task
     * @param desc description for the task
     * @param deadline deadline of the task
     */
    public Deadlines(String desc, LocalDateTime deadline) {
        super(desc);
        this.deadline = deadline;
    }

    /**
     * getter for the deadline of the task
     * @return date and time for the task
     */
    public LocalDateTime getBy() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                DateTimeUtil.formatSmart(deadline));
    }
}
