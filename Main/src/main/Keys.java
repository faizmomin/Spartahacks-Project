package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener {

    static boolean UP = false;
    static boolean DOWN = false;
    static boolean LEFT = false;
    static boolean RIGHT = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            UP = true;
        } else if (e.getKeyChar() == 'a') {
            LEFT = true;
        } else if (e.getKeyChar() == 's') {
            DOWN = true;
        } else if (e.getKeyChar() == 'd') {
            RIGHT = true;
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            UP = false;
        } else if (e.getKeyChar() == 'a') {
            LEFT = false;
        } else if (e.getKeyChar() == 's') {
            DOWN = false;
        } else if (e.getKeyChar() == 'd') {
            RIGHT = false;
        } 
    }

}
