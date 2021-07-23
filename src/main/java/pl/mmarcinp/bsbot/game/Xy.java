package pl.mmarcinp.bsbot.game;

import org.apache.logging.log4j.util.Strings;

public class Xy {
    private int x;
    private int y;
    private final String regex = "\\D+";

    public Xy(String[] array) {
        this.x = Integer.parseInt(array[0].replaceAll(regex, Strings.EMPTY));
        this.y = Integer.parseInt(array[1].replaceAll(regex, Strings.EMPTY));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
