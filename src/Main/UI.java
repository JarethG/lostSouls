package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UI {

        JFrame frame;
        Canvas canvas;
    public static final ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);

        public UI(int width, int height, String name){
            frame = new JFrame(name);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            canvas = new Canvas(600,600);
            frame.add(canvas);
            frame.pack();
        }

    @SuppressWarnings("BusyWait")
    public void start(Game game){
            boolean running = true;
        pool.schedule(()->{
            long frameTime = 1000/32;
            System.out.println("running");
            long dt;
            while(running){
                dt = System.currentTimeMillis();
                game.update();
                clear();
                game.render(canvas.getBackingGraphics());
                redraw();
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
            }},500, TimeUnit.MILLISECONDS);
    }

        public void setKeyListener(KeyListener keyListener){
            canvas.requestFocus();
            canvas.addKeyListener(keyListener);
        }

        public void redraw(){
            canvas.redisplay();
        }

        public void clear(){canvas.clear();}


}
