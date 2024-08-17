package egc.models;

/**
 * Window.java
 * Base model class for storing the GUI's data
 */

public class Window {
    public int width, height;
    public String title;
    public int fps;
    public Window(int width, int height, String title, int fps) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.fps = fps;
    }
}
