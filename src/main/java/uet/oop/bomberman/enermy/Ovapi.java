package uet.oop.bomberman.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.Menu.scoreInt;
import static uet.oop.bomberman.media.Media.playSound;

public class Ovapi extends Enermy {
    int cntCircle=0,cntToDie=0;
    int right=0;
    int left=0;
    boolean ok=false;
    int ranVelocity=1;
     int ranVelocityY=1;
    Rectangle r1= new Rectangle(x,y,32,32);
    Rectangle r2= new Rectangle(player.getX(),player.getY(),32,32);

    public Ovapi(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void update() {
        ranRun(cntCircle);
        colissBomber();
        colissBom();
    }
    public void setDiedFame() {
        if (cntToDie<4) {
            setImg(Sprite.ovapi_dead.getFxImage());
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
            scoreInt+=10;
        }
    }

    @Override
    public void colissBomber() {
        r2.x= player.getX();
        r2.y= player.getY();
        r1.x=x; r1.y=y;
        if (r1.x < r2.x + r2.width && r1.x + r1.width > r2.x && r1.y < r2.y + r2.height && r1.height + r1.y > r2.y) {

            if (!isPause) playSound("src/main/resources/sound/player_die.wav");
            isPause=true;
            player.setDiedFame();
        }
    }

    public boolean checkBound(int x, int y) {
        return x>0 && y>0&&
                x<Sprite.SCALED_SIZE * WIDTH && y< Sprite.SCALED_SIZE * HEIGHT;
    }

    private void ranRun(int cnt) {
        if (!isDead) {
            if (cntCircle == 100) {
                cntCircle = 0;
                ranVelocity= (int) (Math.random()*10%2+1);
                if (ranVelocity%2==0) ranVelocityY=-ranVelocity;
                else ranVelocityY=ranVelocity;
                //if (ranVelocity==2) ok=true; else ok=false;
                //System.out.println(ranVelocity+" "+x+" "+y+" "+cntCircle);
            }
            if (cntCircle >= 0) {
                cntCircle++;
                upDown();
                rightLeft();
            }//} else ;
        } else setDiedFame();
    }
    public void rightLeft(){
        if (checkBound(x,y) && !ok) {
            //ranVelocity=Math.abs(ranVelocity);
            //ranVelocity= (int) (Math.random()*10%2+1);
            //ranVelocity=-ranVelocity;
            x+=ranVelocity;
            if (right<8) {img=Sprite.ovapi_right1.getFxImage(); right++;}
            else if (right<16){
                img=Sprite.ovapi_right2.getFxImage(); right++;
            } else if (right<24) {
                img=Sprite.ovapi_right3.getFxImage(); right++;
            } else right=0;
        }
        else {ok=true; x-=2; y-=2;}
        if (ok) {
            if (checkBound(x,y)) {
                //ranVelocity= (int) (Math.random()*10%2+1);
                x-=ranVelocity;
                if (left<8) {img=Sprite.ovapi_left1.getFxImage(); left++;}
                else if (left<16){
                    img=Sprite.ovapi_left2.getFxImage(); left++;
                } else if (left<24) {
                    img=Sprite.ovapi_left3.getFxImage(); left++;
                } else left=0;
            }
            else {ok=false;
                x+=2; y+=2;
                //ranVelocity= (int) (Math.random()*10%2+1);
                x+=ranVelocity;}
            //if (!ok) {cntCircle++; }

        }
    }
    private void upDown(){
        //if (ranVelocity%2==1) ranVelocity=-ranVelocity;
        if (checkBound(x,y) && !ok) {
            y+=ranVelocityY;
        }
        else {ok=true; x-=2; y-=2; }
        if (ok) {
            if (checkBound(x,y)) y-=ranVelocityY;
            else {ok=false; y+=ranVelocityY;x=100*ranVelocity;y=160;}
            //if(!ok) cntCircle++;
        }
    }
}
