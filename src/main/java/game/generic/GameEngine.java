package game.generic;

public interface GameEngine {
    void start();
    void stop();
	void gameStep();
	boolean isRunning();
}
