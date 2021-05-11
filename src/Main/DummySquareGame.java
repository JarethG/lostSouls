package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DummySquareGame {
    UI GUI;
    Graphics2D graphics;
    Rectangle rec;
    int speed = 10;
    int movementX=0;
    int movementY=0;
    public DummySquareGame(){
//        GUI = new UI(600,600,"LostSouls");
//        graphics = GUI.canvas.getBackingGraphics();
//        rec = new Rectangle(100,100,100,100);
        mainLoop();
    }

    public void mainLoop(){
        pool.schedule(()->{
            long frameTime = 1000/24;
            System.out.println("running");
            long dt = 0;
            while(true){
                dt = System.currentTimeMillis();
                update();
                render();
                dt = System.currentTimeMillis()-dt;
                if(dt<frameTime){
                    Thread.sleep(frameTime-dt);
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




    public static final ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);
    public static final ScheduledThreadPoolExecutor pool2 = new ScheduledThreadPoolExecutor(1);

    /**
     * example of thread pool that uses system.current time to control frames.
     * 25 frames per second
     */
    public void rep(){
        pool.schedule(()->{
        long frameTime = 1000/24;
        int count = 0;
        long startTime = System.currentTimeMillis();
        long timePassed = 0;
        System.out.println("Test started");
            while(true){
                long usedTime = System.currentTimeMillis();
                count++;
                if(usedTime-startTime>1000){
                    startTime=usedTime;
                    System.out.println(count);
                    count=0;
                }
//                GUI.update();
                usedTime = System.currentTimeMillis() - usedTime;//gets time passed
                long sleepTime = frameTime-usedTime;
                if(sleepTime>1){Thread.sleep(sleepTime);

            }
        }},500,TimeUnit.MILLISECONDS);
    }

    public int count = 0;
    long startTime;

    public void rep2(){
        System.out.println("starting rep2");
        startTime = System.currentTimeMillis();
        pool2.scheduleAtFixedRate((()->{
            if(!pool2.getQueue().isEmpty()){System.out.println("Skipping the frame");return;}
            try{SwingUtilities.invokeAndWait(()->{
                count++;
                long end = System.currentTimeMillis();
                if(end-startTime>1000){
                    startTime=end;
                    System.out.println(count);
                    count=0;
                }
            });}
                catch(InvocationTargetException | InterruptedException e) {

                    e.printStackTrace();
                    System.exit(0);
                }}),500,1000, TimeUnit.MILLISECONDS);

    }


}
