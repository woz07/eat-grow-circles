package egc.models;

import com.raylib.Raylib;
import egc.abstractc.Entity;

/**
 * Enemy.java
 * Base model class for storing the enemies data
 */

public class Enemy extends Entity {
    public Enemy(float x, float y, float radius, Raylib.Color color) {
        super(x, y, radius, color);
    }
}
