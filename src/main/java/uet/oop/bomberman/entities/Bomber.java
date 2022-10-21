package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

import static uet.oop.bomberman.media.Media.playSound;

public class Bomber extends Entity {
    private int countToRun = 0;
    private int swap = 1;
    private int died = 1;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    public int getSwap() {
        return swap;
    }

    public void setSwap(int swap) {
        this.swap = swap;
    }

    public void setCountToRun(int countToRun) {
        this.countToRun = countToRun;
    }

    public int getCountToRun() {
        return countToRun;
    }

    public boolean checkRun(List stillObjects, int k){
        return stillObjects.get(k) instanceof Grass;
    }
    public void UP(){
        if(y % 8 == 0) {
            if (getSwap() == 1) {
                super.setImg(Sprite.player_up_1.getFxImage());
                setSwap(2);
            } else if (getSwap() == 2) {
                super.setImg(Sprite.player_up.getFxImage());
                setSwap(3);
            } else if (getSwap() == 3) {
                super.setImg(Sprite.player_up_2.getFxImage());
                setSwap(4);
            } else {
                super.setImg(Sprite.player_up.getFxImage());
                setSwap(1);
            }
        }
    }
    public void DOWN(){
        if(y % 8 == 0) {
            if (getSwap() == 1) {
                super.setImg(Sprite.player_down_1.getFxImage());
                setSwap(2);
            } else if (getSwap() == 2) {
                super.setImg(Sprite.player_down.getFxImage());
                setSwap(3);
            } else if (getSwap() == 3) {
                super.setImg(Sprite.player_down_2.getFxImage());
                setSwap(4);
            } else {
                super.setImg(Sprite.player_down.getFxImage());
                setSwap(1);
            }
        }
    }

    public void RIGHT(){
        if(x % 8 == 0){
            if (getSwap() == 1) {
                super.setImg(Sprite.player_right_1.getFxImage());
                setSwap(2);
            } else if (getSwap() == 2) {
                super.setImg(Sprite.player_right.getFxImage());
                setSwap(3);
            } else if (getSwap() == 3) {
                super.setImg(Sprite.player_right_2.getFxImage());
                setSwap(4);
            } else {
                super.setImg(Sprite.player_right.getFxImage());
                setSwap(1);
            }
        }
    }

    public void LEFT(){
        if(x % 8 == 0) {
            if (getSwap() == 1) {
                super.setImg(Sprite.player_left_1.getFxImage());
                setSwap(2);
            } else if (getSwap() == 2) {
                super.setImg(Sprite.player_left.getFxImage());
                setSwap(3);
            } else if (getSwap() == 3) {
                super.setImg(Sprite.player_left_2.getFxImage());
                setSwap(4);
            } else {
                super.setImg(Sprite.player_left.getFxImage());
                setSwap(1);
            }
        }
    }

    public void setDiedFame(){
        if(died == 1){
            super.setImg(Sprite.player_dead1.getFxImage());
            died = 2;
        }
        else if(died == 2){
            super.setImg(Sprite.player_dead2.getFxImage());
            died = 3;
        }
        else{
            super.setImg(Sprite.player_dead3.getFxImage());
            died = 1;
            BombermanGame.isPause=true;
        }
    }
    public void bomber_died(Bomb bom){
        if(bom.isEx()){
            if((x >= bom.getX() - 32 && x <= bom.getX() + 32 && y == bom.getY())
                    || (x == bom.getX() && y <= bom.getY() + 32 && y >= bom.getY() - 32)){
                playSound("src/main/resources/sound/player_die.wav");
                setDiedFame();
                /*if(!bom.isEx()){
                    return true;
                }*/
            }
        }
        //return false;
    }
    @Override
    public void update(){

    }
}
