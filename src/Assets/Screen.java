package Assets;

import java.awt.*;

public class Screen {
    Map map;
    Point mapOffset = new Point(0,0);

    public Screen(){

    }

    public void update(int dx, int dy) {
        mapOffset.x+=dx;mapOffset.y+=dy;
    }

    public  void draw(Graphics2D graphics){
        map.draw(graphics,mapOffset);
    }

    public void setMap(Map map){
        this.map = map;
    }
}
