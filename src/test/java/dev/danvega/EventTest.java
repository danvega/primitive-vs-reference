package dev.danvega;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void shouldThrowIllegalArgumentExceptionWhenEndIsBeforeStart() {
        assertThrows(IllegalArgumentException.class, () -> new Event(1,
                "SpringOne",
                LocalDateTime.now().plus(30, ChronoUnit.DAYS),
                LocalDateTime.now()));
    }

}