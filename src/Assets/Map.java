package Assets;

import java.awt.*;

public class Map {
    Tile[][] tiles;

    public Map(Tile[][] tiles){
        this.tiles = tiles;
    }

    public void draw(Graphics2D graphics,Point offset){
        for (int y = 0; y <  tiles.length; y++) {
            for (int x = 0; x < tiles.length; x++) {
                graphics.drawImage(tiles[x][y].image,400+offset.x + x * 50 - y * 50, 50+offset.y + 25 * x + y * 25,null);
            }
        }
    }
}
