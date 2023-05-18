package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class Explosive extends SpriteProp {

    private static final int FRAME_WIDTH = 32, FRAME_HEIGHT = 32, FUSE_FRAME_COUNT = 6, EXPLOSION_FRAME_COUNT = 8;
    private static Image[] fuseAnimation = new Image[FUSE_FRAME_COUNT];
    private static Image[] explosionAnimation = new Image[EXPLOSION_FRAME_COUNT];
    private GameEngine engine;
    private int initialFuse;
    private int fuseRemaining;
    private int damage;
    private int range;
    private ExplosiveState state = ExplosiveState.IDLE;

    static {
        // Load animations.
        Image sprites = GameEngine.loadImage("Images/Objects/BombAnimation.png");
        for (int i = 0; i < FUSE_FRAME_COUNT; i++) {
            fuseAnimation[i] = GameEngine.subImage(sprites, FRAME_WIDTH * i, 0, FRAME_WIDTH, FRAME_HEIGHT);
        }
        for (int i = 0; i < FUSE_FRAME_COUNT; i++) {
            explosionAnimation[i] = GameEngine.subImage(sprites, FUSE_FRAME_COUNT + (FRAME_WIDTH * i), 0, FRAME_WIDTH, FRAME_HEIGHT);
        }
    }

    public Explosive(int x, int y, Grid.Cell cell) {
        super(PropType.EXPLOSIVE, x, y, cell);
        this.durationPerFrame = 0.35;
        this.setSprites(fuseAnimation);
    }

    @Override
    public void update(double dt) {
        // Prevent animations from playing while the explosive is idle.
        if (state != ExplosiveState.IDLE) {
            super.update(dt);
        }
    }

    public void lightFuse(int fuse, int damage, int range) {
        this.initialFuse = fuse;
        this.damage = damage;
        this.range = range;
        this.state = ExplosiveState.FUSE;
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
            case FUSE -> {
                this.setSprites(explosionAnimation);
                this.spriteIndex = 0;
                this.durationPerFrame = 0.35;
            }
            // Remove reference to self, effectively self-destruct.
            case EXPLODE -> super.cell.clearContents();
        }
    }

    private enum ExplosiveState {
        IDLE, FUSE, EXPLODE
    }
}
