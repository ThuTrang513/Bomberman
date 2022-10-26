package uet.oop.bomberman.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;

public class SpeedItem extends Item {
    private long start;
    public SpeedItem(int x, int y, Image image){
        super(x,y,image);
    }
    public void change(Entity entity) {
        //((Bomber)entity).setStep(((Bomber)entity).getStep() / 2);
        setStart();
        ((Bomber)entity).setStep(4);
    }

    @Override
    public boolean reset(Entity entity) {
        if(stop() == true) {
            ((Bomber)entity).setStep(2);
            return true;
        }
        return false;
    }

    public void update() {

    }
}
