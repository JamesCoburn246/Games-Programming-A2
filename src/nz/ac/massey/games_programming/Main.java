// Games Programming A2 "Game"
// Reece Bonnington
// Pierce Grant
// Josh Cressey
// James Coburn

package nz.ac.massey.games_programming;

import nz.ac.massey.games_programming.props.Explosive;
import nz.ac.massey.games_programming.props.Player;
import nz.ac.massey.games_programming.props.PropType;
import nz.ac.massey.games_programming.util.CardinalDirection;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Main extends GameEngine {
    private Player player;
    private static final Color lightBlue = new Color(65, 77, 100);
    private final Grid grid = new Grid(LevelManager.GRID_WIDTH, LevelManager.GRID_HEIGHT);
    public GameState gameState;
    int keyPressed;
    boolean bombDropped;

    public static void main(String[] args) {
        createGame(new Main(), 30);
    }

    public void init() {
        // Determine how wide the game window needs to be.
        // NOTE: This can be extended for elements outside the grid.
        mWidth = grid.determineScreenWidth();
        mHeight = grid.determineScreenHeight();
        setWindowSize(width(), height());

        player = new Player(10, 10, grid.getCell(10,10), grid, CardinalDirection.UP, 2);

//        // Testing.
//        Grid.Cell cell = grid.getCell(0,0);
//        cell.setContents(new Explosive(0,0, cell));

        LevelManager.loadLevelIntoGrid(grid, LevelManager.GameLevel.ONE);
        
        // Sets initial GameState and outputs to console
        this.gameState = new GameState(this);
        gameState.setGameState(GameState.State.MAIN_MENU);
    }

    /**
     * Update game physics etc.
     * @param dt time since last update, measured in seconds.
     */
    @Override
    public void update(double dt) {
        // Currently set to Space Bar (Maybe "Space" to place, "E" to blow up bombs?).
        if (bombDropped && gameState.is(GameState.State.PLAYING)) {
            int pointX = 500;  // Example,Change to currentPlayPos or whatever
            int pointY = 500;  // Example, currentPlayPos or whatever
            int cellIndex = grid.calculateGrid(width(), height(), pointX, pointY);
            System.out.println("Bomb Dropped At Grid Reference: " + cellIndex);
            bombDropped = false;
        }
        // Update animations.
        grid.updateAll(dt);
    }

    /**
     * Update game graphics etc.
     */
    @Override
    public void paintComponent() {
        switch (gameState.getGameState()) {
            case MAIN_MENU -> {
                paintMainMenu();
            }
            case PLAYING, PAUSED -> {
                paintGame();
            }
            case GAME_OVER -> {
                paintGame();
                paintEndGameOverlay();
            }
        }
    }

    /**
     * Paint the main menu screen that is used at the start of the game and when Esc is pressed.
     */
    private void paintMainMenu() {
        // Reset background.
        changeBackgroundColor(lightBlue);
        clearBackground(width(), height());

        // Play and Quit Buttons in black text
        changeColor(Color.black);
        drawCenteredText(250, "Play", "Arial", 65);
        drawCenteredText(400, "Quit", "Arial", 65);
    }

    /**
     * Paint the grid, each prop, the player, etc.
     */
    private void paintGame() {
        // Reset background.
        changeBackgroundColor(Color.darkGray);
        clearBackground(width(), height());

        // Draw each item in the grid.
        grid.drawAll(this);

        // Draw the player.
        player.draw(this);
    }

    /**
     * Paint the end game screen overtop of the actual game.
     */
    private void paintEndGameOverlay() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            // TODO: Add character position change
            ///////// W A S D - Movement Keys /////////////
            ///////// Arrow Key - Movement Keys /////////////
            // If user presses W or up arrow
            case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                System.out.println("KeyPressed: Up");
                player.moveUp(grid);
                keyPressed = 1;
            }
            // If user presses A or left arrow
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                System.out.println("KeyPressed: Left");
                player.moveLeft(grid);
                keyPressed = 2;
            }
            // If user presses S or down arrow
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                System.out.println("KeyPressed: Down");
                player.moveDown(grid);
                keyPressed = 3;
            }
            // If user presses D or right arrow
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                System.out.println("KeyPressed: Right");
                player.moveRight(grid);
                keyPressed = 4;
            }

            ///////// Special Keys - Interaction and Pause Menu /////////////
            // If user presses space
            case KeyEvent.VK_SPACE -> {
                System.out.println("KeyPressed: Space");
            }
            // If user presses E //// DROP BOMB
            case KeyEvent.VK_E -> {
                System.out.println("KeyPressed: E");
                Grid.Cell cell = grid.getCell(3,3);
                Explosive exp = (Explosive) cell.getContents();
                exp.lightFuse();
            }
            // If user presses escape, display the main menu
            case KeyEvent.VK_ESCAPE -> {
                System.out.println("KeyPressed: Esc");
                // Toggle between main menu and playing modes.
                if (gameState.is(GameState.State.MAIN_MENU)) {
                    gameState.setGameState(GameState.State.PLAYING);
                } else if (gameState.is(GameState.State.PLAYING)) {
                    gameState.setGameState(GameState.State.MAIN_MENU);
                }
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

        // If player presses the Play button in the menu, game will start
        if (x > 255 && x < 395 && y > 190 && y < 265) {
            if (gameState.is(GameState.State.MAIN_MENU)) {
                System.out.println("Starting the game!");
                gameState.setGameState(GameState.State.PLAYING);
            }
        }

        // If player presses the Quit button in the menu, game will start
        if (x > 250 && x < 385 && y > 340 && y < 405) {
            if (gameState.is(GameState.State.MAIN_MENU)) {
                System.out.println("Exiting game...");
                gameState.is(GameState.State.GAME_OVER);
                System.exit(420);
                mFrame.dispose();
                mFrame.setVisible(false);
            }
        }

        // If the user left-clicks the screen, display that position
        System.out.println("Left click at position (" + x + ", " + y + ")"); // Use for debugging
    }
}
