package com.example.test;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.example.test.Sprite.wall;
import static javafx.scene.input.KeyCode.*;

public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    public static Canvas canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);

    public GraphicsContext gc= canvas.getGraphicsContext2D();
    private List<Entity> player = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    public static Group root = new Group();
    public static boolean play=false;
    public static Scene scene;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
         scene = new Scene(Menu.createMenu(stage));

       /* scene.setOnKeyPressed(event -> {
            if (player.isLife()) {
                switch (event.getCode()) {
                    case UP:
                        Move.up(player);
                        break;
                    case DOWN:
                        Move.down(player);
                        break;
                    case RIGHT:
                        Move.right(player);
                        break;
                    case LEFT:
                        Move.left(player);
                        break;
                    case SPACE:
                        Bomb.putBomb();
                        break;
                    case P:
                        //isPause = !isPause;
                        break;
                }
            }
        });*/
        // Them scene vao stage
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        player.add(bomberman);


        stage.setScene(scene);
        stage.show();
        stage.setHeight(Sprite.SCALED_SIZE * HEIGHT);
        stage.setWidth(Sprite.SCALED_SIZE * WIDTH);
    }



    public void createMap() {

        String path = "E:/test/src/main/resources/map/createMap.txt";
        File fileInput = new File(path);
        Scanner in = null;
        try {
            in = new Scanner(fileInput);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i = 0;
        while(in.hasNextLine()) {
            String map  = in.nextLine();
            Entity object = null;
            for (int j = 0; j < 31; ++j){
                if (map.charAt(j)== '#') {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                }
                else if(map.charAt(j) == '*') {
                    //object = new Brick(j, i, Sprite.brick.getFxImage());
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                /*else if(map.charAt(j) == 'x') {
                    object = new Portal(j, i, Sprite.portal.getFxImage());
                }*/
                else{
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
            ++i;
        }
    }

    public void update() {
        player.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        player.forEach(g -> g.render(gc));
    }

}