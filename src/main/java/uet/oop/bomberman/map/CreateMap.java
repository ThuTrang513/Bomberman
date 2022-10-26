package uet.oop.bomberman.map;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.enermy.*;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.item.Portal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static uet.oop.bomberman.BombermanGame.enermy;
import static uet.oop.bomberman.BombermanGame.stillObjects;

public class CreateMap {
    public CreateMap(String path) {

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
                else if(map.charAt(j) == 'x') {
                    BombermanGame.portal=new Portal(j, i, Sprite.portal.getFxImage());
                    object=new Brick(j, i, Sprite.brick.getFxImage());
                }
                else if (map.charAt(j) == '1'){
                    object=(new Grass(j,i,Sprite.grass.getFxImage()));
                    enermy.add(new Balloom(j,i,Sprite.balloom_left3.getFxImage()));
                } else if (map.charAt(j) == '2'){
                    object=(new Grass(j,i,Sprite.grass.getFxImage()));
                    enermy.add(new Oneal(j,i,Sprite.oneal_right1.getFxImage()));
                } else if (map.charAt(j) == '3'){
                    object=(new Grass(j,i,Sprite.grass.getFxImage()));
                    enermy.add(new Doll(j,i,Sprite.doll_right1.getFxImage()));
                } else if (map.charAt(j) == '4'){
                    object=(new Grass(j,i,Sprite.grass.getFxImage()));
                    enermy.add(new Minvo(j,i,Sprite.minvo_right1.getFxImage()));
                } else if (map.charAt(j) == '5'){
                    object=(new Grass(j,i,Sprite.grass.getFxImage()));
                    enermy.add(new Kondoria(j,i,Sprite.kondoria_right1.getFxImage()));
                } else if (map.charAt(j) == '6'){
                    object=(new Grass(j,i,Sprite.grass.getFxImage()));
                    enermy.add(new Ovapi(j,i,Sprite.ovapi_right1.getFxImage()));
                }
                else{
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
            ++i;
        }

    }
}
