package uet.oop.bomberman.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.isPass;
import static uet.oop.bomberman.entities.Bomb.frame_range;

public class WallpassItem extends Item{
    public WallpassItem(int x, int y, Image image){
        super(x,y,image);
    }
    @Override
    public void update() {

    }

    @Override
    public void change(Entity entity) {
        isPass = true;
    }

    @Override
    public boolean reset(Entity entity) {
        if(stop() == true) {
            isPass = false;
            return true;
        }
        return false;
    }
}
