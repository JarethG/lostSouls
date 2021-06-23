package Graphics;

import Assets.Map;
import Assets.Tile;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MapParser {
    HashMap<String, BufferedImage> tileImages;

    public MapParser(){
        ImageLoader imageLoader = new ImageLoader();
        tileImages = imageLoader.loadImages("./src/Resources/Tiles");
    }

    public Map loadMap(String pathName){
        return null;
    }

    public void saveMap(Map map) {
        System.out.println("saving map...");
        Tile[][] tiles = map.getTiles();

        try (PrintWriter pw = new PrintWriter("./src/Resources/Maps/new.csv")) {
            Arrays.stream(tiles)
                    .map(this::convertToCSV)
                    .forEach(pw::println);
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("failed map saving!");
            return;
        }
        System.out.println("save complete!");
    }

    public String convertToCSV(Tile[] data) {
        return  Arrays.stream(data)
                .map(t -> t.getId())
                .collect(Collectors.joining(","));
    }

    public void saveAsMap(String pathName){

    }

    public Map loadEmptyMap(int size){
        Tile[][] tiles = new Tile[size][size];
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                tiles[x][y] = new Tile(tileImages.get("empty"),(x - y) * 32,(x + y) * 16,64,32);
            }
        }
        centre(tiles);
        return new Map(tiles);
    }

    public Tile[][] loadDummyMap(int size){
        BufferedImage[][] images = loadDummyImages(size);
        Tile[][] tiles = new Tile[size][size];
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                tiles[x][y] = new Tile(tileImages.get("empty"),(x - y) * 32,(x + y) * 16,64,32);
            }
        }
        centre(tiles);
        return tiles;
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

    public void centre(Tile[][] tiles){
        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles.length; y++){
                tiles[x][y].move(300,50);
            }
        }
    }
}
