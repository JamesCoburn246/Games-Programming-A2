package nz.ac.massey.games_programming;

import nz.ac.massey.games_programming.props.Dirt;
import nz.ac.massey.games_programming.props.Explosive;
import nz.ac.massey.games_programming.props.Stone;

public class LevelManager {

    public static final int GRID_WIDTH = 20;
    public static final int GRID_HEIGHT = 20;

    private static final char[][] levelOneTemplate = {
            {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', 'E', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', 'D', 'D', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', 'D', 'D', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', 'D', 'D', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'D', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'D', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'D', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'E', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'}
    };

    private LevelManager() {
    }

    public static void loadLevelIntoGrid(Grid grid, GameLevel level) {
        switch (level) {
            // Remove all items on the board.
            case CLEAR -> {
                for (int col = 0; col < grid.COLS; col++) {
                    for (int row = 0; row < grid.ROWS; row++) {
                        grid.getCell(col, row).clearContents();
                    }
                }
            }
            case ONE -> {
                for (int col = 0; col < grid.COLS; col++) {
                    for (int row = 0; row < grid.ROWS; row++) {
                        loadCellFromTemplate(grid, levelOneTemplate, col, row);
                    }
                }
            }
        }
    }

    private static void loadCellFromTemplate(Grid grid, char[][] level, int col, int row) {
        Grid.Cell cell = grid.getCell(col, row);
        switch (level[col][row]) {
            case 'R' -> cell.setContents(new Stone(col, row, cell));
            case 'D' -> cell.setContents(new Dirt(col, row, cell));
            case 'E' -> cell.setContents(new Explosive(col, row, cell));
            default -> cell.clearContents();
        }
    }

    public enum GameLevel {
        CLEAR, ONE,
    }
}
