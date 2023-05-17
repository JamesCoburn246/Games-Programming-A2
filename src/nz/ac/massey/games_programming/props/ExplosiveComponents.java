package nz.ac.massey.games_programming.props;

import java.awt.*;
import java.util.ArrayList;

public class ExplosiveComponents extends Collectable {
    public ExplosiveComponents(PropType type, int x, int y, ArrayList<SpriteProp> container) {
        super(type, x, y, container);
    }
}
