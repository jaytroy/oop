package nl.rug.oop.introduction;

import java.util.List;

/**
 * The lecturer class.
 */
public class Lecturer {
    private String name;

    public Lecturer(String name) {
        this.name = name;
    }

    /**
     * A method used to teach students.
     * @param attendees The list of students.
     */
    public void lecture(List<Student> attendees) {
        System.out.println(this.name + ": Don't expect to get all information from the lecture slides. Read the " +
                "entire " +
                "reader, " +
                "carefully scan every single page of the book, and even then, you can't expect to know everything " +
                "this " +
                "course requires you to know.");

        for(Student attendee : attendees) {
            attendee.obtainKnowledge();
        }
    }
}
