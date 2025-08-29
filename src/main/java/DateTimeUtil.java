import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


public class DateTimeUtil {
    public DateTimeUtil() {}

    private static final List<DateTimeFormatter> CANDIDATES = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy H:mm")
    );

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("MMMM d yyyy");
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");

    public static LocalDateTime parseFlexible(String text) {
        String s = text.trim();
        for (DateTimeFormatter f : CANDIDATES) {
            // First try LocalDateTime
            try {
                return LocalDateTime.parse(s, f);
            } catch (DateTimeParseException ignored) {}

            // Then try LocalDate
            try {
                LocalDate d = LocalDate.parse(s, f);
                return LocalDateTime.of(d, LocalTime.MIDNIGHT);
            } catch (DateTimeParseException ignored) {}
        }
        throw new IllegalArgumentException("Could not parse date/time: \"" + text + "\"");
    }

    public static String formatSmart(LocalDateTime dt) {
        if (dt.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            return dt.toLocalDate().format(DATE_FMT);         // e.g., Oct 15 2019
        }
        return dt.format(DATETIME_FMT);                       // e.g., Dec 2 2019 6:00 PM
    }
}
