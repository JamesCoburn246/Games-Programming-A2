package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.Grid;

public class Dirt extends Breakable {
    public Dirt(int x, int y, int health, Grid.Cell cell) {
        super(PropType.DIRT, x, y, health, cell);
    }
}
