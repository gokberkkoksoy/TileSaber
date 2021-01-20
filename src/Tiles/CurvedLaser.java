package Tiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;


import java.io.File;

public class CurvedLaser extends Laser  {
    String id;
    int status = 0;
    public CurvedLaser(int x, int y,boolean movable, String id) {
        super(x, y, movable);
        this.id = id;
        if(movable) {
            switch(id){
                case "00":
                    setFill(new ImagePattern(new Image(new File("images/curved00.png").toURI().toString())));
                    break;
                case "01":
                    setFill(new ImagePattern(new Image(new File("images/curved01.png").toURI().toString())));
                    break;
                case "10":
                    setFill(new ImagePattern(new Image(new File("images/curved10.png").toURI().toString())));
                    break;
                case "11":
                    setFill(new ImagePattern(new Image(new File("images/curved11.png").toURI().toString())));
                    break;
            }
        } else {
            switch(id){
                case "00":
                    setFill(new ImagePattern(new Image(new File("images/curvedStatic00.png").toURI().toString())));
                    break;
                case "01":
                    setFill(new ImagePattern(new Image(new File("images/curvedStatic01.png").toURI().toString())));
                    break;
                case "10":
                    setFill(new ImagePattern(new Image(new File("images/curvedStatic10.png").toURI().toString())));
                    break;
                case "11":
                    setFill(new ImagePattern(new Image(new File("images/curvedStatic11.png").toURI().toString())));
                    break;
            }
        }

        setStroke(Color.GRAY);
    }

    public String getIdNum(){
        return this.id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CurvedLaser{" +
                "id='" + id + '\'' +
                '}';
    }
}
