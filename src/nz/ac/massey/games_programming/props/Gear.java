package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.Grid;

public abstract class Gear extends Collectable {
    public Gear(PropType type, int x, int y, Grid.Cell cell) {
        super(type, x, y, cell);
    }
}
