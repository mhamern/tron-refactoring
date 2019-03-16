import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private Position center;
    private List<Position> path;
    private TronDirection direction;
    private int speed;

    public Player(Position center, TronDirection currentTronDirection, int speed) {
        this.center = center;
        this.path = new ArrayList<Position>();
        this.direction = currentTronDirection;
        this.speed = speed;
    }

    public Position getCenter() {
        return center;
    }

    public void setCenter(Position center) {
        this.center = center;
    }

    public List<Position> getPath() {
        return path;
    }

    public void setPath(List<Position> path) {
        this.path = path;
    }

    public TronDirection getDirection() {
        return direction;
    }

    public void setDirection(TronDirection currentTronDirection) {
        this.direction = currentTronDirection;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
