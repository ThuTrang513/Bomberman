package uet.oop.bomberman.Media;

import javax.sound.sampled.*;
import java.io.File;

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
    public static  void playSound(final String url) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(url));
            Media control = new Media();
            clip.addLineListener(control);
            clip.open(inputStream);
            clip.start();
            //control.waitonfinish();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}