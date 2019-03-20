package game.tron;

import java.util.Map;

public class TronPlayerControls {
    private Map<Integer, TronDirection> controls;

    public TronPlayerControls(Map<Integer, TronDirection> controls) {
        this.controls = controls;
    }

    public void setControls(Map<Integer, TronDirection> controls) {
        this.controls = controls;
    }

    public boolean isValidKey(Integer key) {
       return controls.containsKey(key);
    }

    public TronDirection getDirection(Integer keyCode) {
        return controls.get(keyCode);
    }
}
