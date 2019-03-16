import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;

public class TronCore extends Core implements KeyListener, MouseListener,
		MouseMotionListener {

	private static final int NUMBER_OF_PLAYERS = 2;
	private static final int PLAYER_SPEED = 5;
	private static final Color BACKGROUND_COLOR = Color.BLACK;

	private Set<TronPlayer> players;
	private Position maxPosition;

	public void init() {
		super.init();

		Window w = screenManager.getFullScreenWindow();
		maxPosition = new Position(screenManager.getWidth(), screenManager.getHeight());
		w.addKeyListener(this);
		w.addMouseListener(this);
		w.addMouseMotionListener(this);
		initializePlayers();
	}

	public static void main(String[] args) {
		new TronCore().run();
	}

	public void draw(Graphics2D graphics) {
		drawBackground(graphics);
		for (TronPlayer player: players) {
			player.moveInCurrentDirection(maxPosition);
			drawPath(graphics, player.getPath(), player.getColor());
		}
		if (arePathsCrossed()) {
			exitGame();
		}
	}

	private void drawBackground(Graphics2D graphics) {
		graphics.setColor(BACKGROUND_COLOR);
		graphics.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());
	}

	private void drawPath(Graphics2D graphics, List<Position> path, Color color) {
		for (Position position: path) {
			graphics.setColor(color);
			graphics.fillRect(position.getX(), position.getY(), 10, 10);
		}
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

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
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


	private void exitGame() {
		System.exit(0);
	}
}
