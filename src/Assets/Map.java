package Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Map {
    Tile[][] tiles;
    Tile[][] viewSection;
    HashMap<String,BufferedImage> assets;


    public Map(Tile[][] tiles, HashMap<String,BufferedImage> assets){
        this.tiles = tiles;
        this.assets=assets;
        setSubSection(0,0,10);
    }

    public Tile[][] setSubSection(int x, int y, int size){
        viewSection = new Tile[size][size];
        for(int i = 0; i < size; i++){
            for( int j = 0; j < size; j++){
                viewSection[i][j] = tiles[x+i][y+j];
            }
        }
        return viewSection;
    }

    public void draw(Graphics2D graphics,Point offset){
        for (int y = 0; y <  viewSection.length; y++) {
            for (int x = 0; x < viewSection.length; x++) {
//                viewSection[x][y].draw(graphics);
                graphics.drawImage(assets.get(viewSection[x][y].name),offset.x-32 + x * 32 - y * 32, offset.y + 16 * x + y * 16,null);
//                graphics.drawRect(offset.x + x * 32 - y * 32, offset.y + 16 * x + y * 16,64,32);
            }
        }
    }

    public Tile[][] getTiles(){
        return tiles;
    }
}
