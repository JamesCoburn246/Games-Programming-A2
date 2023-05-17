package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;

import java.awt.*;

public interface Animatable {
    void setSprites(Image[] sprite);

    void nextFrame();

    void outOfFrames();

    void draw(GameEngine engine, int x_offset, int y_offset, int x_width, int y_width);
}
