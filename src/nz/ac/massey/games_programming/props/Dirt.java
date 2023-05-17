package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class Dirt extends Breakable {
    public Dirt(PropType type, int x, int y, Image sprite, int health, Grid.Cell cell) {
        super(type, x, y, sprite, health, cell);
    }
}
