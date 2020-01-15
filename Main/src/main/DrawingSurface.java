package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawingSurface extends JPanel {

    public static Map map = new Map();
    public static ArrayList<Player> players = new ArrayList();
    public static ArrayList<Bullet> bullets = new ArrayList();
    public static ArrayList<Integer> bulletsToRemove = new ArrayList();
    private static long lastFrame = System.currentTimeMillis();
    public static long frameTime = 0;

    public DrawingSurface() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                repaint();
            }
        };
        Timer timer = new Timer(0, al);
        timer.start();
    }

    public static void init() {
        players.add(new Player(0, (int) (2800 * Math.random())));
    }

    public void doDrawing(Graphics g) {

        synchronized (players) {
            g.translate(1366/2 - (int) players.get(0).x, 768/2 - (int) players.get(0).y);
            Client.sendPlayer(players.get(0));
            g.clearRect(0, 0, 4500, 3500);
            g.setColor(new Color(0,144,151));
            g.fillRect(-1000, -1000, 5900, 5900);
            g.setColor(new Color(26,166,4));
            g.fillRect(0, 0, 3900, 2900);

            for (Player player : players) {
                player.draw(g);
            }
        }
        int i = 0;

        synchronized (bullets) {
            for (Bullet bullet : bullets) {
                bullet.draw(g, i);
                ++i;
            }
        }
        map.draw(g);
        synchronized (bullets) {
            for (i = bulletsToRemove.size() - 1; i >= 0; --i) {
                bullets.remove(i);
            }
        }
        bulletsToRemove = new ArrayList();
        long newFrame = System.currentTimeMillis();
        frameTime = newFrame - lastFrame;
        lastFrame = newFrame;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
