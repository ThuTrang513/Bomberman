package uet.oop.bomberman.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.bom;
import static uet.oop.bomberman.BombermanGame.bom;
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
        setStart();
        ++frame_range;
    }

    @Override
    public boolean reset(Entity entity) {

        if(stop() == true && bom.isEmpty() == true) {
            if(frame_range > 1){
                --frame_range;
            }
            return true;
        }
        return false;
    }
}
