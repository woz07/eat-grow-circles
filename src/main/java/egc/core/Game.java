package egc.core;

import com.raylib.Raylib;
import egc.models.Enemy;
import egc.models.Player;
import egc.models.Window;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static com.raylib.Jaylib.BLACK;
import static com.raylib.Jaylib.WHITE;
import static com.raylib.Jaylib.LIGHTGRAY;
import static com.raylib.Jaylib.GRAY;
import static com.raylib.Jaylib.DARKGRAY;
import static com.raylib.Jaylib.YELLOW;
import static com.raylib.Jaylib.ORANGE;
import static com.raylib.Jaylib.PINK;
import static com.raylib.Jaylib.RED;
import static com.raylib.Jaylib.MAROON;
import static com.raylib.Jaylib.GREEN;
import static com.raylib.Jaylib.LIME;
import static com.raylib.Jaylib.DARKGREEN;
import static com.raylib.Jaylib.SKYBLUE;
import static com.raylib.Jaylib.BLUE;
import static com.raylib.Jaylib.DARKBLUE;
import static com.raylib.Jaylib.PURPLE;
import static com.raylib.Jaylib.VIOLET;
import static com.raylib.Jaylib.DARKPURPLE;
import static com.raylib.Jaylib.BEIGE;
import static com.raylib.Jaylib.BROWN;
import static com.raylib.Jaylib.DARKBROWN;
import static com.raylib.Raylib.*;

/**
 * Game.java
 * The main class that renders the GUI and the game
 */

public class Game {
    public Window window = new Window(
            1000, 1000,
            "Eat grow circles",
            60
    );
    public Player player = new Player(
            0, 0,
            10, 0,
            WHITE
    );
    public ArrayList<Enemy> enemies = new ArrayList<>();
    public Game() {
        Random random = new Random();
        Raylib.Color[] colors = {
                LIGHTGRAY, GRAY, DARKGRAY, YELLOW, ORANGE, PINK, RED, MAROON,
                GREEN, LIME, DARKGREEN, SKYBLUE, BLUE, DARKBLUE, PURPLE,
                VIOLET, DARKPURPLE, BEIGE, BROWN, DARKBROWN
        };

        InitWindow(window.width, window.height, window.title);
        SetTargetFPS(window.fps);

        boolean replay = false;
        boolean game = true;
        while (!WindowShouldClose()) {
            BeginDrawing();
            ClearBackground(BLACK);
            DrawCircle((int)player.pos.x(), (int)player.pos.y(), player.radius, player.color);
            for (Enemy enemy : enemies) {
                DrawCircle((int)enemy.pos.x(), (int)enemy.pos.y(), enemy.radius, enemy.color);
            }
            EndDrawing();

            player.pos.x(GetMouseX());
            player.pos.y(GetMouseY());

            if (Math.abs(GetTime() % 0.25) < 0.1) {
                int x, y, count;
                do {
                    count = 0;
                    x = random.nextInt((window.width - 10) + 1 - 10) + 10;
                    y = random.nextInt((window.height - 10) + 1 - 10) + 10;

                    if (
                            (player.pos.x() + player.radius) != x && (player.pos.x() - player.radius) != x &&
                                    (player.pos.y() + player.radius) != y && (player.pos.y() - player.radius) != y
                    ) {
                        for (Enemy enemy : enemies) {
                            if ((enemy.pos.x() == x && enemy.pos.y() == y)) {
                                count++;
                            }
                        }
                    }
                } while(count > 0);
                Enemy enemy = new Enemy(
                        x, y,
                        5,
                        colors[random.nextInt(colors.length - 1)]
                );
                enemies.add(enemy);
            }

            Iterator<Enemy> iterator = enemies.iterator();
            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();
                if (CheckCollisionCircles(player.pos, player.radius, enemy.pos, enemy.radius)) {
                    if (player.radius > enemy.radius) {
                        player.score++;
                        player.radius += 0.80;
                        iterator.remove();
                    } else {
                        game = false;
                        break;
                    }
                }
                enemy.radius += 0.08;
            }

            if (!game) {
                BeginDrawing();
                ClearBackground(BLACK);
                DrawText(
                        "Game over!\n\n\nScore: " + player.score,
                        window.width / 2 - 100,
                        window.height / 2 - 100,
                        50,
                        WHITE
                );
                EndDrawing();
                WaitTime(3);
                break;
            }
        }
        CloseWindow();
    }
    public static void main(String[] args) {
        new Game();
    }
}