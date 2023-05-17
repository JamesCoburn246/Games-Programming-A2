package nz.ac.massey.games_programming;

import nz.ac.massey.games_programming.props.Prop;

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

    public void drawAll(GameEngine engine) {
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS; row++) {
                int x_offset = COLS * Cell.CELL_WIDTH;
                int y_offset = ROWS * Cell.CELL_HEIGHT;
                cells[col][row].drawContents(engine, x_offset, y_offset, Cell.CELL_WIDTH, Cell.CELL_HEIGHT);
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

        public void drawContents(GameEngine engine, int x_offset, int y_offset, int x_width, int y_width) {
            if (this.prop != null) {
                this.prop.draw(engine, x_offset, y_offset, x_width, y_width);
            }
        }

        public void clearContents() {
            this.prop = null;
        }
    }
}
