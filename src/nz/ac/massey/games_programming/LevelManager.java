package nz.ac.massey.games_programming;

import nz.ac.massey.games_programming.props.*;

public class LevelManager {

    public static final int GRID_WIDTH = 20;
    public static final int GRID_HEIGHT = 20;

    private static final char[][] levelOneTemplate = {
            {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'},
            {'R', 'G', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'G', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', 'T', '_', '_', '_', '_', '_', 'G', '_', '_', '_', '_', '_', 'T', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'G', '_', 'R'},
            {'R', '_', '_', '_', 'D', 'D', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', 'D', 'D', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', 'D', 'D', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', 'B', '_', '_', '_', 'G', 'G', '_', '_', '_', '_', '_', '_', 'G', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'G', 'D', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', 'T', '_', '_', '_', '_', '_', '_', 'G', 'D', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'D', 'G', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', 'B', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'B', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
            {'R', '_', '_', '_', '_', '_', '_', '_', '_', 'G', '_', '_', '_', '_', '_', '_', '_', '_', '_', 'R'},
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
                        Grid.Cell cell = grid.getCell(col, row);
                        cell.setContents(new Nothing(col, row, cell));
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
            case 'R' -> cell.setContents(new Rock(col, row, cell));
            case 'D' -> cell.setContents(new Dirt(col, row, cell));
            case 'E' -> cell.setContents(new Explosive(col, row, cell, grid));
            case 'B' -> cell.setContents(new BombCrate(col, row, cell));
            case 'T' -> cell.setContents(new DetonatorCrate(col, row, cell));
            case 'G' -> cell.setContents(new Gem(col, row, cell));
            default -> cell.setContents(new Nothing(col, row, cell));
        }
    }

    public enum GameLevel {
        CLEAR, ONE,
    }
}
