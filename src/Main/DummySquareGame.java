package Main;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DummySquareGame {
    UI GUI;
    Graphics2D graphics;
    Rectangle rec;
    public DummySquareGame(){
        GUI = new UI(600,600,"LostSouls");
        graphics = GUI.canvas.getBackingGraphics();
        rec = new Rectangle(100,100,100,100);
        setKeys();
    }

    public void move(int x){
        rec.translate(x,0);
        GUI.clear();
        graphics.draw(rec);
        GUI.redraw();
    }

    public void setKeys() {
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        move(5);
                        break;
                    case KeyEvent.VK_LEFT:
                        move(-5);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        };
        GUI.setKeyListener(keyListener);
    }
}
