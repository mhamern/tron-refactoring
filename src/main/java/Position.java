public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveUp(int amount) {
        this.y += amount;
    }

    public void moveDown(int amount) {
        this.y -= amount;
    }

    public void moveRight(int amount) {
        this.x += amount;
    }

    public void moveLeft(int amount) {
        this.x -= amount;
    }
}
