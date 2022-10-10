package com.example.test;



import static com.example.test.BombermanGame.enermy;

public class level1 {
    createMap x= new createMap("");
    public void createLevel (){
        Entity oneal1 = new Oneal(1, 1, Sprite.oneal_left1.getFxImage());
        Entity balloom1 = new Balloom(7, 8, Sprite.balloom_left1.getFxImage());
        enermy.add(oneal1);
        enermy.add(balloom1);
    }
}
