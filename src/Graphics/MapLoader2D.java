package Graphics;

import Assets.Map;
import Assets.Tile;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MapLoader2D {
    HashMap<String, BufferedImage> tileImages;

    public MapLoader2D(){
        ImageLoader imageLoader = new ImageLoader();
        tileImages = imageLoader.loadImages("./src/Resources/Tiles");
    }

    public Map loadMap(String pathName){
        return null;
    }

    public BufferedImage[][] loadDummyImages(int size){
        BufferedImage[][] imageArray = new BufferedImage[size][size];
        List<BufferedImage> images = tileImages.values().stream().toList();
        Random r = new Random();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                imageArray[i][j] = images.get(r.nextInt(images.size()));
            }
        }
        return imageArray;
    }
}
