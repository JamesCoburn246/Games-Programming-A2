package nz.ac.massey.games_programming;

import java.awt.*;

public class Player {
    private int x, y; // x and y co-ordinate of the player
    private int explosiveCount;
    private int detonatorCount;
    private Image spriteImage; // The image of the character
    private Image playerSprite;

    public static void main(String[] args) {
        // 5x5 grid representing the game grid for testing
        int[][] grid = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        // New player spawning in at position (2, 2)
        Player player = new Player(2, 2);

        // Print initial player position
        System.out.println("Initial position: (" + player.getX() + ", " + player.getY() + ")");

        String[] directions = {"up", "right", "down", "left"};

        for (String direction : directions) {
            boolean moved = false;

            switch (direction) {
                case "up":
                    moved = player.moveUp(grid); // Try to move the player UP in the grid
                    break;
                case "right":
                    moved = player.moveRight(grid); // Try to move the player RIGHT in the grid
                    break;
                case "down":
                    moved = player.moveDown(grid); // Try to move the player DOWN in the grid
                    break;
                case "left":
                    moved = player.moveLeft(grid); // Try to move the player LEFT in the grid
                    break;
            }

            // If the player successfully moved, print the new position
            if (moved) {
                System.out.println("Moved " + direction + ". New position: (" + player.getX() + ", " + player.getY() + ")");
            }
            // If the player cannot move to the new position, print erro message
            else {
                System.out.println("Cannot move " + direction + ".");
            }
        }
    }

    public Player(int startX, int startY) {
        this.x = startX; // startX and startY are the initial starting points for the player
        this.y = startY;
        this.explosiveCount = 3; // Starting explosive count
        this.detonatorCount = 3; // Starting detonator count

        spriteImage = GameEngine.loadImage("Images/Player/Character.png");
        playerSprite = GameEngine.subImage(spriteImage, 0, 0, 32 , 32);
    }

    public void drawPlayer(GameEngine g) {
        g.drawImage(playerSprite, x, y);
    }

    // Resets the explosives back to their starting values
    public void resetExplosives() {
        explosiveCount = 3;
        detonatorCount = 3;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getExplosiveCount() {
        return explosiveCount;
    }

    public int getDetonatorCount() {
        return detonatorCount;
    }

    /**********************************************************************************
     ************* Functions to check the explosive count for the player *************
     * Return TRUE if the count is ABOVE ZERO and DECREMENT the count
     * Otherwise, return FALSE
     */
    public boolean consumeExplosive() {
        if (explosiveCount > 0) {
            explosiveCount--;
            return true;
        }
        return false;
    }

    // If player has more than 0 detonators, consume one
    public boolean consumeDetonator() {
        if (detonatorCount > 0) {
            detonatorCount--;
            return true;
        }
        return false;
    }

    /**********************************************************
    ************* Functions to move the character *************
    * If the cell is empty, move the player to the cell in the grid
    * The functions will return TRUE if the player moved
    * The functions will return FALSE if the player DID NOT move
     */

    public boolean moveUp(int[][] grid) {
        if (y - 1 >= 0 && grid[y - 1][x] == 0) {
            y--;
            return true;
        }
        return false;
    }

    public boolean moveDown(int[][] grid) {
        if (y + 1 < grid.length && grid[y + 1][x] == 0) {
            y++;
            return true;
        }
        return false;
    }

    public boolean moveLeft(int[][] grid) {
        if (x - 1 >= 0 && grid[y][x - 1] == 0) {
            x--;
            return true;
        }
        return false;
    }

    public boolean moveRight(int[][] grid) {
        if (x + 1 < grid[0].length && grid[y][x + 1] == 0) {
            x++;
            return true;
        }
        return false;
    }
}

