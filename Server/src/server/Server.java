package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

class Get implements Runnable {

    private final Socket clientSocket; //initialize in const'r
    private final int index;

    public Get(Socket c, int i) {
        clientSocket = c;
        index = i;
    }

    public void run() {

        try {
            Scanner in = new Scanner(clientSocket.getInputStream());

            while (true) {
                int type = in.nextInt();
                if (type == 0) {
                    float x = in.nextFloat();
                    float y = in.nextFloat();
                    synchronized (Server.players) {
                        Server.players.set(index, new Player(x, y));
                    }
                } else if (type == 1) {
                    float x = in.nextFloat();
                    float y = in.nextFloat();
                    float xv = in.nextFloat();
                    float yv = in.nextFloat();
                    synchronized (Server.bullets) {
                        Server.bullets.add(new Bullet(x, y, xv, yv, index));
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Send implements ActionListener {

    Send() {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        synchronized (Server.sockets) {
            synchronized (Server.bullets) {

                for (int i = 0; i < Server.sockets.size(); i++) {
                    System.out.println("Sending " + i);
                    synchronized (Server.players) {
                        PrintStream p;
                        try {
                            p = new PrintStream((Server.sockets.get(i)).getOutputStream());

                            //send int
                            p.println(Server.players.size() - 1);

                            for (int j = 0; j < Server.players.size(); j++) {
                                if (j != i) {
                                    //send player data
                                    p.println(Server.players.get(j).getX());
                                    p.println(Server.players.get(j).getY());
                                }

                            }
                            int n = 0;
                            for (int j = 0; j < Server.bullets.size(); ++j) {
                                if (Server.bullets.get(j).index != i) {
                                    ++n;
                                }
                            }
                            p.println(n);
                            for (int j = 0; j < Server.bullets.size(); ++j) {
                                if (Server.bullets.get(j).index != i) {
                                    p.println(Server.bullets.get(j).xPos);
                                    p.println(Server.bullets.get(j).yPos);
                                    p.println(Server.bullets.get(j).xVel);
                                    p.println(Server.bullets.get(j).yVel);
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                Server.bullets = new ArrayList();
            }
        }
    }

}

public class Server {

    public static ArrayList<Socket> sockets = new ArrayList();
    public static ArrayList<Player> players = new ArrayList();
    public static ArrayList<Bullet> bullets = new ArrayList();

    public static void main(String[] args) throws IOException {
        ActionListener s = new Send();
        Timer timer = new Timer(1000/32, s);
        timer.start();
        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(5432);
        int i = 0;
        for (;;) {
            Socket clientSocket = null;
            clientSocket = serverSocket.accept();
            synchronized (players) {
                synchronized (sockets) {
                    players.add(new Player(0, 0));
                    sockets.add(clientSocket);
                }
            }
            System.out.println(i + "Person added");
            //delegate to new thread
            new Thread(new Get(clientSocket, i)).start();
            ++i;
        }

    }

}


