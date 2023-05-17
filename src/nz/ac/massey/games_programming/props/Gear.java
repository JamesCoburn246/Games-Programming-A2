package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class Gear extends Collectable {
    public Gear(PropType type, int x, int y, Image sprite, Grid.Cell cell) {
        super(type, x, y, sprite, cell);
    }
}
