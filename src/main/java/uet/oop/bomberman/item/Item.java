package uet.oop.bomberman.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;

public abstract class Item extends Entity {

    private Long start = Long.valueOf(1);
    public Item(int x, int y, Image image) {
        super(x,y,image);
    }

    public abstract void change(Entity entity);
    public abstract boolean reset(Entity entity);

    public void setStart() {
        this.start = System.currentTimeMillis()/1000;
    }
    public boolean stop() {
        Long now = System.currentTimeMillis()/1000;
        if(now - start > 15 ) {
            return true;
        }
        return false;
    }
}
