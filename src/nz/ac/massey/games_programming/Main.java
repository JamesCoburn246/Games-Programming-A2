// Games Programming A2 "Game"
// Reece Bonnington
// Pierce Grant
// Josh Cressey
// James Coburn

package nz.ac.massey.games_programming;

import java.awt.event.MouseEvent;
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

    // Manages user mouse button input
    @Override
    public void mousePressed(MouseEvent e) {
        // Gets the x and y co-ordinates where the user clicked
        int x = e.getX();
        int y = e.getY();

        // Return if non-left mouse click
        if (e.getButton() != MouseEvent.BUTTON1) {
            return;
        }

        // If user clicks the Play button, start the game
        if (x > 570 && y > 190) {
            if (x < 716 && y < 270) {
                System.out.println("Starting the game!");
                // TO-DO: Add code that starts the game here
            }
        }

        // If user clicks the Quit button, exit the game
        if (x > 570 && y > 340) {
            if (x < 710 && y < 410) {
                System.out.println("Exiting game...");
                System.exit(420);
                mFrame.dispose();
                mFrame.setVisible(false);
            }
        }

        // If the user left-clicks the screen, display that position
        System.out.println("Left click at position (" + x + ", " + y + ")");
    }
}
