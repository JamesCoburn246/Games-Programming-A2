// Originally used in A1 by James Coburn.

package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;

public abstract class Prop {

    private final PropType TYPE;
    private final int GRID_X, GRID_Y;

    public Prop(PropType type, int x, int y) {
        this.TYPE = type;
        this.GRID_X = x;
        this.GRID_Y = y;
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

    public abstract void draw(GameEngine engine, int x_offset, int y_offset, int x_width, int y_width);
}
