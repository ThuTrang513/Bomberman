package uet.oop.bomberman.Enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.*;

public abstract class Enermy extends Entity {
    boolean isDead=false;
    Enermy(int x, int y, Image image){
        super(x,y,image);
    }

    public void colissBom() {
        bom.forEach(e->{
            if (e.isEx())
                if ((Math.abs(x-e.getX())<65 && y==e.getY()) || (Math.abs(y- e.getY())<65 && x==e.getX())) {
                    isDead=true;
                }
        });
    }
    public void colissBomber(){
        if ((Math.abs(x-player.getX())<33 && y==player.getY()) || (Math.abs(y- player.getY())<33 && x==player.getX())) {
            player.setDiedFame();
            isPause=true;
        }
    }
    public abstract void setDiedFame();
}
