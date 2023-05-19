package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;
import nz.ac.massey.games_programming.util.CardinalDirection;

import java.awt.*;

public class Explosive extends SpriteProp {

    // How long the animations should play, measured in seconds.
    private static final float FUSE_ANIMATION_DURATION = 1.0F, EXPLOSION_ANIMATION_DURATION = 0.5F;
    // Frame dimensions in the sprite sheet, measured in pixels.
    private static final int FRAME_WIDTH = 32, FRAME_HEIGHT = 32;
    // Frame count variables.
    private static final int FUSE_FRAME_COUNT = 6, EXPLOSION_FRAME_COUNT = 8;
    // Variables used to store animations in.
    private static final Image[] fuseAnimation = new Image[FUSE_FRAME_COUNT];
    private static final Image[] explosionAnimation = new Image[EXPLOSION_FRAME_COUNT];

    // How far in tiles should the explosion propagate. Possible to be improved later with gear, but is globally consistent.
    public static int explosionRange;
    // How much health to subtract from Breakable props that would be hit by an explosion.
    private static int explosionDamage;

    static {
        // Load animations.
        Image sprites = GameEngine.loadImage("Images/Objects/BombAnimation.png");
        for (int i = 0; i < FUSE_FRAME_COUNT; i++) {
            fuseAnimation[i] = GameEngine.subImage(sprites, FRAME_WIDTH * i, 0, FRAME_WIDTH, FRAME_HEIGHT);
        }
        for (int i = 0; i < EXPLOSION_FRAME_COUNT; i++) {
            explosionAnimation[i] = GameEngine.subImage(sprites, (FRAME_WIDTH * FUSE_FRAME_COUNT) + (FRAME_WIDTH * i), 0, FRAME_WIDTH, FRAME_HEIGHT);
        }
    }

    private final Grid grid;
    private int fuseRemaining;
    private ExplosiveState state = ExplosiveState.IDLE;

    public Explosive(int x, int y, Grid.Cell cell, Grid grid) {
        super(PropType.EXPLOSIVE, x, y, cell);
        this.setSprites(fuseAnimation);
        this.grid = grid;
    }

    @Override
    public void update(double dt) {
        // Prevent animations from playing while the explosive is idle.
        if (state != ExplosiveState.IDLE) {
            super.update(dt);
        }
    }

    @Override
    public void outOfFrames() {
        switch (state) {
            // This should not happen.
            case IDLE -> {
                System.out.println("An error has occurred! An idle explosive should never run out of frames!");
                System.exit(1);
            }
            // Change to the explosion animation.
            case COUNTDOWN -> {
                this.setSprites(explosionAnimation);
                this.spriteIndex = 0;
                this.durationPerFrame = EXPLOSION_ANIMATION_DURATION / EXPLOSION_FRAME_COUNT;
                this.triggerExplosion();
            }
            // Remove reference to self, effectively self-destruct.
            case EXPLOSION -> {
                destroy();
            }
        }
    }

    public void lightFuse() {
        this.state = ExplosiveState.COUNTDOWN;

        // Calculate the animation speed for the fuse timing.
        this.durationPerFrame = (double) FUSE_ANIMATION_DURATION / FUSE_FRAME_COUNT;
    }

    private void triggerExplosion() {
        this.state = ExplosiveState.EXPLOSION;
        attemptToSpawnExplosion(CardinalDirection.UP);
        attemptToSpawnExplosion(CardinalDirection.DOWN);
        attemptToSpawnExplosion(CardinalDirection.LEFT);
        attemptToSpawnExplosion(CardinalDirection.RIGHT);
    }

    private void attemptToSpawnExplosion(CardinalDirection cd) {
        int x, y;
        switch (cd) {
            case UP -> {
                x = getX();
                y = (getY() - 1);
            }
            case DOWN -> {
                x = getX();
                y = (getY() + 1);
            }
            case LEFT -> {
                x = (getX() - 1);
                y = getY();
            }
            default -> {
                x = (getX() + 1);
                y = getY();
            }
        }
        Grid.Cell target = grid.getCell(x, y);
        if (target.getContents() instanceof Nothing) {
            target.setContents(new Explosion(x, y, target, grid, cd, explosionDamage, explosionRange));
        } else if (target.getContents() instanceof Breakable breakable) {
            breakable.dealDamage(explosionDamage);
        }
    }

    private enum ExplosiveState {
        IDLE, COUNTDOWN, EXPLOSION
    }
}
