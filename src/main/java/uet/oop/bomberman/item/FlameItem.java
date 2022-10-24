package uet.oop.bomberman.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.entities.Bomb.frame_range;

public class FlameItem extends Item {
    public FlameItem(int x, int y, Image image){
        super(x,y,image);
    }
    @Override
    public void update() {

    }

    @Override
    public void change(Entity entity) {
        ++frame_range;
    }

    @Override
    public void reset(Entity entity) {
        --frame_range;
    }
}
