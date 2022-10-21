package uet.oop.bomberman.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.media.Media.playSound;

public abstract class Enermy extends Entity {
    boolean isDead=false;
    Enermy(int x, int y, Image image){
        super(x,y,image);
    }


    public void colissBom() {
        bom.forEach(e->{
            if (e.isEx())
                if ((Math.abs(x-e.getX())<60 && y==e.getY()) || (Math.abs(y- e.getY())<60 && x==e.getX())) {
                    isDead=true;
                }
        });
    }
    public void colissBomber(){
        if ((Math.abs(x-player.getX())<33 && y==player.getY()) || (Math.abs(y- player.getY())<33 && x==player.getX())) {
            player.setDiedFame();
            if (!isPause) playSound("src/main/resources/sound/player_die.wav");
            isPause=true;
        }
    }
    boolean checkUL(int x,int y){
        for (int i=0;i<bom.size();i++){
            if (bom.get(i).getX()/32==x/32 && bom.get(i).getY()/32==(y/32)) return false;
        }
        return stillObjects.get((y / 32)* WIDTH + x/32) instanceof Grass ;
    }
    boolean checkD(int x,int y){
        for(int i=0;i<bom.size();i++){
            if (bom.get(i).getX()/32==x/32 && bom.get(i).getY()/32==y/32+1) return false;
        }
        return stillObjects.get((y / 32+1)*31  + x/32) instanceof Grass;
    }
    public boolean checkR(int x,int y){
        for(int i=0;i<bom.size();i++){
            if(bom.get(i).getX()/32==x/32+1 && bom.get(i).getY()/32==(y/32)) return false;
        }
        return stillObjects.get((y / 32)*31  + x/32+1) instanceof Grass;
    }
    public abstract void setDiedFame();
    public abstract void update();

}
