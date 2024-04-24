package nl.rug.oop.introduction;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * The submission class.
 */
public class Submission {
    @Getter
    private int quality;
    @Getter
    private LocalDateTime submissionTime;
    @Getter
    private Student student;
    @Getter
    private Assignment assignment;

    /**
     * The submission constructor.
     * @param student The student submitting.
     * @param assignment The assignment being submitted.
     */
    public Submission(Student student, Assignment assignment) {
        this.student = student;
        this.assignment = assignment;
        this.submissionTime = LocalDateTime.now();
        this.quality = student.getKnowledgeLevel();
    }
}
