package uet.oop.bomberman.level;

import uet.oop.bomberman.map.CreateMap;
import uet.oop.bomberman.Menu;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.player;
import static uet.oop.bomberman.Menu.*;
import static uet.oop.bomberman.Menu.time;
import static uet.oop.bomberman.item.Portal.isPortal;

public class Level3 {
     Level3() {
         Level1.lv=3;
         isPause=false;
         grid.getChildren().addAll(score,ener,bomNum,time,level);
         isPortal=false;
         entities.clear();
         enermy.clear();
         stillObjects.clear();
         bom.clear();
         player=new Bomber(1,1,(Sprite.player_right.getFxImage()));
         entities.add(player);
         Menu.timeInt=300;
         new CreateMap("src/main/resources/map/level3.txt");

         level.setText("Level 3");

    }
}
