package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;
import nz.ac.massey.games_programming.util.CardinalDirection;

import java.awt.*;

public class Player extends SpriteProp {
    private static Player playerInstance = null;
    private int x, y; // x and y co-ordinate of the player
    private final CardinalDirection direction;
    private final Grid grid;
    private int explosiveCount;
    private int detonatorCount;
    private int health; // Could be changed to a boolean if we don't want multiple lives

    // The image of the character
    private static final Image spriteImage = GameEngine.loadImage("Images/Player/Character.png");
    private static final Image playerSprite = GameEngine.subImage(spriteImage, 0, 0, 32 , 32);

    // Get player cell co-ordinates
    @Override
    public int getX() { return x; }
    @Override
    public int getY() { return y; }

    public int getExplosiveCount() { return explosiveCount; }
    public int getDetonatorCount() { return detonatorCount; }

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

    public static Player getInstance(int x, int y, Grid.Cell cell, Grid grid, CardinalDirection direction, int health) {
        if (playerInstance == null){
            playerInstance = new Player(x, y, cell, grid, direction, health);
        }
        return playerInstance;
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


    /************ Functions to move the character *************
    // If the cell is empty, move the player to the cell in the grid
    //*/

    public void moveUp(Grid grid) {
        // If the cell above the character is empty or a collectable, move character 1 cell up
        if (y-1 >= 0 && (grid.getCell(x,y-1).getContents() instanceof Nothing || grid.getCell(x,y-1).getContents() instanceof Collectable)) {
            y--;
        }
    }

    public void moveDown(Grid grid) {
        // If the cell below the character is empty or a collectable, move character 1 cell down
        if (y+1 < grid.ROWS && (grid.getCell(x,y+1).getContents() instanceof Nothing || grid.getCell(x,y+1).getContents() instanceof Collectable)) {
            y++;
        }
    }

    public void moveLeft(Grid grid) {
        // If the cell to the left is empty or a collectable, move character 1 cell to the left
        if (x-1 >= 0 && (grid.getCell(x-1,y).getContents() instanceof Nothing || grid.getCell(x-1,y).getContents() instanceof Collectable)) {
            x--;
        }
    }

    public void moveRight(Grid grid) {
        // If the cell to the right is empty or a collectable, move character 1 cell to the right
        if (x+1 < grid.COLS && (grid.getCell(x+1,y).getContents() instanceof Nothing || grid.getCell(x+1,y).getContents() instanceof Collectable)) {
            x++;
        }
    }

    // Return true if player is standing on top of a collectable
    public boolean isOnCollectable(Grid grid) {
        if (grid.getCell(x,y).getContents() instanceof Collectable) {
            System.out.println("Picked up collectable!");
            return true;
        }
        return false;
    }
    // Decrease explosiveCount by 1
    public void bombPlaced() {
        explosiveCount--;
    }

    // Decrease detonatorCount by 1
    public void detonatorUsed() {
        detonatorCount--;
    }

    public void addExplosive() {
        this.explosiveCount++;
    }

    public void addDetonator() {
        this.detonatorCount++;
    }

    public void setExplosiveCount(int explosiveCount) {
        this.explosiveCount = explosiveCount;
    }

    public void setDetonatorCount(int detonatorCount) {
        this.detonatorCount = detonatorCount;
    }

    public void draw(GameEngine engine) {
        draw(engine, x*32, y*32, 32, 32);
    }
}

