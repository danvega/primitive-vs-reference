# Java Class Design 

This repository contains the code examples for the Java Class Design presentation. These are a few of the topics I often get asked about when it comes to Java class design.

- [Primitive vs Reference types](#primitive-vs-reference-types)
- [Immutable Objects](#immutable-objects)
- [Record Type](#record-type)
- [Validate Record Components](#validate-record-components)

## Primitive vs Reference types

I often get asked why I use `Integer` instead of `int` when design my domain classes. The answer is simple, I use `Integer` when I need to represent a number that can be `null`. This could be the same for all the other wrapper classes. Primitive types in Java cannot be `null` and contain a default value of 0.

- Show example of using `int` and `Integer` in a class
- Create an instance and point out default values

```java
public class Todo {

    private Integer userid;
    private int id;
    private String title;
    private boolean completed;

}
```

```java
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

```

## Immutable Objects

- Immutable objects (final) the internal state must remain the same after its creation
- Show example of changing state of an object (create instance, change state)
- Change the class to be immutable

```java
public class Todo {

    private Integer userid;
    private int id;
    private String title;
    private boolean completed;

    public Todo() {
    }

    public Todo(Integer userid, int id, String title, boolean completed) {
        this.userid = userid;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    // getters, setters, equals, hashcode and toString
}
```

## Record Type

- Change class to record
- Show how to use the record (getter names)
- It is immutable by default (no need to change anything)

```java
public record Post(Integer id, String title, String body, String author) {
}
```

```java
@Test
void shouldCreateNewPost(){
    Post post = new Post(1, "Hello, World!", "This is my first post!", "danvega");
    assertAll(
        () -> assertEquals(1, post.id()),
        () -> assertEquals("Hello, World!", post.title()),
        () -> assertEquals("This is my first post!", post.body()),
        () -> assertEquals("danvega", post.author())
    );
}
```

## Validate Record Components

```java
public record Event(Integer id, String title, LocalDateTime start, LocalDateTime end) {

    public Event {
        if (end.isBefore(start)) throw new IllegalArgumentException("End must be after start: " + start + " " + end);
    }

}
```

```java
class EventTest {

    @Test
    void shouldThrowIllegalArgumentExceptionWhenEndIsBeforeStart() {
        assertThrows(IllegalArgumentException.class, () -> new Event(1,
                "SpringOne",
                LocalDateTime.now().plus(30, ChronoUnit.DAYS),
                LocalDateTime.now()));
    }

}
```