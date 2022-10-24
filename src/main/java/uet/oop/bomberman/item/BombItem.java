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
        ++bombNum;
        System.out.println(bombNum);
    }

    @Override
    public void reset(Entity entity) {
        --bombNum;
    }

    @Override
    public void update() {

    }
}
