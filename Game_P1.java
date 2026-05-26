import java.util.Random;
import java.util.Scanner;

public class Game_P1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random(); // java.util.Random (not Math.random)

        int totalScore = 0;
        int roundNumber = 0;
        String playAgain;

        System.out.println("========================================");
        System.out.println("   WELCOME TO THE DECODELABS NUMBER GAME");
        System.out.println("========================================");

        // ── Session Persistence: do-while for multiple rounds ────────
        do {
            roundNumber++;
            // Zero-Index Shift: nextInt(100) + 1 → range 1 to 100
            int target = random.nextInt(100) + 1;
            int attempts = 0;
            final int MAX_ATTEMPTS = 7; // Attempt Limiter
            boolean win = false;

            System.out.println("\n--- Round " + roundNumber + " ---");
            System.out.println("I have picked a number between 1 and 100.");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts. Good luck!");

            // ── Feedback Loop: while (!win) ──────────────────────────
            while (!win && attempts < MAX_ATTEMPTS) {
                int remaining = MAX_ATTEMPTS - attempts;
                System.out.print("\nAttempts remaining: " + remaining + " → Enter your guess: ");

                int guess = -1;

                // ── Defensive Engineering: try-catch for invalid input ─
                try {
                    guess = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("  Invalid input! Please enter a number between 1 and 100.");
                    continue; // re-prompt without consuming an attempt
                }

                // Validate range
                if (guess < 1 || guess > 100) {
                    System.out.println("  Out of range! Please enter a number between 1 and 100.");
                    continue;
                }

                attempts++;

                // ── Logic Architecture: High / Low / Win ────────────
                if (guess == target) {
                    win = true;
                    System.out.println("\n  ✔ CORRECT! The number was " + target + ".");
                    System.out.println("  You guessed it in " + attempts + " attempt(s).");
                } else if (guess > target) {
                    System.out.println("  ↓ Too High! Try lower.");
                } else {
                    System.out.println("  ↑ Too Low! Try higher.");
                }
            }

            // ── Termination State ────────────────────────────────────
            if (!win) {
                System.out.println("\n  ✘ Out of attempts! The number was: " + target);
            }

            // ── Score Tracking ───────────────────────────────────────
            if (win) {
                int roundScore = (MAX_ATTEMPTS - attempts + 1) * 10;
                totalScore += roundScore;
                System.out.println("  Round Score: +" + roundScore + " points");
            }

            System.out.println("  Total Score: " + totalScore + " points");

            // ── Play Again prompt ────────────────────────────────────
            System.out.print("\nPlay again? [Y/N]: ");
            playAgain = sc.nextLine().trim();

        } while (playAgain.equalsIgnoreCase("Y"));

        // ── Final Report ─────────────────────────────────────────────
        System.out.println("\n========================================");
        System.out.println("            GAME OVER - FINAL REPORT   ");
        System.out.println("========================================");
        System.out.printf("  Rounds Played : %d%n", roundNumber);
        System.out.printf("  Final Score   : %d points%n", totalScore);
        System.out.println("========================================");
        System.out.println("  Thanks for playing! - DecodeLabs 2026");
        System.out.println("========================================");

        sc.close();
    }
}