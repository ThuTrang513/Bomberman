package uet.oop.bomberman.highScore;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.io.*;
import java.util.List;

import java.util.*;

import static javafx.scene.input.KeyCode.R;
import static uet.oop.bomberman.Menu.grid;
import static uet.oop.bomberman.Menu.scoreInt;


public class HighScore  {
    public static List<HighScore> scores = new ArrayList<HighScore>();
    String name;
    int score;
    static Text text = new Text();
    static Text text1 = new Text();

    public static void show() {
        String s="";
        String ss="";
        text.setFont(Font.font("Segoe Script",30));
        text.setFill(Color.BLACK);
        text.setLineSpacing(10);
        text.setTranslateY(60);
        text.setTranslateX(120);

        text1.setFont(Font.font("Segoe Script",30));
        text1.setFill(Color.BLACK);
        text1.setLineSpacing(10);
        text1.setTranslateY(60);
        text1.setTranslateX(640);
        for (int i = 0; i < scores.size(); i++) {
            if (i<3)  s += ((int)i+1)+". "+scores.get(i).name + String.format("%10d",scores.get(i).score) + "\n";
            else ss += ((int)i+1)+". "+scores.get(i).name + " \t\t" + scores.get(i).score + "\n";
        }
        text.setText(s);
        text1.setText(ss);
        grid.getChildren().addAll(text,text1);
    }

    public int getScore() {
        return score;
    }

    public HighScore(){
    }

    public HighScore(String a, int i) {
        name = a;
        score = i;
    }

    public static void readHighScoreFile() {
        try {

            Scanner scanner = new Scanner(new File("src/main/resources/highScore.txt"));
            while (scanner.hasNext()) {
                scores.add(new HighScore(scanner.next(), Integer.parseInt(scanner.next())));
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.getMessage();
            System.out.println("xyz");
        }
    }
    public static void writeHighScore(){

        if (scores.size()<7 || scores.get(scores.size() - 1).score < scoreInt) {
            TextField high=new TextField("Write your name");
            Scene scene = new Scene(high);
            Stage s=new Stage();
            s.setTitle("write high score name:");
            TextField b = new TextField("write your name");
            b.setMaxSize(160,30);
            b.setMinSize(160,30);
            b.setTranslateY(70);
            b.setTranslateX(70);
            TilePane r = new TilePane();
            Label l = new Label("You get high score, please type your name");
            l.setFont(Font.font("Arial Black",20));
            l.setTranslateY(100);
            l.setTranslateX(30);
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    HighScore hs= new HighScore(b.getText(), scoreInt);
                    scores.add(hs);
                    Collections.sort(scores, new Comparator<HighScore>() {
                        public int compare(HighScore hs1, HighScore hs2){
                            return  hs2.getScore()-hs1.getScore() ;
                        }
                    });
                    while (scores.size()>=7) scores.remove(scores.size()-1);
                    l.setText("         You rank "+((int)scores.lastIndexOf(hs)+1)+" in HighScore board           ");
                    try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/highScore.txt"));
                    String ss="";
                    for (int i = 0; i < scores.size(); i++) {
                        ss+=scores.get(i).name + " " + scores.get(i).score + "\n";
                    }
                    writer.write(ss,0,ss.length());
                    writer.close();
                    System.out.println(ss);
                    } catch (IOException ex) {
                        System.out.println("Error writing");
                    }
                }
            };
            b.setOnAction(event);
            //b.setTranslateY(300);
            Pane x= new Pane();
            x.setEffect(new ColorInput(0,0,600,500,new LinearGradient(0,0,0,1,true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0.1f, Color.rgb(25, 200, 0, .4)),
                    new Stop(1.0f, Color.rgb(0, 0, 0, .1)))));
            //b.setEffect(new ColorInput(0,0,b.getWidth(),b.getHeight(),Color.RED));
            //ImageView im=new ImageView(new Image("E:/Github/Bomberman/src/main/resources/textures/2.png"));
            //x.getChildren().add(im);
            r.getChildren().add(x);
            r.getChildren().add(b);
            r.getChildren().add(l);
            Scene sc = new Scene(r, 600, 500);
            s.setScene(sc);
            s.show();
        }
    }
    public static void removeText(){
        grid.getChildren().removeAll(text,text1);
    }

}
