package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;
import nz.ac.massey.games_programming.Main;

import java.awt.*;

public class DetonatorCrate extends Collectable {
    private static final Image DETONATORCRATE;
    private static final GameEngine.AudioClip pickup;
    private final Player player;
    private Main game;

    static {
        Image detonatorSprite = GameEngine.loadImage("Images/Objects/DetonatorCrate.png");
        DETONATORCRATE = GameEngine.subImage(detonatorSprite, 0, 0, 32, 32);
        pickup = GameEngine.loadAudio("PickUp.wav");
    }
    public DetonatorCrate(int x, int y, Grid.Cell cell) {
        super(PropType.DETONATORCRATE, x, y, cell);
        this.setSprite(DETONATORCRATE);
        this.player = Player.getInstance(0, 0, null, null, null, 0);
    }

    @Override
    public void onCollection() {
        // Add 1 detonator to detonator count
        player.addDetonator();

        // Play the sound of collecting the detonator crate
        game.playAudio(pickup, 10);
    }
}
