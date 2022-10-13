package uet.oop.bomberman.Level;

import uet.oop.bomberman.Enermy.Balloom;
import uet.oop.bomberman.Enermy.Oneal;
import uet.oop.bomberman.Item.FlameItem;
import uet.oop.bomberman.Map.createMap;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.enermy;

public class Level1 {
    public Level1(){
        new createMap("src/main/resources/map/createMap.txt");
        Entity oneal1 = new Oneal(7, 7, Sprite.oneal_left1.getFxImage());
        Entity balloom1 = new Balloom(14, 9, Sprite.balloom_left1.getFxImage());
        Entity balloom2=new Balloom(8,5,Sprite.balloom_left3.getFxImage());
        Entity flameUp=new FlameItem(2,3,Sprite.powerup_flames.getFxImage());
        enermy.add(oneal1);
        enermy.add(balloom1);
        enermy.add(balloom2);

    }
}
