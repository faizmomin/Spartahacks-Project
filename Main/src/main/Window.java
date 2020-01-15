package main;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Window extends JFrame {

    public Window() {
        initWindow();
    }

  

    public void initWindow() {
        setTitle("");
        add(new DrawingSurface());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(720, 500);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        addKeyListener(new Keys());
        addMouseListener(new Mouse());
        setFocusable(true);
    }

}
