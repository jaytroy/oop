package nl.rug.oop.introduction;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class.
 */
public class Main {
    /**
     * The main method.
     * @param args Args.
     */
    public static void main(String[] args) {
        Lecturer lecturer1 = new Lecturer("Professor Stacy");

        Student student1 = new Student(0, "John");
        Student student2 = new Student(1, "Hannah");
        Student student3 = new Student(2, "Bobert");
        Student student4 = new Student(3, "Spud");

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        lecturer1.lecture(students);

        Assignment assignment1 = new Assignment("Do some coding", LocalDateTime.of(2024, Month.APRIL, 29, 17, 0));

        TeachingAssistant teachingAssistant1 = new TeachingAssistant("Jessica");

        List<Submission> submissions = new ArrayList<>();

        submissions.add(student1.doAssignment(assignment1));
        submissions.add(student2.doAssignment(assignment1));
        submissions.add(student3.doAssignment(assignment1));
        submissions.add(student4.doAssignment(assignment1));

        teachingAssistant1.gradeAll(submissions);
    }
}