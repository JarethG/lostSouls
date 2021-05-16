package Main;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import Assets.Map;
import Assets.Screen;
import Graphics.MapLoader2D;


public class LostSouls implements Game {

    UI GUI;
    MapLoader2D mapLoader2D = new MapLoader2D();
    Screen screen;
    int dx = 0;
    int dy = 0;
    int posX = 0;
    int posY = 0;
    int speed = 5;




    public LostSouls (){
        GUI = new UI(600,600,"LostSouls");
        screen = new Screen();
        screen.setMap(new Map(mapLoader2D.loadDummyMap(20)));
        setKeys();
        GUI.start(this);

    }

    @Override
    public void update() {
        screen.update(dx,dy);
    }

    @Override
    public void render(Graphics2D graphics) {
        screen.draw(graphics);
    }

    public void setKeys() {
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT -> dx = -speed;
                    case KeyEvent.VK_LEFT -> dx = speed;
                    case KeyEvent.VK_UP -> dy = speed;
                    case KeyEvent.VK_DOWN -> dy = -speed;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        if(dx == -speed)
                            dx=0;
                        break;
                    case KeyEvent.VK_LEFT:
                        if(dx == speed)
                            dx=0;
                        break;
                    case KeyEvent.VK_DOWN:
                        if(dy == -speed)
                            dy=0;
                        break;
                    case KeyEvent.VK_UP:
                        if(dy == speed)
                            dy=0;
                        break;
                }
            }
        };
        GUI.setKeyListener(keyListener);
    }
}
