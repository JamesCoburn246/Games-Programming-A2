package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;
import nz.ac.massey.games_programming.util.CardinalDirection;

import java.awt.*;

public class Player extends SpriteProp {
    private int x, y; // x and y co-ordinate of the player
    private final CardinalDirection direction;
    private final Grid grid;
    private int explosiveCount;
    private int detonatorCount;
    private int health; // Could be changed to a boolean if we don't want multiple lives

    // The image of the character
    private static final Image spriteImage = GameEngine.loadImage("Images/Player/Character.png");
    private static final Image playerSprite = GameEngine.subImage(spriteImage, 0, 0, 32 , 32);

    public Player(int x, int y, Grid.Cell cell, Grid grid, CardinalDirection direction, int health) {
        super(PropType.PLAYER, x, y, cell);
        this.grid = grid;
        this.direction = direction;
        this.health = health;
        this.setSprite(playerSprite);

        this.explosiveCount = 6;
        this.detonatorCount = 2;
        this.x = x;
        this.y = y;
    }

    @Override
    public void outOfFrames() {
        super.outOfFrames();

        Grid.Cell nextCell = getNextCell();
        Prop nextProp = nextCell.getContents();

        // If the tile is empty, move the player to that tile
        if (nextProp instanceof Nothing) {
            getNextCell().setContents(new Player(nextProp.getX(), nextProp.getY(), nextCell, grid, direction, health));
        }
    }

    private Grid.Cell getNextCell() {
        return switch (this.direction) {
            case UP -> grid.getCell(getX(), (getY() - 1));
            case DOWN -> grid.getCell(getX(), (getY() + 1));
            case LEFT -> grid.getCell((getX() - 1), getY());
            default -> grid.getCell((getX() + 1), getY());
        };
    }

    // Resets the explosives back to their starting values
    public void resetExplosives() {
        explosiveCount = 6;
        detonatorCount = 2;
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

    public boolean moveUp(Grid grid) {
        if (y - 1 >= 0 && grid.getCell(x,y-1).getContents() instanceof Nothing) {
            y--;
            return true;
        }
        return false;
    }

    public boolean moveDown(Grid grid) {
        if (y + 1 < grid.ROWS && grid.getCell(x,y+1).getContents() instanceof Nothing) {
            y++;
            return true;
        }
        return false;
    }

    public boolean moveLeft(Grid grid) {
        if (x - 1 >= 0 && grid.getCell(x-1,y).getContents() instanceof Nothing) {
            x--;
            return true;
        }
        return false;
    }

    public boolean moveRight(Grid grid) {
        // If player can move, move character
        if (x + 1 < grid.COLS && grid.getCell(x+1,y).getContents() instanceof Nothing) {
            x++;
            return true;
        }
        return false;
    }

    public int getExplosiveCount() {
        return explosiveCount;
    }

    public int getDetonatorCount() {
        return detonatorCount;
    }

    public void draw(GameEngine engine) {
        draw(engine, x*32, y*32, 32, 32);
    }
}

