package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class ImageEntity extends Entity{

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
