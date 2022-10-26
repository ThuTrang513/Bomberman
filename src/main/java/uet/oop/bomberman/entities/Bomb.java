package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.item.*;
import uet.oop.bomberman.BombermanGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static uet.oop.bomberman.BombermanGame.stillObjects;

import static uet.oop.bomberman.media.Media.playSound;

public class Bomb extends Entity{
    private long timeSetBomb;
    private boolean isBomb = false;

    private long timer;
    private int isSwap = 1;
    private int isSwap2 = 1;
    private int frame = 0;

    public static int frame_range = 1;

    public int frame_range_s;
    private Entity Middle = new ImageEntity(x/32,y/32,Sprite.bomb_exploded.getFxImage());
    private Entity verticalUpLast = new ImageEntity(x/32,(y-32 * frame_range)/32,Sprite.explosion_vertical_top_last.getFxImage());
    private Entity verticalDownLast = new ImageEntity(x/32,(y+32 * frame_range)/32,Sprite.explosion_vertical_down_last.getFxImage());
    private Entity horizontalLeftLast = new ImageEntity((x-32 * frame_range)/32,y/32,Sprite.explosion_horizontal_left_last.getFxImage());
    private Entity horizontalRightLast = new ImageEntity((x+32 * frame_range)/32,y/32,Sprite.explosion_horizontal_right_last.getFxImage());
    private List<Entity> verticalUp = new ArrayList<>();
    private List<Entity> verticalDown = new ArrayList<>();
    private List<Entity> horizontalLeft = new ArrayList<>();
    private List<Entity> horizontalRight = new ArrayList<>();


    boolean hasEx = false;
    boolean isEx = false;
    public Bomb(int x, int y, Image img, List entities){
        super(x, y, img);
        isEx=false;
        timeSetBomb = System.currentTimeMillis();
        isBomb = true;
        this.horizontalImg();
        this.verticalImg();
        frame_range_s = frame_range;
        /**/
    }

    public void setHasEx(boolean hasEx) {
        this.hasEx = hasEx;
    }

