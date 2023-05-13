// Originally used in A1 by James Coburn.

package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;

import java.awt.*;

public abstract class SpriteProp extends Prop {

    private Image sprite;

    public SpriteProp(PropType type, int x, int y, Image sprite) {
        super(type, x, y);
        setSprite(sprite);
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public void draw(GameEngine engine, int x_offset, int y_offset, int x_width, int y_width) {
        if (sprite != null) {
            engine.drawImage(sprite, x_offset, y_offset);
        }
    }
}
