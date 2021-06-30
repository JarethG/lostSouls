package Graphics;

import Assets.Map;
import Assets.Tile;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class MapParser {
    HashMap<String, BufferedImage> tileImages;

    public MapParser(){
        ImageLoader imageLoader = new ImageLoader();
        tileImages = imageLoader.loadImages("./src/Resources/Tiles");
    }

    public Map loadMap(String pathName){
        System.out.println("loading map...");

        InputStream input = MapParser.class.getResourceAsStream(pathName);
        BufferedReader csvReader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
//        String[][] mapIds = csvReader.lines().map(line-> line.split(",")).toArray(size -> new String[size][size]);
        String[][] mapIds = csvReader.lines().map(line-> line.split(",")).toArray(size -> new String[size][size]);
        ArrayList<Tile[]>tiles = new ArrayList<>();
        for(String[] array : mapIds){
            tiles.add(Arrays.stream(array).map(id -> new Tile(id)).toArray(Tile[]::new));
        }
        String[] unique =  Arrays.stream(mapIds).flatMap(array -> Arrays.stream(array)).distinct().toArray(String[]::new);
        HashMap<String,BufferedImage> mapAssets = new HashMap<>();
        for(String s : unique){
            mapAssets.put(s,tileImages.get(s));
        }
        printArray(mapIds);
        System.out.println("map is loaded!");
        return new Map(tiles.toArray(Tile[][]::new),mapAssets);
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
        String s =
        Arrays.stream(data)
                .map(t -> t.getId())
                .collect(Collectors.joining(","));
        return s;
    }

    public void saveAsMap(String pathName){

    }

    public Map loadEmptyMap(int size){
        Tile[][] tiles = new Tile[size][size];
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                tiles[x][y] = new Tile(tileImages.get("empty"),(x - y) * 32,(x + y) * 16,64,32,"empty");
            }
        }
        centre(tiles);

        return new Map(tiles, new HashMap<>() {{
            put("s", tileImages.get("empty"));
        }});
    }

    public Map loadDummyMap(int size){
        BufferedImage[][] images = loadDummyImages(size);
        Tile[][] tiles = new Tile[size][size];
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                tiles[x][y] = new Tile(images[x][y],(x - y) * 32,(x + y) * 16,64,32,"");
            }
        }
        centre(tiles);
        return new Map(tiles,tileImages);
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

    public void printArray(String[][] array){
        for(String[] x : array){
            for (String s : x){
                System.out.print(s);
            }
            System.out.println();
        }
    }
}
