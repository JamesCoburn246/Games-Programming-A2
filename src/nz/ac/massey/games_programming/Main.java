// Games Programming A2 "Game"
// Reece Bonnington
// Pierce Grant
// Josh Cressey
// James Coburn

package nz.ac.massey.games_programming;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Main extends GameEngine {

    private static final Color lightBlue = new Color(65, 77, 100);

    private static final int GRID_WIDTH = 20, GRID_HEIGHT = 20;
    private final Grid grid = new Grid(GRID_WIDTH, GRID_HEIGHT);
    // Manages user keyboard input
    int keyPressed;
    boolean bombDropped;
    boolean isGameStarted;

    public static void main(String[] args) {
        createGame(new Main(), 30);
    }

    public void init() {
        setWindowSize(width(), height());

    }

    @Override
    public void update(double dt) {
        // Currently set to Space Bar (Maybe "Space" to place, "E" to blow up bombs?).
        if (bombDropped && isGameStarted) {
            int pointX = 500;  // Example,Change to currentPlayPos or whatever
            int pointY = 500;  // Example, currentPlayPos or whatever
            int cellIndex = grid.calculateGrid(width(), height(), pointX, pointY);
            System.out.println("Bomb Dropped At Grid Reference: " + cellIndex);
            bombDropped = false;
        }
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

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            // TO-DO: Add character position change
            ///////// W A S D - Movement Keys /////////////
            ///////// Arrow Key - Movement Keys /////////////
            // If user presses W or up arrow
            case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                System.out.println("KeyPressed: Up");
                keyPressed = 1;
            }
            // If user presses A or left arrow
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                System.out.println("KeyPressed: Left");
                keyPressed = 2;
            }
            // If user presses S or down arrow
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                System.out.println("KeyPressed: Down");
                keyPressed = 3;
            }
            // If user presses D or right arrow
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                System.out.println("KeyPressed: Right");
                keyPressed = 4;
            }

            ///////// Special Keys - Interaction and Pause Menu /////////////
            // If user presses space
            case KeyEvent.VK_SPACE -> {
                System.out.println("KeyPressed: Space");
                bombDropped = true;

            }
            // If user presses E //// DROP BOMB
            case KeyEvent.VK_E -> {
                System.out.println("KeyPressed: E");
            }
            // If user presses escape, display the main menu
            case KeyEvent.VK_ESCAPE -> {
                System.out.println("KeyPressed: Esc");
                displayMainMenu();
            }
        }
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
                isGameStarted = true;
                // TO-DO: Add code that starts the game here
            }
        }

        // If user clicks the Quit button, exit the game
        if (x > 570 && y > 340) {
            if (x < 710 && y < 410) {
                System.out.println("Exiting game...");
                isGameStarted = false;
                System.exit(420);
                mFrame.dispose();
                mFrame.setVisible(false);
            }
        }

        // If the user left-clicks the screen, display that position
        // System.out.println("Left click at position (" + x + ", " + y + ")"); // Use for debugging
    }

}
