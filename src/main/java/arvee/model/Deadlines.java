package arvee.model;

import java.time.LocalDateTime;
import arvee.util.DateTimeUtil;

public class Deadlines extends Task {

    private LocalDateTime deadline;

    public Deadlines(String desc, LocalDateTime deadline) {
        super(desc);
        this.deadline = deadline;
    }

    public LocalDateTime getBy() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                DateTimeUtil.formatSmart(deadline));
    }
}
