package uet.oop.bomberman;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.canvas;

public class Menu {

    public static GridPane createMenu(Stage primaryStage) throws FileNotFoundException {
        GridPane grid = new GridPane();


        ImageView backGround = new ImageView(new Image(new FileInputStream("E:/Github/Bomberman/src/main/resources/textures/main.png")));

        grid.getChildren().addAll(backGround);

        Image newGame = new Image("E:/Github/Bomberman/src/main/resources/textures/play.png");
        ImageView menu = new ImageView(newGame);
        menu = new ImageView(newGame);
        menu.setTranslateX(-150);
        menu.setTranslateY(10);
        menu.setScaleX(0.4);
        menu.setScaleY(0.4);

        newGame = new Image("E:/Github/Bomberman/src/main/resources/textures/instruction.png");
        ImageView help = new ImageView(newGame);
        help.setTranslateX(-150);
        help.setTranslateY(80);
        help.setScaleX(0.4);
        help.setScaleY(0.4);

        newGame = new Image("E:/Github/Bomberman/src/main/resources/textures/exit.png");
        ImageView exit = new ImageView(newGame);
        exit.setTranslateX(-150);
        exit.setTranslateY(150);
        exit.setScaleX(0.4);
        exit.setScaleY(0.4);

        ImageView sound = new ImageView(newGame);
        sound.setLayoutX(-65);
        sound.setTranslateY(-100);
        sound.setScaleX(0.5);
        sound.setScaleY(0.5);
        grid.getChildren().addAll(menu,help,exit);

        menu.setOnMouseClicked(e->{
            //createGame(primaryStage);
            grid.getChildren().add(canvas);
            //new level1();

        });
        sound.setOnMouseClicked(e->{
            //sound=true;
        });
        help.setOnMouseClicked(e->{
            //grid.getChildren().remove(help);
            ImageView instruction =new ImageView(new Image("E:/Github/Bomberman/src/main/resources/textures/elp.png"));
            grid.add(instruction,0,0);
            ImageView back=new ImageView(new Image("E:/Github/Bomberman/src/main/resources/textures/back.png"));
            back.setTranslateX(-30);
            back.setTranslateY(-360);
            back.setScaleX(0.3);
            back.setScaleY(0.3);
            grid.getChildren().add(back);
            back.setOnMouseClicked(x->{
                grid.getChildren().removeAll(back,instruction);
            });
        });

        exit.setOnMouseClicked(e->{
            primaryStage.close();
        });
        return grid;
    }
    private static void createGame(Stage stage) {

    }


}
