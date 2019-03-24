package game.tron;

import game.generic.GameEngine;
import utils.Position;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class TronEngine implements GameEngine, KeyListener, MouseListener {

	private static final int NUMBER_OF_PLAYERS = 2;

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
		movePlayers();
		if (CollisionDetector.collisionOccured(players)) {
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

	private void movePlayers() {
		for (TronPlayer player: players) {
			player.moveInCurrentDirection(maxPosition);
		}
	}

	private void initializePlayers() {
		players = PlayerGenerator.generateRandomPlayers(NUMBER_OF_PLAYERS);
	}
}
