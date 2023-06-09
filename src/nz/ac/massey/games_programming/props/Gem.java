package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;
import nz.ac.massey.games_programming.Main;

import java.awt.*;

public class Gem extends Collectable {
    private static final Image GEM;
    private static final GameEngine.AudioClip pickup;
    private final Player player;
    private Main game;

    static {
        Image gemSprite = GameEngine.loadImage("Images/Objects/Gem.png");
        GEM = GameEngine.subImage(gemSprite, 0, 0, 32, 32);
        pickup = GameEngine.loadAudio("PickUp.wav");
    }
    public Gem(int x, int y, Grid.Cell cell) {
        super(PropType.GEM, x, y, cell);
        this.setSprite(GEM);
        this.player = Player.getInstance(0, 0, null, null, null, 0);
    }

    @Override
    public void onCollection() {
        // Play the sound of collecting the gem
        game.playAudio(pickup, 10);
    }
}
