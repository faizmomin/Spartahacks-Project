package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Player {

    public float x;
    public float y;
    private int hp = 10;
    private boolean isMain;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        isMain = false;
    }

    public Player(int x, int y, boolean isMain) {
        this.x = x;
        this.y = y;
        this.isMain = isMain;
        this.hp = 10;
    }

    public void shoot(MouseEvent e) {
        synchronized (DrawingSurface.bullets) {
            DrawingSurface.bullets.add(new Bullet(x, y, e.getX(), e.getY()));
        }
    }

    public void hit() {
        --hp;
        if (hp <= 0) {
            x = 0;
            y = (float)Math.random() * 2800;
            hp = 10;
        }
    }

    public void update() {
        float dv[] = new float[2];
        if (Keys.UP) {
            dv[1] -= 0.2f;
        }
        if (Keys.DOWN) {
            dv[1] += 0.2f;
        }
        if (Keys.RIGHT) {
            dv[0] += 0.2f;
        }
        if (Keys.LEFT) {
            dv[0] -= 0.2f;
        }

        for (Wall w : Map.walls) {
            w.circleHit(x, y, 15f, dv);
        }
        for (Rect r : Map.rects) {
            r.circleHit(x, y, 15f, dv);
        }
        x += dv[0] * DrawingSurface.frameTime;
        y += dv[1] * DrawingSurface.frameTime;

    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillOval((int) x, (int) y, 30, 30);
        update();
    }
}
