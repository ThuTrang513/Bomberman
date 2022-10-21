package uet.oop.bomberman.level;

import static uet.oop.bomberman.BombermanGame.isPause;
import static uet.oop.bomberman.level.Level1.lv;

public class Next {
    public static int nextLevelScene=300;
    public Next(){
        nextLevelScene=300;
        if (lv==1) new Level2();
        else if(lv==2) new Level3();
        else if(lv==3) isPause=true;
    }
}
