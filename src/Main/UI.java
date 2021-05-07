package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

    public class UI {

        JFrame frame;
        Canvas canvas;
        LostSouls lostsouls;

        public UI(int width, int height, String name){
            lostsouls = new LostSouls();
            frame = new JFrame(name);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            canvas = new Canvas(600,600);
            frame.add(canvas);
            frame.pack();
        }

        public void setKeyListener(KeyListener keyListener){
            canvas.requestFocus();
            canvas.addKeyListener(keyListener);
        }

        public void redraw(){
            canvas.redisplay();
        }
}
