package nz.ac.massey.games_programming.props;

import java.awt.*;
import java.util.ArrayList;

public class Explosive extends SpriteProp {

    private int fuse;
    private int damage;
    private int range;
    private boolean ready = false;
    private boolean exploded = false;
    private Image[] explosionAnimation;


    public Explosive(PropType type, int x, int y, ArrayList<SpriteProp> container) {
        super(type, x, y, container);
    }

    public Explosive(PropType type, int x, int y, ArrayList<SpriteProp> container, int damage, int range) {
        super(type, x, y, container);
        this.damage = damage;
        this.range = range;
    }

    public void lightFuse(int fuse) {
        this.fuse = fuse;
        System.out.println("The fuse has been lit.");
    }

    public void setAnimations(Image[] fuseAnimation, Image[] explosionAnimation) {
        this.setAnimation(fuseAnimation);
        this.explosionAnimation = explosionAnimation;
    }

    @Override
    public void outOfFrames() {
        if (!exploded) {
            // Once we finish the countdown animation, start the explosion animation.
            exploded = true;
            setAnimation(explosionAnimation);
        } else {
            // Remove this sprite now that it is gone.
            container.remove(this);
        }
    }
}
