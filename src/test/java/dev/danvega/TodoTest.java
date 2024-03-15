package dev.danvega;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void shouldCreateTodoWithDefaultValues() {
        var todo = new Todo();
        todo.setUserid(1);
        todo.setTitle("First Task");

        Assertions.assertAll(
                () -> assertEquals(1, todo.getUserid()),
                () -> assertEquals(0, todo.getId()),
                () -> assertEquals("First Task", todo.getTitle()),
                () -> assertFalse(todo.isCompleted())
        );
    }

}