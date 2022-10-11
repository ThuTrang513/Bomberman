package uet.oop.bomberman.Map;

import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static uet.oop.bomberman.BombermanGame.stillObjects;

public class createMap {
    public createMap(String path) {

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
}
