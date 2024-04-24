package nl.rug.oop.introduction;

import java.util.List;
import java.util.Random;

public class TeachingAssistant {
    private String name;
    private final int MINIMUM_GRADE = 0;

    public TeachingAssistant(String name) {
        this.name = name;
    }

    public void grade(Submission sub) {
        if(sub.getSubmissionTime().isBefore(sub.getAssignment().getDeadline())) {
            Random random = new Random();
            int grade = random.nextInt(5) + sub.getQuality();
            //System.out.println(sub.getQuality());
            System.out.println("Student " + sub.getStudent().getName() + " received grade " + grade + " for assignment "
                    + sub.getAssignment().getName() + ". Graded by " + this.name + ".");
        } else {
            System.out.println("Student " + sub.getStudent().getName() + " received grade " + MINIMUM_GRADE + " for assignment "
                    + sub.getAssignment().getName() + ". Graded by " + this.name + ".");
        }
    }

    public void gradeAll(List<Submission> submissions) {
        for(Submission sub : submissions) {
            grade(sub);
        }
    }
}
