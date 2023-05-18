package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class Stone extends Breakable {

    public Stone(int x, int y, int health, Grid.Cell cell) {
        super(PropType.ROCK, x, y, health, cell);
    }

}
