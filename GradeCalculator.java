package codesoft;

import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Subject[] subjects = new Subject[5];
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the marks scored in each subject for 100: ");
        String[] subjectNames = {"Sanskrit","English", "Maths", "Physics", "Chemistry"};
        for (int i = 0; i < subjects.length; i++) {
            System.out.println("Enter marks for " + subjectNames[i] + ": ");
            subjects[i] = new Subject(subjectNames[i], in.nextInt());
        }

        int totalMarks = calculateTotalMarks(subjects);
        double avgper = calculateAveragePercentage(totalMarks, subjects.length);
        String grade = calculateGrade(avgper);

        System.out.println("Grade obtained in Subjects:\n");
        System.out.println("Subject\t\tMarks");
        for (Subject subject : subjects) {
            System.out.println(subject.getName() + "\t\t" + subject.getMarks());
        }
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + avgper);
        System.out.println("Grade: " + grade);
    }

    private static int calculateTotalMarks(Subject[] subjects) {
        int totalMarks = 0;
        for (Subject subject : subjects) {
            totalMarks += subject.getMarks();
        }
        return totalMarks;
    }

    private static double calculateAveragePercentage(int totalMarks, int subjectCount) {
        return (double) totalMarks / subjectCount;
    }

    private static String calculateGrade(double avgper) {
        if (avgper >= 90) {
            return "A";
        } else if (avgper >= 80) {
            return "B";
        } else if (avgper >= 70) {
            return "C";
        } else if (avgper >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    static class Subject {
        private final String name;
        private final int marks;

        public Subject(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public int getMarks() {
            return marks;
        }
    }
}