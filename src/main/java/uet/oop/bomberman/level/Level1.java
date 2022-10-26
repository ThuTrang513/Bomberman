package uet.oop.bomberman.level;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.map.CreateMap;
import uet.oop.bomberman.Menu;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Bomb.frame_range;
import static uet.oop.bomberman.item.Portal.isPortal;
import static uet.oop.bomberman.Menu.*;


public class Level1 {
    public static int lv=0;
    ImageView level1= new ImageView(new Image("E:/Bomberman/src/main/resources/images/level 1.png"));
    public Level1(){
        lv=1;bombNum = 1;
        frame_range=1;

        BombermanGame.hasBomb=0;
        isPause=false;
        isPortal=false;
        entities.clear();
        enermy.clear();
        stillObjects.clear();
        bom.forEach(e->{
            e.setIsEx(false);
            //Bomb.timeSetBomb = System.currentTimeMillis();
        });
        bom.clear();
        media.forEach(e->{
            e.close();
        });
        Menu.scoreInt=0;
        level1.setScaleX(0.8);
        level1.setScaleY(0.6);
        level1.setTranslateX(0);
        level1.setTranslateY(-238);


        //grid.add(level1,1,1);
        Menu.timeInt=300;
        player=new Bomber(1,1,(Sprite.player_right.getFxImage()));
        entities.add(player);
        new CreateMap("src/main/resources/map/level1.txt");

        level.setText("Level 1");

        //Entity oneal1 = new Oneal(7, 7, Sprite.oneal_left1.getFxImage());
        //Entity balloom1 = new Balloom(14, 9, Sprite.balloom_left1.getFxImage());

        //enermy.add(oneal1);
        //enermy.add(balloom1);
        //grid.getChildren().addAll(score,level,ener,bomNum,time);
    }

}
