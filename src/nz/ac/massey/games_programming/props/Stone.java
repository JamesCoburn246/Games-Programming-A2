package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class Stone extends Breakable {

    private static final int INITIAL_HEALTH = 3;

    public Stone(int x, int y, Grid.Cell cell) {
        super(PropType.ROCK, x, y, cell, INITIAL_HEALTH);
    }

}
