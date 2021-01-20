package Tiles;

import javafx.scene.shape.Rectangle;

public abstract class Tile extends Rectangle {
    private boolean movable;
    public static final int TILE_WIDTH = 200;
    public static final int TILE_HEIGHT = 200;

    Tile(int x,int y){
        setX(x);
        setY(y);
        setWidth(TILE_WIDTH);
        setHeight(TILE_HEIGHT);
    }

    public int gridPosition(double coordinate){
        return (int) (coordinate / 200);
    }
    public double tileCenter(double coordinate) {
        return coordinate - 100;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }


}
