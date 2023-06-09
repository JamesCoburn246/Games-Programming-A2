// Games Programming A2 "Game"
// Reece Bonnington (15357959)
// Pierce Grant (21018347)
// Josh Cressey
// James Coburn (19044568)

package nz.ac.massey.games_programming;

import nz.ac.massey.games_programming.props.*;
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
    Image mainMenu = GameEngine.loadImage("Images/HUD/MainMenu3.png");

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
        // TODO
        changeColor(Color.black);
        drawText(0, 30, ("Score: " + ScoreTracker.getScore()), "Arial", 30);
    }

    /**
     * Paint the main menu screen that is used at the start of the game and when Esc is pressed.
     */
    private void paintMainMenu() {
        drawImage(mainMenu, 0, 0);
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

        // Escape to toggle between menu and playing
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.out.println("KeyPressed: Esc");
            // Toggle between main menu and playing modes.
            if (gameState.is(GameState.State.MAIN_MENU)) {
                gameState.setGameState(GameState.State.PLAYING);
            }
            else if (gameState.is(GameState.State.PLAYING)) {
                gameState.setGameState(GameState.State.MAIN_MENU);
            }
        }
        // Other keyboard inputs are only acknowledged if player is not in the menu
        else if (gameState.is(GameState.State.PLAYING)) {
            switch (keyCode) {

                ///////// W A S D - Movement Keys /////////////
                ///////// Arrow Key - Movement Keys /////////////
                // If user presses W or up arrow
                case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                    System.out.println("KeyPressed: Up");
                    player.moveUp(grid);
                    player.isOnCollectable(grid);
                    keyPressed = 1;
                }
                // If user presses A or left arrow
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                    System.out.println("KeyPressed: Left");
                    player.moveLeft(grid);
                    player.isOnCollectable(grid);
                    keyPressed = 2;
                }
                // If user presses S or down arrow
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                    System.out.println("KeyPressed: Down");
                    player.moveDown(grid);
                    player.isOnCollectable(grid);
                    keyPressed = 3;
                }
                // If user presses D or right arrow
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                    System.out.println("KeyPressed: Right");
                    player.moveRight(grid);
                    player.isOnCollectable(grid);
                    keyPressed = 4;
                }

                ///////// Special Keys - Interaction and Pause Menu /////////////

                // If user presses space //// PLACE BOMB
                case KeyEvent.VK_SPACE -> {
                    System.out.println("KeyPressed: Space");

                    // Places bomb on the same cell the player is standing on if not already standing on a bomb
                    if (!(grid.getCell(player.getX(), player.getY()).getContents() instanceof Explosive)) {
                        if (player.getExplosiveCount() > 0) {
                            Grid.Cell cell = grid.getCell(player.getX(), player.getY());
                            cell.setContents(new Explosive(player.getX(), player.getY(), cell, grid));

                            // Decreases bombCount by 1
                            player.bombPlaced();
                            System.out.println("Bomb Placed!");
                            System.out.println("Bombs remaining = " + player.getExplosiveCount());
                        }
                        else {
                            System.out.println("No explosives remaining!");
                        }

                    }
                    else {
                        System.out.println("Player is already standing on a bomb!");
                    }

                }

                // If user presses Q //// DETONATE BOMB IF PLAYER IS ADJACENT TO BOMB
                case KeyEvent.VK_Q -> {
                    System.out.println("KeyPressed: Q");

                    // Ignites all bombs adjacent to the player if they have a detonator (Order of checking: Right, Left, Below, Above)
                    if (player.getDetonatorCount() > 0) {
                        Grid.Cell cell;

                        for (int i = 0; i < 4; i++) {

                            if (i == 0) {
                                cell = grid.getCell(player.getX() + 1, player.getY());
                            }
                            else if (i == 1) {
                                cell = grid.getCell(player.getX() - 1, player.getY());
                            }
                            else if (i == 2) {
                                cell = grid.getCell(player.getX(), player.getY() + 1);
                            }
                            else {
                                cell = grid.getCell(player.getX(), player.getY() - 1);
                            }

                            if (cell.getContents().getType() == PropType.EXPLOSIVE) {
                                System.out.println("Igniting bomb!");
                                Explosive exp = (Explosive) cell.getContents();
                                exp.lightFuse();

                                // Decreases detonatorCount by 1
                                player.detonatorUsed();
                                System.out.println("Detonators remaining = " + player.getDetonatorCount());
                            }
                        }
                    }
                    else {
                        System.out.println("No detonators remaining!");
                    }
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
        if (x > 170 && x < 470 && y > 250 && y < 310) {
            if (gameState.is(GameState.State.MAIN_MENU)) {
                System.out.println("Starting the game!");
                gameState.setGameState(GameState.State.PLAYING);
            }
        }

        // If player presses the Quit button in the menu, game will start
        if (x > 175 && x < 470 && y > 340 && y < 400) {
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
