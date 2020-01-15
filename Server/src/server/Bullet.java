package server;

/**
 *
 * @author eddie
 */
public class Bullet {
    public float xPos, yPos, xVel, yVel;
    public int index;
    public Bullet(float xPos, float yPos, float xVel, float yVel, int index) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;
        this.index = index;
    }
}


