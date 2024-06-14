package nl.rug.oop.introduction;

import lombok.Getter;

/**
 * The student class.
 */
public class Student {
    @Getter
    private int knowledgeLevel;
    @Getter
    private String name;

    public Student(int knowledgeLevel, String name) {
        this.knowledgeLevel = Math.min(knowledgeLevel,6);
        this.name = name;
    }

    public void obtainKnowledge() {
        knowledgeLevel = Math.min(knowledgeLevel + 1, 6);
    }

    public Submission doAssignment(Assignment ass) {
        return new Submission(this, ass);
    }
}
