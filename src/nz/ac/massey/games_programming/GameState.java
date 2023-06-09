package nz.ac.massey.games_programming;

public class GameState {

    private static GameEngine.AudioClip menuMusic;
    private static GameEngine.AudioClip gameMusic;
    private static GameEngine.AudioClip gameStart;

    static {
        GameState.menuMusic = GameEngine.loadAudio("Sounds/MenuMusic.wav");
        GameState.gameMusic = GameEngine.loadAudio("Sounds/gameMusic.wav");
        GameState.gameStart = GameEngine.loadAudio("Sounds/gameStart.wav");
    }

    private final Main game;
    private State currentState;

    public GameState(Main game) {
        this.game = game;
    }

    // Game State Getter
    public State getGameState() {
        return currentState;
    }

    // GameState Setter
    public void setGameState(State state) {
        currentState = state;
        if (currentState == State.MAIN_MENU) {
            System.out.println("Game State Changed: " + currentState);
        }
        if (currentState == State.PLAYING) {
            System.out.println("Game State Changed: " + currentState);
        }
        if (currentState == State.PAUSED) {
            System.out.println("Game State Changed: " + currentState);
        }

    }

    public boolean is(State state) {
        return (this.currentState == state);
    }

    // Available Game States
    public enum State {
        MAIN_MENU, PLAYING, PAUSED, GAME_OVER
    }

}
