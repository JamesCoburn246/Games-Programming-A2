package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.Grid;

public class DetonatorComponents extends Collectable {
    public DetonatorComponents(PropType type, int x, int y, Grid.Cell cell) {
        super(type, x, y, cell);
    }

    @Override
    public void onCollection() {

    }
}
