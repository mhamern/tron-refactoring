package game.tron;

public enum TronDirection {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    private TronDirection opposite;

    static {
        UP.opposite = DOWN;
        DOWN.opposite = UP;
        LEFT.opposite = RIGHT;
        RIGHT.opposite = LEFT;
    }

    public TronDirection getOppositeDirection() {
        return opposite;
    }
}
