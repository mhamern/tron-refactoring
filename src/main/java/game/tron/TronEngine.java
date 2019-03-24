package game.tron;

import game.generic.GameEngine;
import utils.Position;
import java.util.*;

public class TronEngine implements GameEngine {

	private static final int NUMBER_OF_PLAYERS = 2;

	private Set<TronPlayer> players;
	private Position maxPosition;
	private boolean isRunning;
	private GameTimer gameTimer;
	private TronMouseControl mouseControl;
	private TronKeyboardControl keyboardControl;

	public TronEngine(int width, int height) {
		this.maxPosition = new Position(width, height);
		initialize();
	}

	public Set<TronPlayer> getPlayers() {
		return players;
	}

	public void start() {
		startGameTimer();
		isRunning = true;
	}

	public void stop() {
		isRunning = false;
	}

	public void gameStep(){
		gameTimer.update();
		movePlayers();
		if (CollisionDetector.collisionOccurred(players)) {
			stop();
		}
	}

	public TronMouseControl getMouseListener() {
		return mouseControl;
	}

	public TronKeyboardControl getKeyListener() {
		return keyboardControl;
	}

	public boolean isRunning() {
		return isRunning;
	}

	private void startGameTimer() {
		gameTimer = new GameTimer();
		gameTimer.start();
	}

	private void movePlayers() {
		for (TronPlayer player: players) {
			player.moveInCurrentDirection(maxPosition);
		}
	}

	private void initialize() {
		players = PlayerGenerator.generateRandomPlayers(NUMBER_OF_PLAYERS);
		this.mouseControl = new TronMouseControl(players);
		this.keyboardControl = new TronKeyboardControl(players);
	}
}
