package nl.rug.oop.introduction;

import java.util.List;
import java.util.Random;

/**
 * The teaching assistant class.
 */
public class TeachingAssistant {
    private String name;
    private final int MINIMUM_GRADE = 0;

    public TeachingAssistant(String name) {
        this.name = name;
    }

    /**
     * A method to grade a student's submission.
     *
     * @param sub The submission.
     */
    public void grade(Submission sub) {
        if (sub.getSubmissionTime().isBefore(sub.getAssignment().getDeadline())) {
            Random random = new Random();
            int grade = random.nextInt(5) + sub.getQuality();
            //System.out.println(sub.getQuality());
            System.out.println("Student " + sub.getStudent().getName() + " received grade " + grade + " for assignment "
                    + sub.getAssignment().getName() + ". Graded by " + this.name + ".");
        } else {
            System.out.println("Student " + sub.getStudent().getName() + " received grade " + MINIMUM_GRADE +
                    " for assignment "
                    + sub.getAssignment().getName() + ". Graded by " + this.name + ".");
        }
    }

    /**
     * A method to grade the submissions of multiple students.
     *
     * @param submissions The list of submissions.
     */
    public void gradeAll(List<Submission> submissions) {
        for (Submission sub : submissions) {
            grade(sub);
        }
    }
}
