package Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImageLoader {

    public HashMap<String,BufferedImage> loadImages(String pathName){
        HashMap<String, BufferedImage> images = new HashMap<>();
        Path path = Paths.get(pathName);//path to directory
        List<Path> paths = findByFileExtension(path, ".png");//list of paths to images in directory
        try {
            for(Path p : paths) {
                BufferedImage image = ImageIO.read(new File(p.toString()));
                String imageName = removeExtension(p.getFileName().toString(), ".png");
                System.out.println("image name : " + imageName);
                images.put(imageName, image);
            }
        }catch(Exception e){}
        return images;
    }

    public BufferedImage[] loadSprite(int size, int width, int height, String path) {

        BufferedImage[] sprite = new BufferedImage[size];

        try {
            BufferedImage spriteSheet = ImageIO.read(new File(path));
            for(int i = 0; i < size; i++) {
                sprite[i] = spriteSheet.getSubimage(i*width,0,width,height);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public List<Path> findByFileExtension(Path path, String fileExtension){

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<Path> result = null;
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(Files::isRegularFile)   // is a file
                    .filter(p -> p.getFileName().toString().endsWith(fileExtension))
                    .collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }

    public String removeExtension(String string, String extension){
        return string.substring(0,string.lastIndexOf(extension));
    }
}
