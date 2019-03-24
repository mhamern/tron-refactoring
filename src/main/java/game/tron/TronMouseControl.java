package game.tron;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;

public class TronMouseControl implements MouseListener {

    private Collection<TronPlayer> players;

    public TronMouseControl(Collection<TronPlayer> players) {
        this.players = players;
    }


    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent e) {
        for (TronPlayer player: players) {
            if (player.isValidKey(e.getButton())) {
                player.turnDirection(player.getControls().getDirection(e.getButton()));
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

}
