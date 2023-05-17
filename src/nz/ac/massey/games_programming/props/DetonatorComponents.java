package nz.ac.massey.games_programming.props;

import java.awt.*;
import java.util.ArrayList;

public class DetonatorComponents extends Collectable {
    public DetonatorComponents(PropType type, int x, int y, Image sprite, ArrayList<SpriteProp> container) {
        super(type, x, y, sprite, container);
    }
}
