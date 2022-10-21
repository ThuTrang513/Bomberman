package uet.oop.bomberman.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;


public class Oneal extends Enermy {
    int cntCircle=0,right=0,left=0,cntToDie=0;
    boolean ok=false,ud=false,rl=false;
    public Oneal(int x, int y, Image image){
        super(x,y,image);
    }
    @Override
    public void update() {
        if (!isDead) {
            if (player.getX() == x) {
                if (player.getY() - y < 0) {
                    ok = true;
                } else {
                    ok = false;
                }
                upDown();
                cntCircle = 3;
            } else if (player.getY() == y) {
                if (player.getX() - x < 0) {
                    ok = true;
                } else {
                    ok = false;
                }
                rightLeft();
                cntCircle = 0;
            } else {

                ranRun(cntCircle);
            }

            colissBomber();
            colissBom();
        } else setDiedFame();
    }

    public void setDiedFame() {
        if (cntToDie<4) {
            setImg(Sprite.oneal_dead.getFxImage());
            cntToDie++;
        }
        else if (cntToDie<8){
            setImg(Sprite.mob_dead1.getFxImage());
            cntToDie++;
        } else if (cntToDie<12){
            setImg(Sprite.mob_dead2.getFxImage());
            cntToDie++;
        } else if (cntToDie<15){
            setImg(Sprite.mob_dead3.getFxImage());
            cntToDie++;
        } else{
            enermy.remove(this);
        }
    }

    public void ranRun(int cnt){
        if (cntCircle>6) cntCircle=0;
        if (cntCircle>3) {
            upDown();
        }
        else rightLeft();
    }
    public void rightLeft(){
        if (checkR(x,y) && !ok) {
            x++;
            if (right<8) {img=Sprite.oneal_right1.getFxImage(); right++;}
            else if (right<16){
                img= Sprite.oneal_right2.getFxImage(); right++;
            } else if (right<24) {
                img=Sprite.oneal_right3.getFxImage(); right++;
            } else right=0;
        }
        else {ok=true;}
        if (ok) {
            if (checkUL(x,y)) {
                x--;

                if (left<8) {img=Sprite.oneal_left1.getFxImage(); left++;}
                else if (left<16){
                    img=Sprite.oneal_left2.getFxImage(); left++;
                } else if (left<24) {
                    img=Sprite.oneal_left3.getFxImage(); left++;
                } else left=0;
            }
            else {ok=false; x++;}
            if (!ok) {cntCircle++; }

        }
    }
    private void upDown(){
        if (checkD(x,y) && !ok) {
            y++;
        }
        else {ok=true;}
        if (ok) {
            if (checkUL(x,y)) y--;
            else {ok=false; y++;}
            if(!ok) cntCircle++;
        }
    }

}
