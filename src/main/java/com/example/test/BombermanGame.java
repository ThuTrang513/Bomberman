package com.example.test;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    public static Canvas canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);

    public GraphicsContext gc= canvas.getGraphicsContext2D();

    public static Bomber player;
    private List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> enermy = new ArrayList<>();
    public static List<Bomb> bom = new ArrayList<>();
    public static Group root = new Group();
    public static boolean play=false;
    public static Scene scene;
    public static int frame;
    public static int typeEvent = 0;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
         scene = new Scene(Menu.createMenu(stage));
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
                    break;
                /*case P:
                    isPause = !isPause;
                    break;*/
            }
        });
        // Them scene vao stage
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();



        entities.add(player);
        stage.setScene(scene);
        stage.show();
        stage.setHeight(Sprite.SCALED_SIZE * HEIGHT);
        stage.setWidth(Sprite.SCALED_SIZE * WIDTH);
    }




    public void update() {
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
            for(int i = 0; i < bom.size(); ++i){
                if(bom.get(i).isBomb()){
                    bom.get(i).explosion(entities,bom, stillObjects);
                }
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));

        //enermy.forEach(g->g.render(gc));
    }

}