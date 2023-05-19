package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.Grid;
import nz.ac.massey.games_programming.util.CardinalDirection;

public class Explosion extends SpriteProp {

    private final Grid grid;
    private final CardinalDirection direction;
    private final int damage;
    private final int range;

    public Explosion(int x, int y, Grid.Cell cell, Grid grid, CardinalDirection direction, int damage, int range) {
        super(PropType.EXPLOSION, x, y, cell);
        this.grid = grid;
        this.direction = direction;
        this.damage = damage;
        this.range = range;
    }

    @Override
    public void outOfFrames() {
        // Trigger the self-destruct.
        super.outOfFrames();

        // Check if this is the last explosion in a stack.
        if (range == 0)
            return;

        // Get the next cell and check what is there.
        Grid.Cell nextCell = getNextCell();
        Prop nextProp = nextCell.getContents();
        // Create a new explosion.
        if (nextProp instanceof Nothing) {
            getNextCell().setContents(new Explosion(nextProp.getX(), nextProp.getY(), nextCell, grid, direction, damage, (range - 1)));
        // Damage a breakable.
        } else if (nextProp instanceof Breakable breakable) {
            breakable.dealDamage(damage);
        // Trigger an explosive.
        } else if (nextProp instanceof Explosive explosive) {
            explosive.lightFuse();
        }
    }

    private Grid.Cell getNextCell() {
        return switch (this.direction) {
            case UP -> grid.getCell(getX(), (getY() - 1));
            case DOWN -> grid.getCell(getX(), (getY() + 1));
            case LEFT -> grid.getCell((getX() - 1), getY());
            default -> grid.getCell((getX() + 1), getY());
        };
    }
}
