package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

import static uet.oop.bomberman.media.Media.playSound;

public class Bomb extends Entity{
    private long timeSetBomb;
    private boolean isBomb = false;

    private long timer;
    private int isSwap = 1;
    private int isSwap2 = 1;
    private int frame = 0;

    private Entity Middle = new ImageEntity(x/32,y/32,Sprite.bomb_exploded.getFxImage());
    private Entity verticalUp = new ImageEntity(x/32,(y-32)/32,Sprite.explosion_vertical_top_last.getFxImage());
    private Entity verticalDown = new ImageEntity(x/32,(y+32)/32,Sprite.explosion_vertical_down_last.getFxImage());
    private Entity horizontalLeft = new ImageEntity((x-32)/32,y/32,Sprite.explosion_horizontal_left_last.getFxImage());
    private Entity horizontalRight = new ImageEntity((x+32)/32,y/32,Sprite.explosion_horizontal_right_last.getFxImage());

    boolean hasEx = false;
    public static boolean isEx = false;
    public Bomb(int x, int y, Image img, List entities){
        super(x, y, img);
        timeSetBomb = System.currentTimeMillis();
        isBomb = true;
        /**/
    }

    public void setHasEx(boolean hasEx) {
        this.hasEx = hasEx;
    }

    public static void setIsEx(boolean isEx) {
        Bomb.isEx = isEx;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    public boolean isEx() {
        return isEx;
    }


    public void init(int x, int y){
        super.setX(x);
        super.setY(y);
        timeSetBomb = System.currentTimeMillis();
        isBomb = true;
    }

    public boolean isBomb() {
        return isBomb;
    }

    private void frameBomb(){
        if(frame % 8 == 0){
            if (isSwap == 1) {
                super.setImg(Sprite.bomb.getFxImage());
                isSwap = 2;
            } else if (isSwap == 2) {
                super.setImg(Sprite.bomb_1.getFxImage());
                isSwap = 3;
            } else if (isSwap == 3) {
                super.setImg(Sprite.bomb_2.getFxImage());
                isSwap = 4;
            } else {
                super.setImg(Sprite.bomb_1.getFxImage());
                isSwap = 1;
            }
        }
    }
    private boolean checkWall(List stillObjects,int index){
        return stillObjects.get(index) instanceof Wall;
    }
    private void setVertical(List stillObjects){
        if(!checkWall(stillObjects,x/32 + (y/32 - 1) * 31)){
            if(isSwap2 == 1){
                verticalUp.setImg(Sprite.explosion_vertical_top_last.getFxImage());
            }
            else if(isSwap2 == 2 || isSwap2 == 4){
                verticalUp.setImg(Sprite.explosion_vertical_top_last1.getFxImage());
            }
            else if(isSwap2 == 3){
                verticalUp.setImg(Sprite.explosion_vertical_top_last2.getFxImage());
            }
        }
        if(!checkWall(stillObjects, x/32 + (y/32 + 1) * 31)){
            if(isSwap2 == 1){
                verticalDown.setImg(Sprite.explosion_vertical_down_last.getFxImage());
            }
            else if(isSwap2 == 2 || isSwap2 == 4){
                verticalDown.setImg(Sprite.explosion_vertical_down_last1.getFxImage());
            }
            else if(isSwap2 == 3){
                verticalDown.setImg(Sprite.explosion_vertical_down_last2.getFxImage());
            }
        }
    }
    private  void setMiddle(){
        if(isSwap2 == 1){
            Middle.setImg(Sprite.bomb_exploded.getFxImage());
        }
        else if(isSwap2 == 2 || isSwap2 == 4){
            Middle.setImg(Sprite.bomb_exploded1.getFxImage());
        }
        else if(isSwap2 == 3) {
            Middle.setImg(Sprite.bomb_exploded2.getFxImage());
        }
    }
    private void setHorizon(List stillObjects){

        if(!checkWall(stillObjects,x/32 - 1 + (y/32) * 31)){

            if(isSwap2 == 1){
                horizontalLeft.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
            }
            else if(isSwap2 == 2 || isSwap2 == 4){
                horizontalLeft.setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
            }
            else if(isSwap2 == 3){
                horizontalLeft.setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
            }
        }
        if(!checkWall(stillObjects, x/32 + 1 + (y/32) * 31)){
            if(isSwap2 == 1){
                horizontalRight.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
            }
            else if(isSwap2 == 2 || isSwap2 == 4){
                horizontalRight.setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
            }
            else if(isSwap2 == 3){
                horizontalRight.setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
            }
        }
    }

    private void addEx(List entities,List stillObjects){
        entities.add(Middle);
        if(!checkWall(stillObjects, x/32 + (y/32 - 1) * 31)) {
            entities.add(verticalUp);
        }
        if(!checkWall(stillObjects, x/32 + (y/32 + 1) * 31)) {
            entities.add(verticalDown);
        }
        if(!checkWall(stillObjects, x/32 - 1 + (y/32) * 31)) {
            entities.add(horizontalLeft);
        }
        if(!checkWall(stillObjects, x/32 + 1 + (y/32) * 31)) {
            entities.add(horizontalRight);
        }
    }
    private void removeEx(List entities,List stillObjects){
        entities.remove(Middle);
        if(!checkWall(stillObjects, x/32 + (y/32 - 1) * 31)) {
            entities.remove(verticalUp);
        }
        if(!checkWall(stillObjects, x/32 + (y/32 + 1) * 31)) {
            entities.remove(verticalDown);
        }
        if(!checkWall(stillObjects, x/32 - 1 + (y/32) * 31)) {
            entities.remove(horizontalLeft);
        }
        if(!checkWall(stillObjects, x/32 + 1 + (y/32) * 31)) {
            entities.remove(horizontalRight);
        }
    }
    private void setExplosion(List stillObjects){
        if(frame % 8 == 0){
            if (isSwap2 == 1) {
                setHorizon(stillObjects);
                setMiddle();
                setVertical(stillObjects);
                isSwap2 = 2;
            } else if (isSwap2 == 2) {
                setHorizon(stillObjects);
                setMiddle();
                setVertical(stillObjects);
                isSwap2 = 3;
            } else if (isSwap2 == 3) {
                setHorizon(stillObjects);
                setMiddle();
                setVertical(stillObjects);
                isSwap2 = 4;
            } else {
                setHorizon(stillObjects);
                setMiddle();
                setVertical(stillObjects);
                isSwap2 = 1;
            }
        }
    }
    private boolean checkBrick(List stillObjects,int index){
        return stillObjects.get(index) instanceof Brick;
    }
    private void changeImageMap(List stillObjects,int xVal,int yVal){
        Entity object = new Grass(xVal/32, yVal/32, Sprite.grass.getFxImage());
        //stillObjects.remove(xVal/32 + (yVal/32)*31);
        //stillObjects.add(xVal/32 + (yVal/32)*31);
        stillObjects.set(xVal/32 + (yVal/32)*31,object);
        //System.out.println (xVal/32+(yVal/32)*31);
    }
    public void setGrass(List stillObjects){
        if(checkBrick(stillObjects, x/32 + (y/32 - 1) * 31)) {
            changeImageMap(stillObjects,x,y-32);
        }
        if(checkBrick(stillObjects, x/32 + (y/32 + 1) * 31)) {
            changeImageMap(stillObjects,x,y+32);
        }
        if(checkBrick(stillObjects, x/32 - 1 + (y/32) * 31)) {
            changeImageMap(stillObjects,x-32,y);
        }
        if(checkBrick(stillObjects, x/32 + 1 + (y/32) * 31)) {
            changeImageMap(stillObjects,x+32,y);
        }
    }
    public void explosion(List entities, List bomb,List stillObjects){
        timer = System.currentTimeMillis() - timeSetBomb;
        ++frame;
        frameBomb();
        if( timer >= 3000){
            setExplosion(stillObjects);
            if(isEx == false){
                playSound("src/main/resources/sound/explosion.wav");
                entities.remove(this);
                setGrass(stillObjects);
                isEx = true;
            }
            if(hasEx == true){
                removeEx(entities,stillObjects);
                hasEx = false;
                //System.out.println(entities.size());
            }
            else if(hasEx == false){
                addEx(entities,stillObjects);
                hasEx = true;
            }
            if(timer > 4000){
                removeEx(entities,stillObjects);
                hasEx = false;
                bomb.remove(this);
                isBomb = false;
                isEx = false;
            }
        }
    }
    @Override
    public void update() {
    }
}
