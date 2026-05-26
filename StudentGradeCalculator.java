import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ── Input Phase ──────────────────────────────────────────────
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter number of subjects: ");
        int numSubjects = Integer.parseInt(sc.nextLine()); // Solution B: avoids buffer trap

        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        for (int i = 0; i < numSubjects; i++) {
            int mark;
            while (true) {
                System.out.print("Enter marks for Subject " + (i + 1) + " (0-100): ");
                mark = Integer.parseInt(sc.nextLine());

                // Defensive programming: validate input before processing
                if (mark >= 0 && mark <= 100) {
                    break;
                } else {
                    System.out.println("  Invalid input! Marks must be between 0 and 100. Try again.");
                }
            }
            marks[i] = mark;
            totalMarks += mark; // Accumulator loop (Gear 1)
        }

        // ── Process Phase ─────────────────────────────────────────────

        // Type casting to double to avoid integer truncation (Gear 2 fix)
        double average = (double) totalMarks / numSubjects;

        // Logic Ladder - check strictest condition first (Gear 3)
        String grade;
        String status;
        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        // Pass/Fail indicator
        if (average >= 60) {
            status = "PASS";
        } else {
            status = "FAIL";
        }

        // ── Output Phase ──────────────────────────────────────────────
        System.out.println("\n========================================");
        System.out.println("         STUDENT GRADE REPORT          ");
        System.out.println("========================================");
        System.out.printf("  Student Name   : %s%n", name);
        System.out.printf("  Total Subjects : %d%n", numSubjects);
        System.out.printf("  Total Marks    : %d / %d%n", totalMarks, numSubjects * 100);
        System.out.printf("  Average        : %.2f%%%n", average); // %.2f for clean formatting
        System.out.printf("  Grade          : %s%n", grade);
        System.out.printf("  Status         : %s%n", status);
        System.out.println("========================================");

        sc.close();
    }
}