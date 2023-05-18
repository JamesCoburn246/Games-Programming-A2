package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class Explosive extends SpriteProp {

    private static Image[] fuseAnimation;
    private static Image[] explosionAnimation;
    private GameEngine engine;
    private int initialFuse;
    private int fuseRemaining;
    private int damage;
    private int range;
    private ExplosiveState state = ExplosiveState.IDLE;

    static {
        // TODO Load animations.
        fuseAnimation = null;
        explosionAnimation = null;
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
