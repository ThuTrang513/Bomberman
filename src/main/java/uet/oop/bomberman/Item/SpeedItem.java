package uet.oop.bomberman.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;

public class SpeedItem extends Item {
    public SpeedItem(int x, int y, Image image){
        super(x,y,image);
    }
    public void change(Entity entity) {
        //((Bomber)entity).setStep(((Bomber)entity).getStep() / 2);
        ((Bomber)entity).setStep(4);
    }

    @Override
    public void reset(Entity entity) {
        ((Bomber)entity).setStep(2);
    }

    public void update() {

    }
}
