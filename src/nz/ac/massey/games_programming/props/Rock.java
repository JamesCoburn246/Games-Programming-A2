package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class Rock extends Breakable {

    private static final float ANIMATION_DURATION = 0.33F;
    private static final int FRAME_WIDTH = 32, FRAME_HEIGHT = 32;
    private static final int FRAME_COUNT = 3;
    private static final Image[] ROCK_CRACKING = new Image[FRAME_COUNT];
    private static final Image[] ROCK_EXPLOSION = new Image[FRAME_COUNT];

    private int frameCounter = 0;
    private static final int INITIAL_HEALTH = 3;
    private static final Image ROCK;

    static {
        Image rockBreakingSprite = GameEngine.loadImage("Images/Objects/RockBreaking.png");
        Image rockExplosionAnimaton = GameEngine.loadImage("Images/Objects/RockExplosion.png");
        for (int i = 0; i < FRAME_COUNT; i++) {
            ROCK_CRACKING[i] = GameEngine.subImage(rockBreakingSprite, FRAME_WIDTH * i, 0, FRAME_WIDTH, FRAME_HEIGHT);
            ROCK_EXPLOSION[i] = GameEngine.subImage(rockExplosionAnimaton, FRAME_WIDTH * i, 0, FRAME_WIDTH, FRAME_HEIGHT);
        }
        Image sprites = GameEngine.loadImage("Images/Objects/RockExplosion.png");
        ROCK = GameEngine.subImage(sprites, 0, 0, 32, 32);
    }

    public Rock(int x, int y, Grid.Cell cell) {
        super(PropType.ROCK, x, y, cell, FRAME_COUNT);
        this.setSprite(ROCK_CRACKING[0]);
        this.durationPerFrame = ANIMATION_DURATION / FRAME_COUNT;
    }

    @Override
    public boolean dealDamage(int damage) {
        boolean isDestroyed = super.dealDamage(damage);
        if (!isDestroyed) {
            frameCounter += 2;
            if (frameCounter < FRAME_COUNT) {
                this.setSprite(ROCK_CRACKING[frameCounter]);
            }
        }
        else {
            this.destroy();
        }
        return isDestroyed;
    }

    @Override
    public void outOfFrames() {
        this.destroy();
        this.setSprite(ROCK);
    }
}
