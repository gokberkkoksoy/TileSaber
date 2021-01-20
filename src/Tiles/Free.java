package Tiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import java.io.File;

public class Free extends Tile {
    public Free(int x, int y) {
        super(x, y);
        super.setMovable(false);
        setFill(new ImagePattern(new Image(new File("images/free.png").toURI().toString())));
        setStroke(Color.GRAY);
    }


    @Override
    public String toString() {
        return "Free tile";
    }
}
