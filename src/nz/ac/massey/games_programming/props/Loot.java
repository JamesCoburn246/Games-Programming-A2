package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class Loot extends Collectable {
    public Loot(PropType type, int x, int y, Image sprite, Grid.Cell cell) {
        super(type, x, y, sprite, cell);
    }
}
