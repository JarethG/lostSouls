package Main;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import Graphics.MapLoader2D;


public class LostSouls implements Game {

    UI GUI;
    MapLoader2D mapLoader2D = new MapLoader2D();
    BufferedImage[][] images;
    int dx = 0;
    int dy = 0;
    int posX = 0;
    int posY = 0;
    int speed = 5;




    public LostSouls (){
        GUI = new UI(600,600,"LostSouls");
        images = mapLoader2D.loadDummyImages(20);
        setKeys();
        GUI.start(this);

    }

    @Override
    public void update() {
        posX+=dx;posY+=dy;
    }

    @Override
    public void render(Graphics2D graphics) {
        for (int y = 0; y < images.length; y++) {
            for (int x = 0; x < images.length; x++) {
                graphics.drawImage(images[x][y],400+posX + x * 50 - y * 50, 50+posY + 25 * x + y * 25,null);
            }
        }
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
