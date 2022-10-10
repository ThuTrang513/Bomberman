package com.example.test;

import com.example.test.Entity;
import javafx.scene.image.Image;

public class ImageEntity extends Entity {

    ImageEntity(int x, int y, Image img){
        super(x,y,img);
    }
    public void setImage(Image img){
        super.img = img;
    }
    @Override
    public void update() {

    }
}
