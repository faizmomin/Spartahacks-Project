package main;

import java.awt.Color;
import java.awt.Graphics;

public class Wall {

    int x1, y1, x2, y2;
    boolean vertical;

    public Wall(int x1, int y1, int x2, int y2, boolean vertical) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.vertical = vertical;
    }

    public void circleHit(float x, float y, float rad, float[] v) {
        x += rad;
        y += rad;
        if (vertical) {
            if (Math.abs(x - x1) < rad) {
                if (y < Math.max(y1, y2) && y > Math.min(y1, y2)) {
                    if (x > x1) {
                        v[0] = Math.max(0, v[0]);
                    } else {
                        v[0] = Math.min(0, v[0]);
                    }
                }
            }
        } else {
            if (Math.abs(y - y1) < rad) {
                if (x < Math.max(x1, x2) && x > Math.min(x1, x2)) {
                    if (y > y1) {
                        v[1] = Math.max(0, v[1]);
                    } else {
                        v[1] = Math.min(0, v[1]);
                    }
                }
            }
        }

    }

    public void draw(Graphics g) {
        g.setColor(new Color(145,142,133));
        g.drawLine(x1, y1, x2, y2);
    }
}
