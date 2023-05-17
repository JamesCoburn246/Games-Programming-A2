package nz.ac.massey.games_programming.props;

import java.awt.*;
import java.util.ArrayList;

public class Loot extends Collectable {
    public Loot(PropType type, int x, int y, ArrayList<SpriteProp> container) {
        super(type, x, y, container);
    }
}
