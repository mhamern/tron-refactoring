package game.tron;

public enum TronDirection {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    private TronDirection opposite;

    private TronDirection leftTo;
    private TronDirection rightTo;

    static {
        UP.opposite = DOWN;
        DOWN.opposite = UP;
        LEFT.opposite = RIGHT;
        RIGHT.opposite = LEFT;
    }

    static {
        UP.leftTo = LEFT;
        DOWN.leftTo = RIGHT;
        LEFT.leftTo = DOWN;
        RIGHT.leftTo = UP;
    }

    static {
        UP.rightTo = RIGHT;
        DOWN.rightTo = LEFT;
        LEFT.rightTo = UP;
        RIGHT.rightTo = DOWN;
    }

    public TronDirection getOppositeDirection() {
        return opposite;
    }

    public TronDirection getTurnDirection(TronDirection turnDirection) {
        if (turnDirection == LEFT) {
            return leftTo;
        }
        else if (turnDirection == RIGHT)  {
            return rightTo;
        }
        return UP;
    }
}