    public void setIsEx(boolean isEx) {
        this.isEx = isEx;
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
        if(index >= 0 && index < stillObjects.size()){
            return stillObjects.get(index) instanceof Wall;
        }
        else return false;
    }private void verticalImg() {
        for(int i = 1; i < frame_range; ++i) {
            Entity up  = new ImageEntity(x/32,(y-32 * i)/32,Sprite.explosion_vertical.getFxImage());
            Entity down = new ImageEntity(x/32,(y+32 * i)/32,Sprite.explosion_vertical.getFxImage());
            verticalUp.add(up);
            verticalDown.add(down);
        }
    }
    private void horizontalImg() {
        for(int i = 1; i < frame_range; ++i) {
            Entity left  = new ImageEntity((x-32 * i)/32,y/32,Sprite.explosion_horizontal.getFxImage());
            Entity right = new ImageEntity((x+32 * i)/32,y/32,Sprite.explosion_horizontal.getFxImage());
            horizontalLeft.add(left);
            horizontalRight.add(right);
        }
    }
    private void setVertical(List stillObjects){
        for (int i = 1; i < frame_range_s; ++ i){
            if(!checkWall(stillObjects,x/32 + (y/32 - i) * 31)){
                if(isSwap2 == 1){
                    verticalUp.get(i-1).setImg(Sprite.explosion_vertical.getFxImage());
                }
                else if(isSwap2 == 2 || isSwap2 == 4){
                    verticalUp.get(i-1).setImg(Sprite.explosion_vertical1.getFxImage());
                }
                else if(isSwap2 == 3){
                    verticalUp.get(i-1).setImg(Sprite.explosion_vertical2.getFxImage());
                }
            }
        }
        if(!checkWall(stillObjects,x/32 + (y/32 - frame_range_s) * 31)){
            if(isSwap2 == 1){
                verticalUpLast.setImg(Sprite.explosion_vertical_top_last.getFxImage());
            }
            else if(isSwap2 == 2 || isSwap2 == 4){
                verticalUpLast.setImg(Sprite.explosion_vertical_top_last1.getFxImage());
            }
            else if(isSwap2 == 3){
                verticalUpLast.setImg(Sprite.explosion_vertical_top_last2.getFxImage());
            }
        }
        for (int i = 1; i < frame_range_s; ++ i){
            if(!checkWall(stillObjects,x/32 + (y/32 + i) * 31)){
                if(isSwap2 == 1){
                    verticalDown.get(i-1).setImg(Sprite.explosion_vertical.getFxImage());
                }
                else if(isSwap2 == 2 || isSwap2 == 4){
                    verticalDown.get(i-1).setImg(Sprite.explosion_vertical1.getFxImage());
                }
                else if(isSwap2 == 3){
                    verticalDown.get(i-1).setImg(Sprite.explosion_vertical2.getFxImage());
                }
            }
        }
        if(!checkWall(stillObjects, x/32 + (y/32 + frame_range_s) * 31)){
            if(isSwap2 == 1){
                verticalDownLast.setImg(Sprite.explosion_vertical_down_last.getFxImage());
            }
            else if(isSwap2 == 2 || isSwap2 == 4){
                verticalDownLast.setImg(Sprite.explosion_vertical_down_last1.getFxImage());
            }
            else if(isSwap2 == 3){
                verticalDownLast.setImg(Sprite.explosion_vertical_down_last2.getFxImage());
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
        for (int i = 1; i < frame_range_s; ++ i) {
            if(!checkWall(stillObjects,x/32 - i + (y/32) * 31)){
                if(isSwap2 == 1){
                    horizontalLeft.get(i-1).setImg(Sprite.explosion_horizontal.getFxImage());
                }
                else if(isSwap2 == 2 || isSwap2 == 4){
                    horizontalLeft.get(i-1).setImg(Sprite.explosion_horizontal1.getFxImage());
                }
                else if(isSwap2 == 3){
                    horizontalLeft.get(i-1).setImg(Sprite.explosion_horizontal2.getFxImage());
                }
            }
        }
        if(!checkWall(stillObjects,x/32 - frame_range_s + (y/32) * 31)){

            if(isSwap2 == 1){
                horizontalLeftLast.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
            }
            else if(isSwap2 == 2 || isSwap2 == 4){
                horizontalLeftLast.setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
            }
            else if(isSwap2 == 3){
                horizontalLeftLast.setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
            }
        }
        for (int i = 1; i < frame_range_s; ++ i) {
            if(!checkWall(stillObjects,x/32 + i + (y/32) * 31)){
                if(isSwap2 == 1){
                    horizontalRight.get(i-1).setImg(Sprite.explosion_horizontal.getFxImage());
                }
                else if(isSwap2 == 2 || isSwap2 == 4){
                    horizontalRight.get(i-1).setImg(Sprite.explosion_horizontal1.getFxImage());
                }
                else if(isSwap2 == 3){
                    horizontalRight.get(i-1).setImg(Sprite.explosion_horizontal2.getFxImage());
                }
            }
        }
        if(!checkWall(stillObjects, x/32 + frame_range_s + (y/32) * 31)){
            if(isSwap2 == 1){
                horizontalRightLast.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
            }
            else if(isSwap2 == 2 || isSwap2 == 4){
                horizontalRightLast.setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
            }
            else if(isSwap2 == 3){
                horizontalRightLast.setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
            }
        }
    }

    private void addEx(List entities,List stillObjects){
        entities.add(Middle);
        int isStop = 0;
        for (int i = 1; i < frame_range_s; ++ i) {
            if(!checkWall(stillObjects, x/32 + (y/32 - i) * 31)) {
                entities.add(verticalUp.get(i-1));
                /*if(checkBrick(stillObjects,x/32 + (y/32 - i) * 31)){
                    isStop = i;
                    break;
                    }
                 */
            }
            else{
                isStop = i;
                break;
            }
        }
        if(isStop == 0 && !checkWall(stillObjects, x/32 + (y/32 - frame_range_s) * 31)) {
            entities.add(verticalUpLast);
        }

        isStop = 0;
        for (int i = 1; i < frame_range_s; ++ i) {
            if(!checkWall(stillObjects, x/32 + (y/32 + i) * 31)) {
                entities.add(verticalDown.get(i-1));
               /* if(checkBrick(stillObjects,x/32 + (y/32 + i) * 31)){
                    isStop = i;
                    break;
                }*/
            }
            else{
                isStop = i;
                break;
            }
        }
        if(isStop == 0 && !checkWall(stillObjects, x/32 + (y/32 + frame_range_s) * 31)) {
            entities.add(verticalDownLast);
        }

        isStop = 0;
        for (int i = 1; i < frame_range_s; ++ i) {
            if(!checkWall(stillObjects, x/32 - i + (y/32) * 31)) {
                entities.add(horizontalLeft.get(i-1));
                /*if(checkBrick(stillObjects,x/32 - i + (y/32) * 31)){
                    isStop = i;
                    break;
                }*/
            }
            else{
                isStop = i;
                break;
            }
        }
        if(isStop == 0 && !checkWall(stillObjects, x/32 - frame_range_s + (y/32) * 31)) {
            entities.add(horizontalLeftLast);
        }

        isStop = 0;
        for (int i = 1; i < frame_range_s; ++ i) {
            if(!checkWall(stillObjects, x/32 + i + (y/32) * 31)) {
                entities.add(horizontalRight.get(i-1));
                /*if(checkBrick(stillObjects,x/32 + i + (y/32) * 31)){
                    isStop = 1;
                    break;
                }*/
            }
            else{
                isStop = i;
                break;
            }
        }
        if(isStop == 0 && !checkWall(stillObjects, x/32 + frame_range_s + (y/32) * 31)) {
            entities.add(horizontalRightLast);
        }
    }
    private void removeEx(List entities,List stillObjects){
        entities.remove(Middle);
        int isStop = 0;
        for (int i = 1; i < frame_range_s; ++ i) {
            if(!checkWall(stillObjects, x/32 + (y/32 - i) * 31)) {
                entities.remove(verticalUp.get(i-1));
                /*if(checkBrick(stillObjects,x/32 + (y/32 - i) * 31)){
                    isStop = i;
                    break;
                }*/
            }
            else{
                isStop = i;
                break;
            }
        }
        if(isStop == 0 && !checkWall(stillObjects, x/32 + (y/32 - frame_range_s) * 31)) {
            entities.remove(verticalUpLast);
        }

        isStop = 0;
        for (int i = 1; i < frame_range_s; ++ i) {
            if(!checkWall(stillObjects, x/32 + (y/32 + i) * 31)) {
                entities.remove(verticalDown.get(i-1));
                /*if(checkBrick(stillObjects,x/32 + (y/32 + i) * 31)){
                    isStop = i;
                    break;
                }*/
            }
            else{
                isStop = i;
                break;
            }
        }
        if(isStop == 0 && !checkWall(stillObjects, x/32 + (y/32 + frame_range_s) * 31)) {
            entities.remove(verticalDownLast);
        }

        isStop = 0;
        for (int i = 1; i < frame_range_s; ++ i) {
            if(!checkWall(stillObjects, x/32 - i + (y/32) * 31)) {
                entities.remove(horizontalLeft.get(i-1));
                /*if(checkBrick(stillObjects,x/32 - i + (y/32) * 31)){
                    isStop = i;
                    break;
                }*/
            }
            else{
                isStop = i;
                break;
            }
        }
        if(isStop == 0 && !checkWall(stillObjects, x/32 - frame_range_s + (y/32) * 31)) {
            entities.remove(horizontalLeftLast);
        }

        isStop = 0;
        for (int i = 1; i < frame_range_s; ++ i) {
            if(!checkWall(stillObjects, x/32 + i + (y/32) * 31)) {
                entities.remove(horizontalRight.get(i-1));
                /*if(checkBrick(stillObjects,x/32 + i + (y/32) * 31)){
                    isStop = i;
                    break;
                }*/
            }
            else{
                isStop = i;
                break;
            }
        }
        if(isStop == 0 && !checkWall(stillObjects, x/32 + frame_range_s + (y/32) * 31)) {
            entities.remove(horizontalRightLast);
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
        if(index >= 0 && index < stillObjects.size()) {
            return stillObjects.get(index) instanceof Brick;
        }
        return false;
    }
    private void changeImageMap(List stillObjects,int xVal,int yVal){
        Random rand = new Random();
        int ranNum = rand.nextInt(42) + 0;
        //ranNum = 5;
        Entity object;
        if(ranNum == 0) {
            object = new FramepassItem(xVal/32, yVal/32, Sprite.powerup_flamepass.getFxImage());
        }
        else if(ranNum == 1) {
            object = new SpeedItem(xVal/32, yVal/32, Sprite.powerup_speed.getFxImage());
        }
        else if(ranNum == 2) {
            object = new FlameItem(xVal/32, yVal/32, Sprite.powerup_flames.getFxImage());
        }
        else if(ranNum == 3){
            object = new BombItem(xVal/32, yVal/32, Sprite.powerup_bombs.getFxImage());
        }
        else if(ranNum == 4){
            object = new WallpassItem(xVal/32, yVal/32, Sprite.powerup_wallpass.getFxImage());
        }
        else if(ranNum == 5) {
            object = new BombpassItem(xVal/32, yVal/32, Sprite.powerup_bombpass.getFxImage());
        }
        else{
            object = new Grass(xVal/32, yVal/32, Sprite.grass.getFxImage());
        }
        stillObjects.set(xVal/32 + (yVal/32)*31,object);
    }
    public void setGrass(List stillObjects){

        int isStop = 0;
        for (int i = 1; i < frame_range_s; ++ i) {
            if(checkBrick(stillObjects, x/32 + (y/32 - i) * 31)) {
                changeImageMap(stillObjects,x,y-32 * i);
                /*isStop = i;
                break;*/
            }
        }
        if(isStop == 0 && checkBrick(stillObjects, x/32 + (y/32 - frame_range_s) * 31)) {
            changeImageMap(stillObjects,x,y-32 * frame_range_s);
        }
        isStop = 0;
        for (int i = 1; i < frame_range_s; ++ i) {
            if(checkBrick(stillObjects, x/32 + (y/32 + i) * 31)) {
                changeImageMap(stillObjects,x,y+32 * i);
                /*isStop = i;
                break;*/
            }
        }
        if(isStop == 0 && checkBrick(stillObjects, x/32 + (y/32 + frame_range_s) * 31)) {
            changeImageMap(stillObjects,x,y+32*frame_range_s);
        }

        isStop = 0;
        for (int i = 1; i < frame_range_s; ++ i) {
            if(checkBrick(stillObjects, x/32 - i + (y/32) * 31)) {
                changeImageMap(stillObjects,x-32*i,y);
                /*isStop = i;
                break;*/
            }
        }
        if(isStop == 0 && checkBrick(stillObjects, x/32 - frame_range_s + (y/32) * 31)) {
            changeImageMap(stillObjects,x-32*frame_range_s,y);
        }
        isStop = 0;
        for (int i = 1; i < frame_range_s; ++ i) {
            if(checkBrick(stillObjects, x/32 + i + (y/32) * 31)) {
                changeImageMap(stillObjects,x+32*i,y);
                /*isStop = i;
                break;*/
            }
        }
        if(isStop == 0 && checkBrick(stillObjects, x/32 + frame_range_s + (y/32) * 31)) {
            changeImageMap(stillObjects,x+32*frame_range_s,y);
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

    public boolean checkPlayer(Entity player) {
        int player_x = player.getX();
        int player_y = player.getY();
        if(player_y == y && player_x == x) return true;
        if(player_y == y) {
            for(int i = 1; i <= frame_range_s; i++){
                if(!checkWall(stillObjects, x/32 + i + (y/32) * 31)) {
                    if(player_x == x + i * 32 ) {
                        return true;
                    }
                }
                else{
                    break;
                }
            }
            for(int i = 1; i <= frame_range_s; i++){
                if(!checkWall(stillObjects, x/32 - i + (y/32) * 31)) {
                    if(player_x == x - i * 32) {
                        return true;
                    }
                }
                else{
                    break;
                }
            }
        }
        else if(player_x == x) {
            for(int i = 1; i <= frame_range_s; i++){
                if(!checkWall(stillObjects, x/32 + (y/32 + i) * 31)) {
                    if(player_y == y + i * 32) {
                        return true;
                    }
                }
                else{
                    break;
                }
            }
            for(int i = 1; i <= frame_range_s; i++){
                if(!checkWall(stillObjects, x/32 + (y/32 - i) * 31)) {
                    if(player_y == y - i * 32) {
                        return true;
                    }
                }
                else{
                    break;
                }
            }
        }
        return false;
    }
    @Override
    public void update() {
    }
}
