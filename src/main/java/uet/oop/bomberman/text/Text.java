package uet.oop.bomberman.text;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.Menu.*;

public class Text {
    static long lastTime= System.currentTimeMillis();
    public static void updateText(){

        score.setText("Score: " + scoreInt);
        ener.setText("\uD83D\uDC7B: " + enermy.size());
        bomNum.setText("\uD83D\uDCA5 " + bombNum);
        long now = System.currentTimeMillis();
        if (now - lastTime > 1000) {
            lastTime = System.currentTimeMillis();

            time.setText("⏰ " + timeInt);
            timeInt--;
            if (timeInt < 0) isPause=true;
        }
    }
    public void removeText(){
    }
}
