package uet.oop.bomberman.media;

import javax.sound.sampled.*;
import java.io.File;

import static uet.oop.bomberman.BombermanGame.isPause;
import static uet.oop.bomberman.BombermanGame.media;

public class Media implements LineListener {
    private boolean done = false;
    public  void update(LineEvent event) {
        if(event.getType() == LineEvent.Type.STOP || event.getType() == LineEvent.Type.CLOSE) {
            done = true;
        }
    }

    public void waitonfinish() throws InterruptedException {
        while(!done) {
            Thread.sleep(1000);
        }
    }
    public static void stopSound(Clip clip) {
        clip.stop();
    }
    public static  void playSound(final String url) {
         //clip ;
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(url));
            //Media control = new Media();
            //clip.addLineListener(control);
            clip.open(inputStream);
            clip.start();
            media.add(clip);
            //if (isPause) stopSound(clip);
            //control.waitonfinish();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
       // return clip;
    }


}