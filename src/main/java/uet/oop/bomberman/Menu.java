package uet.oop.bomberman;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.bomberman.Level.Level1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.canvas;

public class Menu {

    public static GridPane createMenu(Stage primaryStage) throws FileNotFoundException {
        GridPane grid = new GridPane();
        grid.setMaxWidth(10);
        grid.setMaxHeight(30);
        grid.setPrefWidth(40);
        grid.setPrefHeight(60);
grid.setMinHeight(10);

        ImageView backGround = new ImageView(new Image(new FileInputStream("E:/Github/Bomberman/src/main/resources/textures/main.png")));
        grid.add(backGround,0,0);
        //grid.getChildren().add(backGround);

        Image newGame = new Image("E:/Github/Bomberman/src/main/resources/textures/play.png");
        ImageView menu = new ImageView(newGame);
        menu = new ImageView(newGame);
        menu.setTranslateX(260);
        menu.setTranslateY(-100);
        menu.setScaleX(0.4);
        menu.setScaleY(0.4);

        newGame = new Image("E:/Github/Bomberman/src/main/resources/textures/instruction.png");
        ImageView help = new ImageView(newGame);
        help.setTranslateX(260);
        help.setTranslateY(-20);
        help.setScaleX(0.4);
        help.setScaleY(0.4);

        newGame = new Image("E:/Github/Bomberman/src/main/resources/textures/exit.png");
        ImageView exit = new ImageView(newGame);
        exit.setTranslateX(260);
        exit.setTranslateY(60);
        exit.setScaleX(0.4);
        exit.setScaleY(0.4);

        ImageView sound = new ImageView(newGame);
        sound.setLayoutX(-65);
        sound.setTranslateY(-100);
        sound.setScaleX(0.5);
        sound.setScaleY(0.5);
        grid.getChildren().addAll(menu,help,exit);

        ImageView black= new ImageView("E:/Github/Bomberman/src/main/resources/images/blackBackground.png");


        menu.setOnMouseClicked(e->{
            new Level1();
            grid.getChildren().removeAll(backGround,exit,help);
            //grid.add(black,0,0);
            grid.getChildren().add(black);
            grid.getChildren().add(canvas);
            showInfo(grid);
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

    private static void showInfo(GridPane grid) {
        //grid.getChildren().add()
        Label score = new Label("Score: ");
        score.setFont(new Font("Arial", 30));
        score.setTextFill(Color.web("#FFFFFF"));
        score.setTranslateX(0);
        score.setTranslateY(-242);

        Label level = new Label("Level");
        level.setFont(new Font("Arial", 30));
        level.setTextFill(Color.web("#FFFFFF"));
        level.setTranslateX(160);
        level.setTranslateY(-242);

        Label ener = new Label("Enermy");
        ener.setFont(new Font("Arial", 30));
        ener.setTextFill(Color.web("#FFFFFF"));
        ener.setTranslateX(320);
        ener.setTranslateY(-242);


        Label bomNum = new Label("Bom");
        bomNum.setFont(new Font("Arial", 30));
        bomNum.setTextFill(Color.web("#FFFFFF"));
        bomNum.setTranslateX(500);
        bomNum.setTranslateY(-242);

        Label time = new Label("Time: ");
        time.setFont(new Font("Arial", 30));
        time.setTextFill(Color.web("#FFFFFF"));
        time.setTranslateX(660);
        time.setTranslateY(-242);
        grid.getChildren().addAll(score,level,ener,bomNum,time);
    }

    private static void createGame(Stage stage) {

    }


}
