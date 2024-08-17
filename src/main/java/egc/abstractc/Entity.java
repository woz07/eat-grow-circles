package egc.abstractc;

import com.raylib.Jaylib;
import com.raylib.Raylib;

/**
 * Entity.java
 * The base model class for every object within the game
 */
public abstract class Entity {
    public Jaylib.Vector2 pos;
    public float radius;
    public Raylib.Color color;
    public Entity(float x, float y, float radius, Raylib.Color color) {
        pos = new Jaylib.Vector2();
        pos.x(x);
        pos.y(y);
        this.radius = radius;
        this.color = color;
    }
}