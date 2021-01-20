package Tiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import java.io.File;


public class Empty extends Tile {
    public Empty(int x, int y){
        super(x, y);
        super.setMovable(true);
        setFill(new ImagePattern(new Image(new File("images/empty.png").toURI().toString())));
        setStroke(Color.GRAY);
    }

    @Override
    public String toString() {
        return "Empty tile";
    }
}
