// Games Programming A2 "Game"
// Reece Bonnington
// Pierce Grant
// Josh Cressey
// James Coburn

package nz.ac.massey.games_programming;

import java.awt.*; // For Color

public class Main extends GameEngine {
    private static final Color lightBlue = new Color(65, 77, 100);
    private int winWidth = 1280, winHeight = 720;

    public static void main(String[] args) {
        createGame(new Main(), 30);
    }

    public void init() {
        setWindowSize(winWidth, winHeight);
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void paintComponent() {
        displayMainMenu();
    }

    public void displayMainMenu() {
        // Light blue background
        changeBackgroundColor(lightBlue);
        clearBackground(width(), height());

        // Play and Quit Buttons in black text
        changeColor(Color.black);
        drawCenteredText(250, "Play", "Arial", 65);
        drawCenteredText(400, "Quit", "Arial", 65);
    }
}
