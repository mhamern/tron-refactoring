public class GameTimer {

    private long gameTimeInMillis;

    public long getGameTimeInMillis() {
        return gameTimeInMillis;
    }

    public void start() {
        gameTimeInMillis =  System.currentTimeMillis();
    }

    public void update() {
        gameTimeInMillis += calculateTimeSinceStart();
    }

    private long calculateTimeSinceStart() {
        return System.currentTimeMillis() - gameTimeInMillis;
    }

}
