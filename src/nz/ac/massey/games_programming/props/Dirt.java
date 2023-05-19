package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.Grid;

public class Dirt extends Breakable {

    private static final int INITIAL_HEALTH = 3;
    public Dirt(int x, int y, Grid.Cell cell) {
        super(PropType.DIRT, x, y, cell, INITIAL_HEALTH);
    }
}
