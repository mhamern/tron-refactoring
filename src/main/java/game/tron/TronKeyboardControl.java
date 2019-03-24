package game.tron;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;

public class TronKeyboardControl implements KeyListener {

    private Collection<TronPlayer> players;

    public TronKeyboardControl(Collection<TronPlayer> players) {
        this.players = players;
    }

    public void keyPressed(KeyEvent e) {
        for (TronPlayer player: players) {
            if (player.isValidKey(e.getKeyCode())) {
                player.setDirection(player.getControls().getDirection(e.getKeyCode()));
            }
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent arg0) {

    }

}
