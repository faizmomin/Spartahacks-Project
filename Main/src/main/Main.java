package main;

import java.io.IOException;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.setProperty("sun.java2d.opengl", "true");
        DrawingSurface.init();
        Client.init();
        Window w = new Window();

        Client.run();
    }

}
