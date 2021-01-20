package Tiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;


import java.io.File;

public class Start extends Laser {
    boolean vertical;
    public Start(int x, int y, boolean vertical) {
        super(x, y, vertical, false);
        this.vertical = vertical;
        if(vertical){
            setFill(new ImagePattern(new Image(new File("images/startVertical.png").toURI().toString())));
        } else {
            setFill(new ImagePattern(new Image(new File("images/startHorizontal.png").toURI().toString())));
        }
        setStroke(Color.GRAY);
    }



    public boolean isVertical() {
        return vertical;
    }

    @Override
    public String toString() {
        return "Start{" +
                "vertical=" + vertical +
                '}';
    }
}
