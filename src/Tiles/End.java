package Tiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import java.io.File;

public class End extends Laser {

    boolean vertical;

    public End(int x, int y, boolean vertical) {
        super(x, y, vertical, false);
        this.vertical = vertical;
        if(vertical){
            setFill(new ImagePattern(new Image(new File("images/endVertical.png").toURI().toString())));
        } else {
            setFill(new ImagePattern(new Image(new File("images/endHorizontal.png").toURI().toString())));
        }
        setStroke(Color.GRAY);
    }


    public boolean isVertical() {
        return vertical;
    }

    @Override
    public String toString() {
        return "End{" +
                "vertical=" + vertical +
                '}';
    }
}
