// Games Programming A2 "Game"
// Reece Bonnington
// Pierce Grant
// Josh Cressey
// James Coburn

package nz.ac.massey.games_programming;

public class Main extends GameEngine {

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
}
