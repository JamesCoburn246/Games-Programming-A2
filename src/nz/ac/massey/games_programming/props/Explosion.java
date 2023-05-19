package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;
import nz.ac.massey.games_programming.util.CardinalDirection;

import java.awt.*;

public class Explosion extends SpriteProp {

    // How long the animations should play, measured in seconds.
    private static final float ANIMATION_DURATION = 1.0F;
    // Frame dimensions in the sprite sheet, measured in pixels.
    private static final int FRAME_WIDTH = 32, FRAME_HEIGHT = 32;
    // Frame count variables.
    private static final int FRAME_COUNT = 6;

    private static final Image[] blastAnimation = new Image[FRAME_COUNT];

    static {
        Image sprites = GameEngine.loadImage("Images/Objects/Fire");
        for (int i = 0; i < FRAME_COUNT; i++) {
            blastAnimation[i] = GameEngine.subImage(sprites, FRAME_WIDTH * i, 0, FRAME_WIDTH, FRAME_HEIGHT);
        }
    }

    private final Grid grid;
    private final CardinalDirection direction;
    private final int damage;
    private final int range;

    public Explosion(int x, int y, Grid.Cell cell, Grid grid, CardinalDirection direction, int damage, int range) {
        super(PropType.EXPLOSION, x, y, cell);
        this.grid = grid;
        this.direction = direction;
        this.damage = damage;
        this.range = range;
        this.setSprites(blastAnimation);
        this.durationPerFrame = ANIMATION_DURATION / FRAME_COUNT;
    }

    @Override
    public void outOfFrames() {
        // Trigger the self-destruct.
        super.outOfFrames();

        // Check if this is the last explosion in a stack.
        if (range == 0)
            return;

        // Get the next cell and check what is there.
        Grid.Cell nextCell = getNextCell();
        Prop nextProp = nextCell.getContents();
        // Create a new explosion.
        if (nextProp instanceof Nothing) {
            getNextCell().setContents(new Explosion(nextProp.getX(), nextProp.getY(), nextCell, grid, direction, damage, (range - 1)));
        // Damage a breakable.
        } else if (nextProp instanceof Breakable breakable) {
            breakable.dealDamage(damage);
        // Trigger an explosive.
        } else if (nextProp instanceof Explosive explosive) {
            explosive.lightFuse();
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
}
