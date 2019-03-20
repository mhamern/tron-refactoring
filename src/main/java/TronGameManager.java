import java.awt.*;

public class TronGameManager implements GameManager {

    private TronEngine gameEngine;
    private TronView gameView;
    private ScreenManager screenManager;

    public static void main(String[] args) {
        new TronGameManager().run();
    }

    public void run() {
        setup();
        gameEngine.start();

        while (gameEngine.isRunning()) {
            gameEngine.gameStep();
            gameView.draw();
            screenManager.update();
        }
        System.exit(0);
    }

    private void setup() {
        setupScreen();
        setupGameEngine();
        setupView();
    }

    private void setupScreen() {
        screenManager = new ScreenManager();
        ScreenManager screenManager = new ScreenManager();
        DisplayMode dm = screenManager.findFirstCompatibleMode(SupportedDisplayModes.DISPLAY_MODES);
        screenManager.setFullScreen(dm);
    }

    private void setupGameEngine() {
        gameEngine = new TronEngine(screenManager.getWidth(), screenManager.getHeight());
        screenManager.getFullScreenWindow().addKeyListener(gameEngine);
        screenManager.getFullScreenWindow().addMouseListener(gameEngine);
    }

    private void setupView() {
        gameView = new TronView(screenManager, gameEngine.getPlayers());
    }
}
