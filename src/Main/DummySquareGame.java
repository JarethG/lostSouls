package Main;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import Graphics.ImageLoader;

public class DummySquareGame {
    UI GUI;
    Graphics2D graphics;
    Rectangle rec;
    int speed = 10;
    int movementX=0;
    int movementY=0;
    int x = 100;
    int y = 100;
    public static final ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);
    boolean running = true;
    BufferedImage[] sprite;
    double frame = 0;

    public static void main(String[] args){
        new DummySquareGame();
    }
    public DummySquareGame(){
        GUI = new UI(600,600,"LostSouls");
        graphics = GUI.canvas.getBackingGraphics();
        ImageLoader il = new ImageLoader();
        sprite = il.loadSprite(7,64,64,"./src/Resources/Sprites/Slime.png");
//        rec = new Rectangle(100,100,100,100);
        setKeys();
        mainLoop();
    }


    @SuppressWarnings("BusyWait")
    public void mainLoop(){
        pool.schedule(()->{
            long frameTime = 1000/24;
            System.out.println("running");
            long dt;
            while(running){
                dt = System.currentTimeMillis();
                update();
                render();
                dt = System.currentTimeMillis()-dt;
                if(dt<frameTime){
                    try {
                        Thread.sleep(frameTime-dt);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("frame rate exceeded");
                }
            }},500,TimeUnit.MILLISECONDS);
    }

    public void update(){
        move();
        frame +=.3;
        if(frame > 6)
            frame = 0;

    }

    public void render(){
        GUI.clear();
        graphics.drawImage(sprite[(int) frame],x,y,null);
//        graphics.draw(rec);
        GUI.redraw();
    }

    public void move(){
        x +=movementX;
        y +=movementY;
//        rec.translate(movementX,movementY);
    }

    public void setKeys() {
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT -> movementX = speed;
                    case KeyEvent.VK_LEFT -> movementX = -speed;
                    case KeyEvent.VK_UP -> movementY = -speed;
                    case KeyEvent.VK_DOWN -> movementY = speed;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        if(movementX == speed)
                        movementX=0;
                        break;
                    case KeyEvent.VK_LEFT:
                        if(movementX == -speed)
                        movementX=0;
                        break;
                    case KeyEvent.VK_DOWN:
                        if(movementY == speed)
                            movementY=0;
                        break;
                    case KeyEvent.VK_UP:
                        if(movementY == -speed)
                            movementY=0;
                        break;
                }
            }
        };
        GUI.setKeyListener(keyListener);
    }





}
