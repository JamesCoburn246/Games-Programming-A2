// Originally used in A1 by James Coburn.

package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;

import java.awt.*;
import java.util.ArrayList;

public abstract class SpriteProp extends Prop implements Animatable {

    private Image sprite;
    private Image[] sprites;
    private int spriteIndex;

    protected ArrayList<SpriteProp> container;

    public SpriteProp(PropType type, int x, int y, Image sprite, ArrayList<SpriteProp> container) {
        super(type, x, y);
        this.container = container;
        setSprite(sprite);
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    @Override
    public void setSprites(Image[] sprites) {
        this.sprites = sprites;
        resetFrames();
    }

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
