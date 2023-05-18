package nz.ac.massey.games_programming;

public class GameState {
    private State currentState;

    // Game State Getter
    public State getGameState() {
        return currentState;
    }

    // GameState Setter - When the GameState changes the Audio changes along with it
    public void setGameState(State state) {
        currentState = state;
        AudioClip MenuMusic = loadAudio("Sounds/MenuMusic.wav");
        AudioClip GameMusic = loadAudio("Sounds/MenuMusic.wav");
        AudioClip GameStart = loadAudio("Sounds/gameStart.wav");
        if (currentState == State.MAIN_MENU) {
            stopAudioLoop(GameMusic);
            startAudioLoop(MenuMusic, 20);
        }
        if (currentState == State.PLAYING) {
            stopAudioLoop(MenuMusic);
            playAudio(GameStart);
            startAudioLoop(GameMusic, 20);
        }
        if (currentState == State.PAUSED) {
            stopAudioLoop(GameMusic);
            startAudioLoop(MenuMusic, 20);
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
