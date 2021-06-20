package Main;

import Assets.Map;
import Assets.Tile;
import Graphics.MapLoader2D;

import java.awt.*;
import java.awt.event.*;
import java.util.Set;

public class MapMaker implements Game {

    UI GUI;
    Map map;
    int size = 10;
    int ox = 200;
    int oy = 50;

    public static void main(String[] args){
        new MapMaker();
    }

    MapMaker(){
        GUI   = new UI(500,500,"MapMaker");
        setMouseListener();
        setKeyListener();
        MapLoader2D loader = new MapLoader2D();

        map = loader.loadEmptyMap(10);
        GUI.start(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D graphics) {
        map.draw(graphics,new Point(ox,oy));
    }

    private void setMouseListener(){
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setTile(e.getX(),e.getY());
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }
        };
        GUI.setMouseListener(mouseAdapter);
    }

    private void setKeyListener() {
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
//                    case KeyEvent.VK_F -> frameOn = !frameOn;
//                    case KeyEvent.VK_G -> gridOn = !gridOn;
                }
            }
        };
        GUI.setKeyListener(keyListener);
    }

    private void setTile(int ex, int ey){
        int fx = ((ex-ox)+2*(ey-oy))/64;
        int fy = (2*(ey-oy) - (ex-ox))/64;
        System.out.println(fx + " | " + fy);
    }

    private void setTiles(Set<Integer> x, Set<Integer> y){

    }
}
