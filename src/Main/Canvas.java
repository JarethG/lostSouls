package Main;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JComponent {

        private Image imgBuf;
        private Graphics2D imgGraphic, visibleGraphic;
        private int width,height;

        public Canvas(int width, int height) {
            super();
            this.width = width;
            this.height = height;
            setPreferredSize(new Dimension(width,height));

            setFocusable(true);
        }

        public void addNotify() {
            super.addNotify();
            this.setBackground(Color.white);
            imgBuf = createImage(width,height);  // Can only be done by peer
            imgGraphic = (Graphics2D) imgBuf.getGraphics();
            imgGraphic.setPaintMode();
            imgGraphic.setColor(Color.black);
            visibleGraphic = (Graphics2D) this.getGraphics();
            clear();
            setFocusable(true);
        }

        @Override
        public void paint(Graphics g) {
            g.drawImage(imgBuf, 0, 0, null);
        }

        public void redisplay() {
            Shape clip = visibleGraphic.getClip();
            Rectangle bounds = new Rectangle(0, 0, width, height);
            visibleGraphic.setClip(bounds);
            visibleGraphic.drawImage(imgBuf, 0, 0, null);
            visibleGraphic.setClip(clip);
            repaint();
        }

        public Graphics2D getBackingGraphics() {
            return imgGraphic;
        }

        public void clear() {
            Color save = imgGraphic.getColor();
            imgGraphic.setColor(Color.white);
            imgGraphic.fillRect(0, 0, width, height);
            imgGraphic.setColor(save);
        }
}
