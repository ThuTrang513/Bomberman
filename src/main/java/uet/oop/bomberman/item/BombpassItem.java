package uet.oop.bomberman.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.bom;
import static uet.oop.bomberman.BombermanGame.isBombpass;
import static uet.oop.bomberman.entities.Bomb.frame_range;

public class BombpassItem extends Item{
    public BombpassItem(int x, int y, Image image){
        super(x,y,image);
    }
    @Override
    public void update() {

    }

    @Override
    public void change(Entity entity) {
        isBombpass = false;
        setStart();
    }

    @Override
    public boolean reset(Entity entity) {
        if(stop() == true) {
            isBombpass = true;
            return true;
        }
        return false;
    }
}
