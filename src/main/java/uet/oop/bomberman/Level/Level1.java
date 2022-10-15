package uet.oop.bomberman.Level;

import uet.oop.bomberman.Enermy.*;
import uet.oop.bomberman.Item.FlameItem;
import uet.oop.bomberman.Map.createMap;
import uet.oop.bomberman.Menu;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.Media.Media.playSound;
import static uet.oop.bomberman.Menu.level;
import static uet.oop.bomberman.Menu.score;


public class Level1 {
    public Level1(){
        enermy.clear();
        stillObjects.clear();

        new createMap("src/main/resources/map/createMap.txt");
        level.setText("Level 1");
        Entity oneal1 = new Oneal(7, 7, Sprite.oneal_left1.getFxImage());
        Entity balloom1 = new Balloom(14, 9, Sprite.balloom_left1.getFxImage());
        Entity minvo=new Minvo(8,5,Sprite.minvo_left3.getFxImage());
        Entity kondo= new Kondoria(4,5,Sprite.kondoria_left1.getFxImage());
        Entity doll= new Doll(4,5,Sprite.doll_left2.getFxImage());
        Entity flameUp=new FlameItem(2,3,Sprite.powerup_flames.getFxImage());
        enermy.add(oneal1);
        enermy.add(doll);
        enermy.add(balloom1);
        enermy.add(minvo);
        enermy.add(kondo);
        playSound("E:/Github/Bomberman/src/main/resources/sound/stage_theme.wav");
    }

}
