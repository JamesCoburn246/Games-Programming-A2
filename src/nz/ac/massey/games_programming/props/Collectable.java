package nz.ac.massey.games_programming.props;

import java.awt.*;
import java.util.ArrayList;

public class Collectable extends SpriteProp {

    public Collectable(PropType type, int x, int y, ArrayList<SpriteProp> container) {
        super(type, x, y, container);
    }

    @Override
    public void outOfFrames() {
        resetFrames();
    }
}
