package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class Rock extends Breakable {

    private static final float ANIMATION_DURATION = 0.33F;
    private static final int FRAME_WIDTH = 32, FRAME_HEIGHT = 32;
    private static final int FRAME_COUNT = 3;
    private static final Image[] ROCK = new Image[FRAME_COUNT];

    private int frameCounter = 0;

    static {
        Image rockBreakingSprite = GameEngine.loadImage("Images/Objects/RockBreaking.png");
        Image rockExplosionAnimaton = GameEngine.loadImage("Images/Objects/RockExplosion.png");
        for (int i = 0; i < FRAME_COUNT; i++) {
            ROCK[i] = GameEngine.subImage(rockBreakingSprite, FRAME_WIDTH * i, 0, FRAME_WIDTH, FRAME_HEIGHT);
        }
    }

    public Rock(int x, int y, Grid.Cell cell) {
        super(PropType.ROCK, x, y, cell, FRAME_COUNT);
        this.setSprite(ROCK[0]);
        this.durationPerFrame = ANIMATION_DURATION / FRAME_COUNT;
    }

    @Override
    public boolean dealDamage(int damage) {
        boolean isDestroyed = super.dealDamage(damage);
        if (!isDestroyed) {
            frameCounter++;
            if (frameCounter < FRAME_COUNT) {
                this.setSprite(ROCK[frameCounter]);
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
    }
}


