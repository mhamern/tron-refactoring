package game.tron;

import utils.Position;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Dummy player generator. In real application it game menu or some smart randomizer should be used
 */
public class PlayerGenerator {

    private static final int PLAYER_SPEED = 5;
    private static int playersCreated = 0;


    public static Set<TronPlayer> generateRandomPlayers(int numberOfPlayers) {
        Set<TronPlayer> players = new HashSet<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(createPlayerWithRandomAttributes());
            playersCreated++;
        }
        return players;
    }

    private static TronPlayer createPlayerWithRandomAttributes() {
        return new TronPlayer(
                createPlayerInitialPosition(),
                TronDirection.DOWN,
                PLAYER_SPEED,
                createPlayerUniqueColor(),
                createPlayerControls());
    }

    private static Position createPlayerInitialPosition() {
        if (playersCreated == 0) {
            return new Position(0, 0);
        }
        return new Position(800, 800);
    }

    private static Color createPlayerUniqueColor() {
        if (playersCreated == 0) {
            return Color.GREEN;
        }
        return Color.RED;
    }


    private static TronPlayerControls createPlayerControls() {
        Map<Integer, TronDirection> controlsMap  = new HashMap<>();

        if (playersCreated == 0) {
            controlsMap.put(KeyEvent.VK_UP, TronDirection.UP);
            controlsMap.put(KeyEvent.VK_DOWN, TronDirection.DOWN);
            controlsMap.put(KeyEvent.VK_LEFT, TronDirection.LEFT);
            controlsMap.put(KeyEvent.VK_RIGHT, TronDirection.RIGHT);
        }
        else {
            controlsMap.put(KeyEvent.VK_W, TronDirection.UP);
            controlsMap.put(KeyEvent.VK_S, TronDirection.DOWN);
            controlsMap.put(KeyEvent.VK_A, TronDirection.LEFT);
            controlsMap.put(KeyEvent.VK_D, TronDirection.RIGHT);
        }
        return new TronPlayerControls(controlsMap);
    }

}
