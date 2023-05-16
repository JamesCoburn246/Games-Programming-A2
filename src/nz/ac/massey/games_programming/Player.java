package nz.ac.massey.games_programming;

public class Player {
    private int x, y; // x and y co-ordinate of the player

    public static void main(String[] args) {
        
    }

    public Player(int startX, int startY) {
        this.x = startX; // startX and startY are the initial starting points for the player
        this.y = startY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

