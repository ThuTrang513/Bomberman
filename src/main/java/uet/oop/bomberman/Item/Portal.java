package uet.oop.bomberman.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Portal extends Entity {
    public static boolean isPortal = false;
    public Portal(int x, int y, Image img) {
        super(x, y, img);
        isPortal=false;
    }


    @Override
    public void update() {
        System.out.println("Portal1");
        //if (BombermanGame.stillObjects.get(33) instanceof Grass){
            isPortal=true;
            System.out.println("Portal");
        //}
    }
}
