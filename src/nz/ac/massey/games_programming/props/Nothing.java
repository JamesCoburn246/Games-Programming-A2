package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;

public class Nothing extends Prop {

    public Nothing(int x, int y, Grid.Cell parent) {
        super(PropType.NONE, x, y, parent);
    }

    // Draw nothing.
    @Override
    public void draw(GameEngine engine, int x_offset, int y_offset, int x_width, int y_height) {
        // This method has intentionally been left empty.
    }
}
