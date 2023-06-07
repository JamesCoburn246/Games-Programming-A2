package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class BombCrate extends Collectable {
    private static final Image BOMBCRATE;
    private final Player player;

    static {
        Image bombSprite = GameEngine.loadImage("Images/Objects/BombCrate.png");
        BOMBCRATE = GameEngine.subImage(bombSprite, 0, 0, 32, 32);
    }
    public BombCrate(PropType type, int x, int y, Grid.Cell cell) {
        super(type, x, y, cell);
        this.setSprite(BOMBCRATE);
        this.player = Player.getInstance(0, 0, null, null, null, 0);
    }

    // TO-DO: This needs to be overridden once the abstract onCollection is added
    public void onCollection() {
        // Add a bomb to the explosive count
        player.addExplosive();

        // Play the sound of collecting the bomb crate
    }
}
