package Main;

import Assets.Map;
import Assets.Tile;
import Graphics.MapParser;
import Graphics.ImageLoader;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class MapMaker implements Game {

    UI GUI;
    Map map;
    int size = 10;
    int ox = 200;
    int oy = 50;
    HashMap<String, BufferedImage> tileImages;
    MapParser parser;



    public static void main(String[] args){
        new MapMaker();
    }

    MapMaker(){
        GUI   = new UI(700,700,"MapMaker");
        setMouseListener();
        setKeyListener();
        parser = new MapParser();
        tileImages = new ImageLoader().loadImages("./src/Resources/Tiles");

        map = parser.loadEmptyMap(10);
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
                setTile(e);
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
                    case KeyEvent.VK_S -> parser.saveMap(map);
                    case KeyEvent.VK_L -> parser.loadMap("");
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

    private void setTile(MouseEvent e){
        Tile[][] temp = map.getTiles();
        Arrays.stream(temp).flatMap(t -> Arrays.stream(t)).forEach(t-> {if(t.isClicked(e.getX(),e.getY()) ) replace(t);});


    }

    private Tile replace(Tile old){
        old.setImage(tileImages.get("water"));
        return old;
    }

    private void setTiles(Set<Integer> x, Set<Integer> y){

    }
}
