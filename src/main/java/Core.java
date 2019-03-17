import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Core {

	private boolean isRunning;
	private GameTimer gameTimer;
	protected ScreenManager screenManager;

	public abstract void draw(Graphics2D g);

	public void stop(){
		isRunning = false;
		screenManager.restoreScreen();
	}

	public void run(){
		init();
		gameLoop();
	}

	public void init() {
		setupScreen();
		isRunning = true;
	}

	private void setupScreen() {
		screenManager = new ScreenManager();
		DisplayMode dm = screenManager.findFirstCompatibleMode(SupportedDisplayModes.DISPLAY_MODES);
		screenManager.setFullScreen(dm);
		Window w = screenManager.getFullScreenWindow();
		w.setFont(new Font("Arial",Font.PLAIN,20));
		w.setBackground(Color.WHITE);
		w.setForeground(Color.RED);
		w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null"));
	}
	
	private void gameLoop(){
		startGameTimer();
		while (isRunning){
			gameTimer.update();
			Graphics2D g = screenManager.getGraphics();
			draw(g);
			g.dispose();
			screenManager.update();
		}
	}

	private void startGameTimer() {
		gameTimer = new GameTimer();
		gameTimer.start();
	}
}
