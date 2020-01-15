package main;

import java.awt.Color;
import java.awt.Graphics;

public class Map {

    public static Wall[] walls;
    public static Rect[] rects;

    public Map() {
        walls = new Wall[4];
        walls[0] = new Wall(0, 0, 3900, 0, false);
        walls[1] = new Wall(0, 0, 0, 2900, true);
        walls[2] = new Wall(3900, 2900, 0, 2900, false);
        walls[3] = new Wall(3900, 2900, 3900, 0, true);
        rects = new Rect[28];
        rects[0] = new Rect(30, 30, 100, 100);
        rects[1] = new Rect(1900, 0, 150, 150);
        rects[2] = new Rect(3200, 0, 400, 200);
        rects[3] = new Rect(3200, 0, 400, 200);
        rects[4] = new Rect(1700, 700, 700, 300);
        rects[5] = new Rect(1700, 1000, 150, 600);
        rects[6] = new Rect(2250, 1000, 150, 350);
        rects[7] = new Rect(2100, 1150, 150, 350);
        rects[8] = new Rect(2700, 1000, 650, 350);
        rects[9] = new Rect(150, 1200, 650, 350);
        rects[10] = new Rect(650, 1550, 150, 1000);
        rects[11] = new Rect(400, 1550, 250, 450);
        rects[12] = new Rect(1300, 1250, 200, 200);
        rects[13] = new Rect(2800, 200, 400, 200);
        rects[14] = new Rect(900, 1050, 200, 600);
        rects[15] = new Rect(1100, 1800, 750, 600);
        rects[16] = new Rect(1250, 2750, 900, 150);
        rects[17] = new Rect(2150, 2000, 400, 900);
        rects[18] = new Rect(2700, 1900, 400, 605);
        rects[19] = new Rect(3100, 1900, 600, 200);
        rects[20] = new Rect(3500, 2100, 200, 800);
        rects[21] = new Rect(2450, 1550, 900, 250);
        rects[22] = new Rect(3000, 400, 200, 450);
        rects[23] = new Rect(1250, 150, 1000, 200);
        rects[24] = new Rect(2400, 0, 150, 550);
        rects[25] = new Rect(1250, 350, 300, 400);
        rects[26] = new Rect(700, 150, 400, 800);
        rects[27] = new Rect(150, 150, 400, 200);

    }

    public void draw(Graphics g) {
        for (Wall wall : walls) {
            wall.draw(g);
        }
        for (Rect rect : rects) {
            rect.draw(g);
        }

    }
}
