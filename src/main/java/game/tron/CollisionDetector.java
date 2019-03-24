package game.tron;

import utils.Position;

import java.util.Collection;
import java.util.List;

public class CollisionDetector {

    public static boolean collisionOccured(Collection<TronPlayer> players) {
        for (TronPlayer player: players) {
            for (TronPlayer otherPlayer: players) {
                if (areCrossingPaths(player, otherPlayer)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean areCrossingPaths(TronPlayer firstPlayer, TronPlayer secondPlayer) {
        List<Position> pathTrail = getPathTrail(secondPlayer.getPath());
        for (Position otherPlayersPosition: pathTrail) {
            if (standsOnPosition(firstPlayer, otherPlayersPosition)) {
                return true;
            }
        }
        return false;
    }

    private static List<Position> getPathTrail(List<Position> path) {
        return path.subList(0, path.size() - 1);
    }

    private static boolean standsOnPosition(TronPlayer player, Position position) {
        return player.getCenter().equals(position);
    }

}
