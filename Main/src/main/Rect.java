package main;

import java.awt.Graphics;

public class Rect {

    public Wall walls[] = new Wall[4];
    int x, y, width, height;

    public Rect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        walls[0] = new Wall(x, y, x, y + height,true);
        walls[1] = new Wall(x, y, x + width, y,false);
        walls[2] = new Wall(x + width, y + height, x, y + height,false);
        walls[3] = new Wall(x + width, y + height, x + width, y, true);
    }
    
    public void circleHit(float x, float y, float rad, float[] v){
        for(Wall wall: walls){
            wall.circleHit(x, y, rad, v);
        }
    }
    
    public void draw(Graphics g){
        g.fillRect(x, y, width, height);
    }
}
