package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.Grid;

import java.awt.*;
import java.util.ArrayList;

public class Stone extends Breakable {

    public Stone(PropType type, int x, int y, Image sprite, int health, Grid.Cell cell) {
        super(type, x, y, sprite, health, cell);
    }

}
