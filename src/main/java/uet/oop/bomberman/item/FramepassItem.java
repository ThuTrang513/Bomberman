package uet.oop.bomberman.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.isFramepass;
import static uet.oop.bomberman.BombermanGame.isPass;

public class FramepassItem extends Item{
    public FramepassItem(int x, int y, Image image){
        super(x,y,image);
    }
    @Override
    public void update() {

    }

    @Override
    public void change(Entity entity) {
        isFramepass = false;
        setStart();
    }

    @Override
    public boolean reset(Entity entity) {
        if(stop() == true) {
            isFramepass = true;
            return true;
        }
        return false;
    }
}
