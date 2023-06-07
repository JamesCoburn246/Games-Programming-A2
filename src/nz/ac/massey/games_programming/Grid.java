package nz.ac.massey.games_programming;

import nz.ac.massey.games_programming.props.Animatable;
import nz.ac.massey.games_programming.props.Nothing;
import nz.ac.massey.games_programming.props.Prop;

import java.awt.*;

public class Grid {
    public final int COLS, ROWS;
    private final Cell[][] cells;

    public Grid(int width, int height) {
        COLS = width;
        ROWS = height;
        cells = new Cell[COLS][ROWS];
        reset();
    }

    public void reset() {
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS; row++) {
                cells[col][row] = new Cell();
            }
        }
    }

    public Cell getCell(int col, int row) {
        return cells[col][row];
    }

    /**
     * Update physics etc. for all cells and their props.
     * @param dt the time passed since this was last called, measured in seconds.
     */
    public void updateAll(double dt) {
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS; row++) {
                cells[col][row].updateContents(dt);
            }
        }
    }

    /**
     * Update graphics for all cells and their props.
     * @param engine the engine used to draw.
     */
    public void drawAll(GameEngine engine) {
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS; row++) {
                int x_offset = col * Cell.CELL_WIDTH;
                int y_offset = row * Cell.CELL_HEIGHT;

                // Checks if the cell is on the edge, used to draw the edge tiles
                boolean isEdge = (col == 0 || col == COLS - 1 || row == 0 || row == ROWS - 1);
                cells[col][row].drawContents(engine, x_offset, y_offset, isEdge);
            }
        }
    }

    public int determineScreenWidth() {
        return this.COLS * Cell.CELL_WIDTH;
    }

    public int determineScreenHeight() {
        return this.ROWS * Cell.CELL_HEIGHT;
    }

    public int calculateGrid(int winWidth, int winHeight, int pointX, int pointY) {

        int numRows = winHeight / Grid.Cell.CELL_HEIGHT;
        int numCols = winWidth / Grid.Cell.CELL_WIDTH;

        int cellIndex = -1;  // Default value if Given point is outside boundary

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int x = col * Grid.Cell.CELL_WIDTH;
                int y = row * Grid.Cell.CELL_HEIGHT;

                // Check if the point is within the current cell
                if (pointX >= x && pointX < x + Grid.Cell.CELL_WIDTH && pointY >= y && pointY < y + Grid.Cell.CELL_HEIGHT) {
                    cellIndex = row * numCols + col;
                    break;
                }
            }
            if (cellIndex != -1) {
                break;
            }
        }
        return cellIndex;
    }



    public static class Cell {

        public static final int CELL_WIDTH = 32, CELL_HEIGHT = 32;

        private Prop prop;
        private static final Image GREYTILE, BLACKTILE, DARKGREYTILE;

        public Cell() {
            this(null);
        }

        public Cell(Prop prop) {
            setContents(prop);
        }

        public Prop getContents() {
            return this.prop;
        }

        public void setContents(Prop newContents) {
            this.prop = newContents;
        }

        static {
            Image tileSprites = GameEngine.loadImage("Images/Tiles/Tile-Sheet.png");
            GREYTILE = GameEngine.subImage(tileSprites, 0, 0, 32, 32);
            BLACKTILE = GameEngine.subImage(tileSprites, 32 * 4, 0, 32, 32);
            DARKGREYTILE = GameEngine.subImage(tileSprites, 32, 0, 32, 32);
        }

        private void updateContents(double dt) {
            // If the prop supports animations, update the animations.
            if (this.prop instanceof Animatable) {
                ((Animatable) this.prop).update(dt);
            }
        }

        private void drawContents(GameEngine engine, int x_offset, int y_offset, boolean isEdge) {
            // Draw the cell background.
            engine.drawImage(GREYTILE, x_offset, y_offset);
            engine.drawText(x_offset, (y_offset + CELL_WIDTH), "C");

            // Draw the prop.
            if (!(this.prop instanceof Nothing)) {
                this.prop.draw(engine, x_offset, y_offset, Cell.CELL_WIDTH, Cell.CELL_HEIGHT);
            }
        }
    }
}
