package main;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static PrintStream p;
    public static Scanner sc;
    public static Socket s;

    public static void sendPlayer(Player pl) {
        p.println((int) 0);
        p.println(pl.x);
        p.println(pl.y);
    }

    public static void sendBullet(Bullet b) {
        p.println(1);
        p.println(b.x);
        p.println(b.y);
        p.println(b.xVel);
        p.println(b.yVel);
    }

    public static void init() throws IOException {
        s = new Socket("127.0.0.1", 5432);
        sc = new Scanner(s.getInputStream());
        p = new PrintStream(s.getOutputStream());
    }

    public static void run() {

        while (true) {
            ArrayList<Player> players = new ArrayList();
            ArrayList<Bullet> bullets = new ArrayList();
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                float xPos = sc.nextFloat();
                float yPos = sc.nextFloat();
                players.add(new Player((int) xPos, (int) yPos));
            }

            n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                float xPos = sc.nextFloat();
                float yPos = sc.nextFloat();
                float xVel = sc.nextFloat();
                float yVel = sc.nextFloat();
                bullets.add(new Bullet(xPos, yPos, xVel, yVel));
            }

            synchronized (DrawingSurface.players) {
                if (players.size() != 0) {
                    Player m = DrawingSurface.players.get(0);
                    DrawingSurface.players = new ArrayList();
                    DrawingSurface.players.add(m);
                    DrawingSurface.players.addAll(players);

                }
            }
            synchronized (DrawingSurface.bullets) {
                DrawingSurface.bullets.addAll(bullets);
            }
        }

    }

}
