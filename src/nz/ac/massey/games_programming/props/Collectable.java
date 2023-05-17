package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class Collectable extends SpriteProp {

    public Collectable(PropType type, int x, int y, Image sprite, Grid.Cell cell) {
        super(type, x, y, sprite, cell);
    }

    @Override
    public void outOfFrames() {
        resetFrames();
    }
}
