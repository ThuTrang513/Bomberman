package uet.oop.bomberman.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;

public abstract class Item extends Entity {

    public Item(int x, int y, Image image) {
        super(x,y,image);
    }

    public abstract void change(Entity entity);
    public abstract void reset(Entity entity);

}
