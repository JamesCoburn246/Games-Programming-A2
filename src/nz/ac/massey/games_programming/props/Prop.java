// Originally used in A1 by James Coburn.

package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;

public abstract class Prop {

    private final PropType TYPE;
    private final int GRID_X, GRID_Y;
    private final Grid.Cell parent;

    public Prop(PropType type, int x, int y, Grid.Cell parent) {
        this.TYPE = type;
        this.GRID_X = x;
        this.GRID_Y = y;
        this.parent = parent;
    }

    public PropType getType() {
        return TYPE;
    }

    public int getX() {
        return GRID_X;
    }

    public int getY() {
        return GRID_Y;
    }

    public abstract void draw(GameEngine engine, int x_offset, int y_offset, int x_width, int y_height);

    protected void destroy() {
        parent.setContents(new Nothing(getX(), getY(), this.parent));
    }
}
