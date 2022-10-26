package uet.oop.bomberman.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.enermy;

public class Minvo extends Enermy {
    public Minvo(int x, int y, Image image) {
        super(x, y, image);
    }
    boolean ok=false;
    int cntCircle=0,cntToDie=0;
    int right=0;
    int left=0;
    @Override
    public void setDiedFame() {
        if (cntToDie<4) {
            setImg(Sprite.minvo_dead.getFxImage());
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
        } else if (cntToDie<100){
            cntToDie++;
        } else {
            enermy.remove(this);
            enermy.add(new Oneal(x/32,y/32+1,Sprite.oneal_right1.getFxImage()));
            enermy.add(new Oneal(x/32,y/32,Sprite.oneal_left1.getFxImage()));

        }
    }

    @Override
    public void update() {
        ranRun(cntCircle);
        colissBomber();
        colissBom();
    }public void ranRun(int cnt) {
        if (!isDead) {
            if (cntCircle == 6) cntCircle = 0;
            if (cntCircle > 3) {
                upDown();
            } else rightLeft();
        } else {
            setDiedFame();
        }
    }public void rightLeft(){
        if (checkR(x,y) && !ok) {
            x++;
            if (right<8) {img= Sprite.minvo_right1.getFxImage(); right++;}
            else if (right<16){
                img=Sprite.minvo_right2.getFxImage(); right++;
            } else if (right<24) {
                img=Sprite.minvo_right3.getFxImage(); right++;
            } else right=0;
        }
        else {ok=true;}
        if (ok) {
            if (checkUL(x,y)) {
                x--;

                if (left<8) {img=Sprite.minvo_left1.getFxImage(); left++;}
                else if (left<16){
                    img=Sprite.minvo_left2.getFxImage(); left++;
                } else if (left<24) {
                    img=Sprite.minvo_left3.getFxImage(); left++;
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
