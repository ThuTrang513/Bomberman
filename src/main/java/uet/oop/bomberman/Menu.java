package uet.oop.bomberman;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.level.Level1;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static javafx.scene.paint.Color.WHITE;
import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.canvas;
import static uet.oop.bomberman.level.Level1.lv;
import static uet.oop.bomberman.media.Media.playSound;

public class Menu {

    public static Text score = new Text("Score: ");
    public static int scoreInt=0;
    public static Label time = new Label("⏰ ");
    public static int timeInt;
    public static Label bomNum = new Label("Bom ");

    public static Label ener = new Label("Enermy: ");
    public static Label level = new Label("Level");
    public static ImageView black;
    public static ImageView menu;
    public static ImageView gameOver= new ImageView("E:/Github/Bomberman/src/main/resources/images/gameOver.png");
    public static GridPane grid = new GridPane();

    static int cnt=0;
    public static boolean isSound=true;

    public static GridPane createMenu(Stage primaryStage) throws FileNotFoundException {

        ImageView backGround = new ImageView(new Image(new FileInputStream("E:/Bomberman/src/main/resources/textures/main.png")));
        grid.add(backGround,0,0);
        //grid.getChildren().add(backGround);

        Image newGame = new Image("E:/Bomberman/src/main/resources/textures/play.png");
        menu = new ImageView(newGame);
        menu.setTranslateX(360);
        menu.setTranslateY(-100);
        menu.setScaleX(0.5);
        menu.setScaleY(0.5);

        menu.setOnMouseEntered(e->{
            menu.setImage(new Image("E:/Bomberman/src/main/resources/textures/play1.png"));
        });
        menu.setOnMouseExited(e->{
            menu.setImage(new Image("E:/Bomberman/src/main/resources/textures/play.png"));
        });
        newGame = new Image("E:/Bomberman/src/main/resources/textures/instruction.png");
        ImageView help = new ImageView(newGame);
        help.setTranslateX(360);
        help.setTranslateY(-20);
        help.setScaleX(0.5);
        help.setScaleY(0.5);
        help.setOnMouseEntered(e->{
            help.setImage(new Image("E:/Bomberman/src/main/resources/textures/instruction1.png"));
        });
        help.setOnMouseExited(e->{
            help.setImage(new Image("E:/Bomberman/src/main/resources/textures/instruction.png"));
        });
        newGame = new Image("E:/Bomberman/src/main/resources/textures/exit.png");
        ImageView exit = new ImageView(newGame);
        exit.setTranslateX(360);
        exit.setTranslateY(60);
        exit.setScaleX(0.5);
        exit.setScaleY(0.5);
        exit.setOnMouseEntered(e->{
            exit.setImage(new Image("E:/Bomberman/src/main/resources/textures/exit1.png"));
        });
        exit.setOnMouseExited(e->{
            exit.setImage(new Image("E:/Bomberman/src/main/resources/textures/exit.png"));
        });
        newGame=new Image("E:/Bomberman/src/main/resources/images/volumeOn.png");
        ImageView sound = new ImageView(newGame);
        sound.setLayoutX(0);
        sound.setTranslateY(-180);
        sound.setScaleX(0.2);
        sound.setScaleY(0.2);
        grid.getChildren().addAll(menu,help,exit,sound);
        black= new ImageView("E:/Bomberman/src/main/resources/images/blackBackground.png");

        grid.setMaxWidth(Sprite.SCALED_SIZE*WIDTH);
        grid.setMaxHeight(Sprite.SCALED_SIZE*HEIGHT);
        grid.setMinWidth(Sprite.SCALED_SIZE*WIDTH);
        //grid.setPrefHeight(60);
        grid.setMinHeight(Sprite.SCALED_SIZE*HEIGHT);
        //showInfo();
        menu.setOnMouseClicked(e->{
            cnt=0;
            grid.getChildren().add(black);
            grid.getChildren().add(canvas);
            showInfo();
            new Level1();
            if (isSound) playSound("src/main/resources/sound/stage_theme.wav");
            grid.getChildren().addAll( score,level,ener,bomNum,time);
            //grid.getChildren().removeAll(backGround,exit,help);
            //grid.add(black,0,0);
        });
        sound.setOnMouseClicked(e->{
            //grid.getChildren().add(sound);
            if (isSound)   sound.setImage(new Image("images/volumeOff.png"));
            else  sound.setImage(new Image("images/volumeOn.png"));
            isSound=!isSound;
        });
        help.setOnMouseClicked(e->{
            //grid.getChildren().remove(help);
            ImageView instruction =new ImageView(new Image("textures/help.png"));
            grid.add(instruction,0,0);
            ImageView back=new ImageView(new Image("textures/back.png"));
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

    private static void showInfo() {
        //grid.getChildren().add()
        score = new Text("score");
        score.setFont(new Font("Arial", 30));
        //score.setTextFill(Color.web("#FFFFFF"));
        score.setFill(WHITE);
        score.setTranslateX(0);
        score.setTranslateY(-238);

        level=new Label("level");
        level.setFont(new Font("Arial", 30));
        level.setTextFill(Color.web("#FFFFFF"));
        level.setTranslateX(160);
        level.setTranslateY(-238);

        ener=new Label("enermy");
        ener.setFont(new Font("Arial", 30));
        ener.setTextFill(Color.web("#FFFFFF"));
        ener.setTranslateX(320);
        ener.setTranslateY(-238);

        bomNum=new Label("bomNum");
        bomNum.setFont(new Font("Arial", 30));
        bomNum.setTextFill(Color.web("#FFFFFF"));
        bomNum.setTranslateX(500);
        bomNum.setTranslateY(-238);

        time=new Label("⏰ ");
        time.setFont(new Font("Arial", 30));
        time.setTextFill(Color.web("#FFFFFF"));
        time.setTranslateX(660);
        time.setTranslateY(-238);
    }
    public static void gameover(Stage stage) {
        if (cnt==39) {
            grid.getChildren().removeAll(canvas, black);
            grid.getChildren().add(gameOver);
        }
        cnt++;
            if (cnt==50) {
                isPause=false;
                grid.getChildren().remove(gameOver);
                media.forEach(e->e.close());
                bom.clear();
                entities.clear();
                enermy.clear();
                lv=0;
            }
    }
}
