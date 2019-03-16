import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class TronCore extends Core implements KeyListener, MouseListener,
		MouseMotionListener {

	private static final int NUMBER_OF_PLAYERS = 2;
	private static final int PLAYER_SPEED = 5;

	private List<TronPlayer> players;
	private Position maxPosition;

	int centrex1 = 40;
	int centrey1 = 40;
	int centrex2 = 600;
	int centrey2 = 440;
	int currentDirection1 = 1;
	int currentDirection2 = 3;
	int moveAmount = 5;
	ArrayList<Integer> pathx1 = new ArrayList();
	ArrayList<Integer> pathy1 = new ArrayList();
	ArrayList<Integer> pathx2 = new ArrayList();
	ArrayList<Integer> pathy2 = new ArrayList();

	public TronCore() {
		initializePlayers();
	}

	public void init() {
		super.init();

		Window w = screenManager.getFullScreenWindow();
		maxPosition = new Position(screenManager.getWidth(), screenManager.getHeight());
		w.addKeyListener(this);
		w.addMouseListener(this);
		w.addMouseMotionListener(this);
	}

	public static void main(String[] args) {
		new TronCore().run();
	}

	public void draw(Graphics2D g) {
		for (TronPlayer player: players) {
			player.moveInCurrentDirection(maxPosition);
		}

	    for (int x = 0;x<pathx1.size();x++){
	    	if (((centrex1 == pathx1.get(x)) && (centrey1 == pathy1.get(x))) || ((centrex2 == pathx2.get(x)) && (centrey2 == pathy2.get(x))) || ((centrex1 == pathx2.get(x)) && (centrey1 == pathy2.get(x))) || ((centrex2 == pathx1.get(x)) && (centrey2 == pathy1.get(x)))){
	    		System.exit(0);
	    	}
	    }
		pathx1.add(centrex1);
		pathy1.add(centrey1);
		pathx2.add(centrex2);
		pathy2.add(centrey2);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());
		for (int x = 0;x<pathx1.size();x++){
		g.setColor(Color.green);
		g.fillRect(pathx1.get(x), pathy1.get(x), 10, 10);
		g.setColor(Color.red);
		g.fillRect(pathx2.get(x), pathy2.get(x), 10, 10);
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (currentDirection1 != 2){
			currentDirection1 = 0;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (currentDirection1 != 0){
				currentDirection1 = 2;
				}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (currentDirection1 != 3){
				currentDirection1 = 1;
				}
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (currentDirection1 != 1){
				currentDirection1 = 3;
				}
		}
		if (e.getKeyCode() == KeyEvent.VK_W){
			if (currentDirection2 != 2){
			currentDirection2 = 0;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			if (currentDirection2 != 0){
				currentDirection2 = 2;
				}
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			if (currentDirection2 != 3){
				currentDirection2 = 1;
				}
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			if (currentDirection2 != 1){
				currentDirection2 = 3;
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
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			players.add(createPlayerWithRandomPosition());
		}
	}

	private TronPlayer createPlayerWithRandomPosition() {
		Position center = new Position(0, 0);
		return new TronPlayer(center, TronDirection.RIGHT, PLAYER_SPEED);
	}
}
