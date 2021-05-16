package Main;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DummySquareGame {
    UI GUI;
    Graphics2D graphics;
    Rectangle rec;
    int speed = 10;
    int movementX=0;
    int movementY=0;
    public static final ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);
    boolean running = true;
    public DummySquareGame(){
        GUI = new UI(600,600,"LostSouls");
        graphics = GUI.canvas.getBackingGraphics();
        rec = new Rectangle(100,100,100,100);
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
    }

    public void render(){
        GUI.clear();
        graphics.draw(rec);
        GUI.redraw();
    }

    public void move(){
        rec.translate(movementX,movementY);
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
