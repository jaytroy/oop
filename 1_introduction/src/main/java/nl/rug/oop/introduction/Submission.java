package nl.rug.oop.introduction;

import lombok.Getter;

import java.time.LocalDateTime;

public class Submission {
    @Getter
    private int quality;
    @Getter
    private LocalDateTime submissionTime;
    @Getter
    private Student student;
    @Getter
    private Assignment assignment;

    public Submission(Student student, Assignment assignment) {
        this.student = student;
        this.assignment = assignment;
        this.submissionTime = LocalDateTime.now();
        this.quality = student.getKnowledgeLevel();
    }
}
