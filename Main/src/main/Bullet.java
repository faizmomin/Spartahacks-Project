package main;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {

    public float x;
    public float y;
    public float xVel;
    public float yVel;
    public int bounces = 0;
    public boolean own = false;
    private static final float maxVel = 1f;

    public Bullet(float xPos, float yPos, int xClick, int yClick) {
        y = yPos + 10;
        x = xPos + 10;
        xVel = xClick - 15 - 1366/2;
        yVel = yClick - 45 -769/2;
        float velTot = (float) Math.sqrt(xVel * xVel + yVel * yVel);
        xVel *= maxVel / velTot;
        yVel *= maxVel / velTot;
        own = true;
        Client.sendBullet(this);
    }

    public Bullet(float xPos, float yPos, float xVel, float yVel) {
        y = yPos;
        x = xPos;
        this.xVel = xVel;
        this.yVel = yVel;
    }

    public void updatePos(int index) {

        float[] dv = {xVel, yVel};
        for (Wall w : Map.walls) {
            w.circleHit(x, y, 15f, dv);
        }
        for (Rect r : Map.rects) {
            r.circleHit(x, y, 3, dv);
        }

        if (dv[0] == 0) {
            ++bounces;
            xVel *= -1;

        } else if (dv[1] == 0) {
            ++bounces;
            yVel *= -1;
        }
        if (bounces == 2) {
            DrawingSurface.bulletsToRemove.add(index);
        }
        x += xVel * DrawingSurface.frameTime;
        y += yVel * DrawingSurface.frameTime;

        if (!own) {
            synchronized (DrawingSurface.players) {
                if (Math.sqrt((x - DrawingSurface.players.get(0).x) * (x - DrawingSurface.players.get(0).x)
                        + (y - DrawingSurface.players.get(0).y) * (y - DrawingSurface.players.get(0).y)) < 20) {
                    DrawingSurface.players.get(0).hit();
                    DrawingSurface.bulletsToRemove.add(index);
                }
            }
        }
    }

    public void draw(Graphics g, int index) {
        updatePos(index);
        g.setColor(new Color(255,223,0));
        g.fillOval((int) x, (int) y, 10, 10);
    }
}
