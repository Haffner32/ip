package arvee.util;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class DateTimeUtilTest {

    static Stream<String> dateOnly() {
        return Stream.of(
                "2019-10-15",
                " 2019-10-15 ",
                "2/12/2019"
        );
    }

    static Stream<String> dateTime24h() {
        return Stream.of(
                "2019-12-02 1800",
                "2019-12-02 18:00",
                "2/12/2019 2:00",
                " 2/12/2019 14:00 "
        );
    }

    @ParameterizedTest
    @MethodSource("dateOnly")
    @DisplayName("Parses date-only inputs to midnight time")
    void parsesDateOnly(String input) {
        LocalDateTime dt = DateTimeUtil.parseFlexible(input);
        assertEquals(LocalTime.MIDNIGHT, dt.toLocalTime());
        assertEquals(2019, dt.getYear());
    }

    @ParameterizedTest
    @MethodSource("dateTime24h")
    @DisplayName("Parses 24h date-time inputs")
    void parsesDateTime24h(String input) {
        LocalDateTime dt = DateTimeUtil.parseFlexible(input);
        assertEquals(2019, dt.getYear());
        assertEquals(12, dt.getMonthValue());
        assertEquals(2, dt.getDayOfMonth());
    }

    @Test
    @DisplayName("Rejects nonsense inputs")
    void rejectsBad() {
        assertThrows(IllegalArgumentException.class,
                () -> DateTimeUtil.parseFlexible("not-a-date"));
    }
}
