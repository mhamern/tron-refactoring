import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TronPlayer {

    private Position center;
    private List<Position> path;
    private TronDirection direction;
    private int speed;
    private Color color;
    private TronPlayerControls controls;

    public TronPlayer(Position center, TronDirection currentTronDirection, int speed, Color color, TronPlayerControls controls) {
        this.center = center;
        this.path = new ArrayList<>();
        this.direction = currentTronDirection;
        this.speed = speed;
        this.color = color;
        this.controls = controls;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public void setDirection(TronDirection direction) {
        if (!isOppositeDirection(direction)) {
            this.direction = direction;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public TronPlayerControls getControls() {
        return controls;
    }

    public void setControls(TronPlayerControls controls) {
        this.controls = controls;
    }

    public boolean isValidKey(Integer key) {
       return this.controls.isValidKey(key);
    }

    public void moveInCurrentDirection(Position boundaries) {
        move(boundaries);
        leaveTrail();
    }

    public boolean isCrossingPath(TronPlayer otherPlayer) {
        List<Position> pathTrail = this.getPathTrail(otherPlayer.getPath());
        for (Position otherPlayersPosition: pathTrail) {
            if (standsOnPosition(otherPlayersPosition)) {
                return true;
            }
        }
        return false;
    }

    private List<Position> getPathTrail(List<Position> path) {
        return path.subList(0, path.size() - 1);
    }

    private void move(Position boundaries) {
        switch(getDirection()){
            case DOWN:
                moveDown(boundaries.getY());
                break;
            case RIGHT:
                moveRight(boundaries.getX());
                break;
            case UP:
                moveUp(boundaries.getY());
                break;
            case LEFT:
                moveLeft(boundaries.getX());
                break;
        }
    }

    private void leaveTrail() {
        path.add(new Position(center.getX(), center.getY()));
    }

    private void moveUp(int maxHeight) {
        if (isOutOfBoundsUp(maxHeight)){
            center.setY(0);
        } else {
            center.moveUp(speed);
        }
    }

    private void moveDown(int maxHeight) {
        if (isOutOfBoundsDown()){
            center.setY(maxHeight);
        } else {
            center.moveDown(speed);
        }
    }

    private void moveLeft(int maxWidth) {
        if (isOutOfBoundsLeft()){
            center.setX(maxWidth);
        } else {
            center.moveLeft(speed);
        }
    }

    private void moveRight(int maxWidth) {
        if (isOutOfBoundsRight(maxWidth)){
            center.setX(0);
        } else {
            center.moveRight(speed);
        }
    }

    private boolean isOutOfBoundsDown() {
        return center.getY() < 0;
    }

    private boolean isOutOfBoundsUp(int maxHeight) {
        return center.getY() > maxHeight;
    }

    private boolean isOutOfBoundsLeft() {
        return center.getX() < 0;
    }

    private boolean isOutOfBoundsRight(int maxWidth) {
        return center.getX() > maxWidth;
    }

    private boolean standsOnPosition(Position position) {
        return center.equals(position);
    }

    private boolean isOppositeDirection(TronDirection directionToChange) {
        return direction == directionToChange.getOppositeDirection();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TronPlayer that = (TronPlayer) o;
        return Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
