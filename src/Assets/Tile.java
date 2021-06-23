package Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile extends GameObject{
    Polygon boundary;

    public Tile(BufferedImage image,int x, int y,int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        int[] xPoints = new int[]{width/2,width,width/2,0};
        int[] yPoints = new int[]{0,height/2,height,height/2};
        boundary = new Polygon(xPoints,yPoints,4);
    }

    public boolean isClicked(int ex, int ey) {
        return boundary.contains(ex-x,ey-y);
    }

    public void move(int dx,int dy){
        x+=dx;
        y+=dy;
    }

    public void draw(Graphics2D g){
        g.drawImage(image,x,y,null);
    }

    public void setImage(BufferedImage i){
        this.image = i;
    }

    public String getId(){
//        return id;
        return "X";
    }
}
