package egc.models;

import com.raylib.Raylib;
import egc.abstractc.Entity;

/**
 * Player.java
 * Base model class for storing the players data
 */

public class Player extends Entity {
    public int score;
    public Player(float x, float y, float radius, int score, Raylib.Color color) {
        super(x, y, radius, color);
        this.score = 0;
    }
}
