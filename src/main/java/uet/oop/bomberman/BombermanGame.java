package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 15;
    public static final int HEIGHT = 14;
    
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public static boolean isPause = false;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class,args);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);


        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

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
        entities.add(bomberman);
    }

    public void createMap() {
        String path = "C:/Users/FPT/IdeaProjects/untitled4/src/main/resources/map/createMap.txt";
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
                    object = new Brick(j, i, Sprite.brick.getFxImage());
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
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
