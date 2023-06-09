package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;
import nz.ac.massey.games_programming.Main;

import java.awt.*;

public class BombCrate extends Collectable {
    private static final Image BOMBCRATE;
    private static final GameEngine.AudioClip pickup;
    private final Player player;
    private Main game;

    static {
        Image bombSprite = GameEngine.loadImage("Images/Objects/BombCrate.png");
        BOMBCRATE = GameEngine.subImage(bombSprite, 0, 0, 32, 32);
        pickup = GameEngine.loadAudio("PickUp.wav");
    }
    public BombCrate(int x, int y, Grid.Cell cell) {
        super(PropType.BOMBCRATE, x, y, cell);
        this.setSprite(BOMBCRATE);
        this.player = Player.getInstance(0, 0, null, null, null, 0);
    }

    @Override
    public void onCollection() {
        // Add a bomb to the explosive count
        player.addExplosive();

        // Play the sound of collecting the bomb crate
        game.startAudioLoop(pickup, 10);
    }
}
