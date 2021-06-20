package Assets;

import java.awt.*;

public class Map {
    Tile[][] tiles;
    Tile[][] viewSection;

    public Map(Tile[][] tiles){
        this.tiles = tiles;
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
                graphics.drawImage(viewSection[x][y].image,400+offset.x + x * 50 - y * 50, 50+offset.y + 25 * x + y * 25,null);
            }
        }
    }
}
