// Originally used in A1 by James Coburn.

package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public abstract class SpriteProp extends Prop implements Animatable {

    protected Grid.Cell cell;
    private Image sprite;
    private Image[] sprites;
    private int spriteIndex;

    public SpriteProp(PropType type, int x, int y, Grid.Cell cell) {
        super(type, x, y);
        this.cell = cell;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    @Override
    public void setSprites(Image[] sprites) {
        this.sprites = sprites;
        resetFrames();
    }

    @Override
    public void resetFrames() {
        this.spriteIndex = 0;
    }

    @Override
    public void nextFrame() {
        if (++spriteIndex < sprites.length) {
            outOfFrames();
        }
    }

    public void draw(GameEngine engine, int x_offset, int y_offset, int x_width, int y_width) {
        if (sprites != null) {
            engine.drawImage(sprites[spriteIndex], x_offset, y_offset);
        } else if (sprite != null) {
            engine.drawImage(sprite, x_offset, y_offset);
        }
    }
}
