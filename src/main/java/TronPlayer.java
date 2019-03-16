
public class TronPlayer extends Player {

    public TronPlayer(Position center, TronDirection currentTronDirection, int speed) {
        super(center, currentTronDirection, speed);
    }

    public void moveInCurrentDirection(Position boundaries) {
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

    private void moveUp(int maxHeight) {
        if (isOutOfBoundsUp(maxHeight)){
            getCenter().moveUp(getSpeed());
        } else {
            getCenter().setY(0);
        }
    }

    private void moveDown(int maxHeight) {
        if (isOutOfBoundsDown()){
            getCenter().moveDown(getSpeed());
        } else {
            getCenter().setY(maxHeight);
        }
    }

    private void moveLeft(int maxWidth) {
        if (isOutOfBoundsLeft()){
            getCenter().moveLeft(getSpeed());
        } else {
            getCenter().setX(maxWidth);
        }
    }

    private void moveRight(int maxWidth) {
        if (isOutOfBoundsRight(maxWidth)){
            getCenter().moveRight(getSpeed());
        } else {
            getCenter().setX(0);
        }
    }

    private boolean isOutOfBoundsDown() {
        return getCenter().getY() > 0;
    }

    private boolean isOutOfBoundsUp(int maxHeight) {
        return getCenter().getY() < maxHeight;
    }

    private boolean isOutOfBoundsLeft() {
        return getCenter().getX() > 0;
    }

    private boolean isOutOfBoundsRight(int maxWidth) {
        return getCenter().getX() < maxWidth;
    }
}
