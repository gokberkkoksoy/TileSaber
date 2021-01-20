package Tiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import java.io.File;

public class Laser extends Tile{
    boolean vertical;
    public Laser(int x, int y, boolean movable){
        super(x,y);
        super.setMovable(movable);
        setStroke(Color.GRAY);
    }

    public Laser(int x, int y, boolean vertical, boolean movable) {
        super(x, y);
        super.setMovable(movable);
        this.vertical = vertical;
        if(vertical && isMovable()){
            setFill(new ImagePattern(new Image(new File("images/verticalLaser.png").toURI().toString())));
        } else if(!vertical && isMovable()) {
            setFill(new ImagePattern(new Image(new File("images/horizontalLaser.png").toURI().toString())));
        } else if (vertical && !(isMovable())){
            setFill(new ImagePattern(new Image(new File("images/staticLaserVertical.png").toURI().toString())));
        } else {
            setFill(new ImagePattern(new Image(new File("images/staticLaserHorizontal.png").toURI().toString())));
        }

        setStroke(Color.GRAY);
    }


    public boolean isVertical() {
        return vertical;
    }


    @Override
    public String toString() {
        return "Laser{" +
                "vertical=" + vertical +
                '}';
    }
}
