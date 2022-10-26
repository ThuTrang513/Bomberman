package uet.oop.bomberman.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.bombNum;

public class BombItem extends Item {
    public BombItem(int x, int y, Image image){
        super(x,y,image);
    }

    @Override
    public void change(Entity entity) {
        setStart();
        ++bombNum;
        System.out.println(bombNum);
    }

    @Override
    public boolean reset(Entity entity) {
        if(stop() == true) {

            --bombNum;
            return true;
        }
        return false;
    }

    @Override
    public void update() {

    }
}
