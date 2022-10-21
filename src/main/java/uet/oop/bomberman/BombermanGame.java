package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.level.Level1;
import uet.oop.bomberman.level.Next;
import uet.oop.bomberman.text.Text;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.item.Portal.isPortal;
import static uet.oop.bomberman.media.Media.playSound;


public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 14;
    private GraphicsContext gc;
    public static Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> enermy = new ArrayList<>();
    public static List<Entity> item=new ArrayList<>();
    public static boolean isPause = false;

    public static Bomber player;
    public static int typeEvent = 0;
    public static int frame;
    public static List<Bomb> bom = new ArrayList<>();
    public static Portal portal;
    public static List<Clip> media= new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class,args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        // Tao scene
        Scene scene = new Scene(Menu.createMenu(stage));
        stage.setScene(scene);
        //portal
        //test
        player = new Bomber(1, 1, Sprite.player_right.getFxImage());
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case UP:
                    if(player.checkRun(stillObjects, player.getX()/32 + (player.getY()/32 - 1) * 31)){
                        typeEvent = 1;
                    }
                    break;
                case DOWN:
                    if(player.checkRun(stillObjects, player.getX()/32 + (player.getY()/32 + 1) * 31)){
                        typeEvent = 2;
                    }
                    break;
                case RIGHT:
                    if(player.checkRun(stillObjects, player.getX()/32 + 1 + (player.getY()/32) * 31)){
                        typeEvent = 3;
                    }
                    break;
                case LEFT:
                    if(player.checkRun(stillObjects, player.getX()/32 - 1 + (player.getY()/32) * 31)){
                        typeEvent = 4;
                    }
                    break;
                case SPACE:
                    Bomb newBom = new Bomb(player.getX()/32, player.getY()/32, Sprite.bomb.getFxImage(),entities);
                    entities.add(newBom);
                    bom.add(newBom);
                    playSound("src/main/resources/sound/place_bomb.wav");
                    break;
                /*case P:
                    isPause = !isPause;
                    break;*/
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                if(!isPause){
                    update();
                } else {
                    Menu.gameover(stage);
                }
            }
        };
        timer.start();

        //entities.add(player);
        stage.show();

        //stage.setHeight(Sprite.SCALED_SIZE * HEIGHT+30);
        //stage.setWidth(Sprite.SCALED_SIZE * WIDTH+12);
    }

    public void print(int time){
        System.out.println(time);
    }


    public void update() {
        enermy.forEach(Entity::update);
        item.forEach(Entity::update);
        entities.forEach(Entity::update);
        /** bomber run */
        switch(typeEvent){
            case 1:
                player.setY(player.getY()-2);
                player.UP();
                ++frame;
                break;
            case 2:
                player.setY(player.getY()+2);
                player.DOWN();
                ++frame;
                break;
            case 3:
                player.setX(player.getX()+2);
                player.RIGHT();
                ++frame;
                break;
            case 4:
                player.setX(player.getX()-2);
                player.LEFT();
                ++frame;
                break;
        }
        if(frame == 16 && typeEvent != 0){
            typeEvent = 0;
            frame = 0;
        }
        if(!bom.isEmpty()){
            for(int i = 0; i < bom.size(); i++){
                if(bom.get(i).isBomb()){
                    bom.get(i).explosion(entities,bom, stillObjects);
                    player.bomber_died(bom.get(i));
                }
            }
        }
        // set text
        if (Level1.lv!=0) Text.updateText();
        //portal
        if (portal!=null) {
            //portal.update();

            if (BombermanGame.stillObjects.get(portal.getX()/32+(portal.getY()/32)*31) instanceof Grass){
                isPortal=true;
               //System.out.println(portal.getX()/32+(portal.getY()/32)*31);
            }

        }
        if (enermy.size()==0 && isPortal && player.getX()== portal.getX()
        && player.getY()== portal.getY()) {

            new Next();
            playSound("src/main/resources/sound/next_level.wav");
        }

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        stillObjects.forEach(g -> g.render(gc));
        if (isPortal) portal.render(gc);
        entities.forEach(g -> g.render(gc));
        enermy.forEach(g->g.render(gc));
        item.forEach(g->g.render(gc));

    }
}
