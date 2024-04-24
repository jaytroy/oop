package nl.rug.oop.introduction;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * The assignment class.
 */
public class Assignment {
    @Getter
    private String name;
    @Getter
    private final LocalDateTime deadline;

    public Assignment(String name, LocalDateTime deadline) {
        this.name = name;
        this.deadline = deadline;
    }
}
