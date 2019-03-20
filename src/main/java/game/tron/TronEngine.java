package game.tron;

import game.generic.GameEngine;
import utils.Position;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class TronEngine implements GameEngine, KeyListener, MouseListener {

	private static final int NUMBER_OF_PLAYERS = 2;
	private static final int PLAYER_SPEED = 5;

	private Set<TronPlayer> players;
	private Position maxPosition;
	private boolean isRunning;
	private GameTimer gameTimer;

	public TronEngine(int width, int height) {
		this.maxPosition = new Position(width, height);
		initializePlayers();
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
		for (TronPlayer player: players) {
			player.moveInCurrentDirection(maxPosition);
		}
		if (arePathsCrossed()) {
			stop();
		}
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void keyPressed(KeyEvent e) {
		for (TronPlayer player: players) {
			if (player.isValidKey(e.getKeyCode())) {
				player.setDirection(player.getControls().getDirection(e.getKeyCode()));
			}
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	private void startGameTimer() {
		gameTimer = new GameTimer();
		gameTimer.start();
	}


	private void initializePlayers() {
		players = new HashSet<>();
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			players.add(createPlayerWithRandomAttributes());
		}
	}

	private TronPlayer createPlayerWithRandomAttributes() {
		return new TronPlayer(createPlayerInitialPosition(),
				TronDirection.DOWN,
				PLAYER_SPEED,
				createPlayerUniqueColor(),
				createPlayerControls());
		// TODO: Create random and non-blocking color and position
	}

	private Position createPlayerInitialPosition() {
		// TODO: Extract to separate class
		// Should be resolved smarter (by game menu)
		if (players.size() == 0) {
			return new Position(0, 0);
		}
		return new Position(800, 800);
	}

	private Color createPlayerUniqueColor() {
		// TODO: Extract to separate class
		// Should be resolved smarter
		if (players.size() == 0) {
			return Color.GREEN;
		}
		return Color.RED;
	}

	private boolean arePathsCrossed() {
		for (TronPlayer player: players) {
			for (TronPlayer otherPlayer: players) {
				if (player.isCrossingPath(otherPlayer)) {
					return true;
				}
			}
		}
		return false;
	}


	private TronPlayerControls createPlayerControls() {
		// TODO: Extract to separate class
		// Should be resolved smarter (by game menu)
		Map<Integer, TronDirection> controlsMap  = new HashMap<>();

		if (players.size() == 0) {
			controlsMap.put(KeyEvent.VK_UP, TronDirection.UP);
			controlsMap.put(KeyEvent.VK_DOWN, TronDirection.DOWN);
			controlsMap.put(KeyEvent.VK_LEFT, TronDirection.LEFT);
			controlsMap.put(KeyEvent.VK_RIGHT, TronDirection.RIGHT);
		}
		if (players.size() == 1) {
			controlsMap.put(KeyEvent.VK_W, TronDirection.UP);
			controlsMap.put(KeyEvent.VK_S, TronDirection.DOWN);
			controlsMap.put(KeyEvent.VK_A, TronDirection.LEFT);
			controlsMap.put(KeyEvent.VK_D, TronDirection.RIGHT);
		}
		return new TronPlayerControls(controlsMap);
	}

}
