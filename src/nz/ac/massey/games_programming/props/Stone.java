package nz.ac.massey.games_programming.props;

import java.awt.*;
import java.util.ArrayList;

public class Stone extends Breakable {

    public Stone(PropType type, int x, int y, ArrayList<SpriteProp> container, int health) {
        super(type, x, y, container, health);
    }

}
