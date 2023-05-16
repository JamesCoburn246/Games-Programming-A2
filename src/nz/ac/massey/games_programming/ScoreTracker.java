package nz.ac.massey.games_programming;

public class ScoreTracker {
    private static int score; // Keeps track of the players score

    public static void main(String[] args) {
        score = 50; // Set current score to 50
        System.out.println("Current score: " + getScore()); // Display score

        // Add 20 points to score and display the change
        System.out.println(updateScore(20));
        System.out.println("Current score: " + getScore());

        // Minus 100 points to score and display the change
        System.out.println(updateScore(-100));
        System.out.println("Current score: " + getScore());
    }

    public static int getScore() {
        return score;
    }

    // Function to update the players score and can be used to check if the game is over
    public static boolean updateScore(int points) {
        score += points; // Add an integer to players score

        // If player score goes below zero set it to zero
        if (score < 0) {
            score = 0;
        }
        // Return true if score is zero, otherwise false
        return (score == 0);
    }
}
