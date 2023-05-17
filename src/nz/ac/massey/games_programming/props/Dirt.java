package nz.ac.massey.games_programming.props;

import java.awt.*;
import java.util.ArrayList;

public class Dirt extends Breakable {
    public Dirt(PropType type, int x, int y, Image sprite, int health, ArrayList<SpriteProp> container) {
        super(type, x, y, sprite, health, container);
    }
}
