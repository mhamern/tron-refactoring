import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;

public class TronView implements GameView {
    private static final Color BACKGROUND_COLOR = Color.BLACK;

    private ScreenManager screenManager;
    private Set<TronPlayer> players;

    public TronView(ScreenManager screenManager, Set<TronPlayer> players) {
        this.players = players;
        this.screenManager = screenManager;
        setup();
    }

    public void draw() {
        Graphics2D graphics = screenManager.getGraphics();
        drawBackground(graphics);
        for (TronPlayer player: players) {
            drawPath(graphics, player.getPath(), player.getColor());
        }
    }

    private void setup() {
        Window w = screenManager.getFullScreenWindow();
        w.setFont(new Font("Arial",Font.PLAIN,20));
        w.setBackground(Color.WHITE);
        w.setForeground(Color.RED);
        w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null"));
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

}
