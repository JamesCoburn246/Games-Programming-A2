// Games Programming A2 "Game"
// Reece Bonnington
// Pierce Grant
// Josh Cressey
// James Coburn

package nz.ac.massey.games_programming;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends GameEngine implements KeyListener {

    private int winWidth = 1280, winHeight = 720;


    public static void main(String[] args) {
        System.out.println("Hello world!");
        createGame(new Main(), 30);
    }

    public void init() {
        setWindowSize(winWidth, winHeight);
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void paintComponent() {

    }


    public void keyPressed(KeyEvent e) {
        ///////// |W A S D - Movement Keys /////////////
        if(e.getKeyCode() == KeyEvent.VK_W){
            System.out.println("W Key is Pressed");

        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("A Key is Pressed");

        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            System.out.println("S Key is Pressed");

        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            System.out.println("D Key is Pressed");

        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            System.out.println("UP Arrow Key is Pressed");

        }

        ///////// |Arrow Key - Movement Keys /////////////
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            System.out.println("Left Arrow Key is Pressed");

        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            System.out.println("Down Arrow Key is Pressed");

        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            System.out.println("Right Arrow Key is Pressed");

        }

        ///////// |Special Keys - Interaction and Pause Menu /////////////
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            System.out.println("Space Bar Key is Pressed");

        }
        if(e.getKeyCode() == KeyEvent.VK_E){
            System.out.println("E Key is Pressed");

        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.out.println("ESC Key is Pressed");

        }
    }
}
