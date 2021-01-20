package Tools;

import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import Tiles.*;
import javafx.scene.shape.PathElement;
import javafx.util.Duration;
import java.util.ArrayList;

public class PathFinder {
    ArrayList<PathElement> paths;
    int status;
    PathTransition pathTransition;

    public PathFinder() {
        pathTransition = new PathTransition();
    }

    public ArrayList<PathElement> generatePath(Tile tile) {
        paths = new ArrayList<>();
        if (tile instanceof CurvedLaser) {
            switch (((CurvedLaser) tile).getIdNum()) {
                case "00":
                    if (((CurvedLaser) tile).getStatus() == 3) {
                        paths.add(new MoveTo(tile.getX() + 100, tile.getY()));
                        paths.add(new LineTo(tile.getX() + 100, tile.getY() + 100));
                        paths.add(new MoveTo(tile.getX() + 100, tile.getY() + 100));
                        paths.add(new LineTo(tile.getX(), tile.getY() + 100));
                    } else {
                        if (((CurvedLaser) tile).getStatus() == 1) {
                            paths.add(new MoveTo(tile.getX(), tile.getY() + 100));
                            paths.add(new LineTo(tile.getX() + 100, tile.getY() + 100));
                            paths.add(new MoveTo(tile.getX() + 100, tile.getY() + 100));
                            paths.add(new LineTo(tile.getX() + 100, tile.getY()));
                        }
                    }
                    break;
                case "01":
                    if (((CurvedLaser) tile).getStatus() == 4) {
                        paths.add(new MoveTo(tile.getX() + 100, tile.getY()));
                        paths.add(new LineTo(tile.getX() + 100, tile.getY() + 100));
                        paths.add(new MoveTo(tile.getX() + 100, tile.getY() + 100));
                        paths.add(new LineTo(tile.getX() + 200, tile.getY() + 100));
                    } else {
                        if (((CurvedLaser) tile).getStatus() == 1) {
                            paths.add(new MoveTo(tile.getX() + 200, tile.getY() + 100));
                            paths.add(new LineTo(tile.getX() + 100, tile.getY() + 100));
                            paths.add(new MoveTo(tile.getX() + 100, tile.getY() + 100));
                            paths.add(new LineTo(tile.getX() + 100, tile.getY()));
                        }
                    }
                    break;
                case "10":
                    if (((CurvedLaser) tile).getStatus() == 2) {
                        paths.add(new MoveTo(tile.getX(), tile.getY() + 100));
                        paths.add(new LineTo(tile.getX() + 100, tile.getY() + 100));
                        paths.add(new MoveTo(tile.getX() + 100, tile.getY() + 100));
                        paths.add(new LineTo(tile.getX() + 100, tile.getY() + 200));
                    } else {
                        if (((CurvedLaser) tile).getStatus() == 3) {
                            paths.add(new MoveTo(tile.getX() + 100, tile.getY() + 200));
                            paths.add(new LineTo(tile.getX() + 100, tile.getY() + 100));
                            paths.add(new MoveTo(tile.getX() + 100, tile.getY() + 100));
                            paths.add(new LineTo(tile.getX(), tile.getY() + 100));
                        }
                    }
                    break;
                case "11":
                    if (((CurvedLaser) tile).getStatus() == 2) {
                        paths.add(new MoveTo(tile.getX() + 200, tile.getY() + 100));
                        paths.add(new LineTo(tile.getX() + 100, tile.getY() + 100));
                        paths.add(new MoveTo(tile.getX() + 100, tile.getY() + 100));
                        paths.add(new LineTo(tile.getX() + 100, tile.getY() + 200));
                    } else {
                        if (((CurvedLaser) tile).getStatus() == 4) {
                            paths.add(new MoveTo(tile.getX() + 100, tile.getY() + 200));
                            paths.add(new LineTo(tile.getX() + 100, tile.getY() + 100));
                            paths.add(new MoveTo(tile.getX() + 100, tile.getY() + 100));
                            paths.add(new LineTo(tile.getX() + 200, tile.getY() + 100));
                        }
                    }
                    break;
            }
            return paths;
        } else if (tile instanceof Start) {
            if (((Start) tile).isVertical()) {
                paths.add(new MoveTo(tile.getX() + 100, tile.getY() + 100));
                paths.add(new LineTo(tile.getX() + 100, tile.getY() + 200));
            } else {
                paths.add(new MoveTo(tile.getX() + 100, tile.getY() + 100));
                paths.add(new LineTo(tile.getX() + 200, tile.getY() + 100));
            }
            return paths;
        }
        else if (tile instanceof End) {
            if (((End) tile).isVertical()) {
                paths.add(new MoveTo(tile.getX() + 100, tile.getY() + 200));
            } else {
                paths.add(new MoveTo(tile.getX(), tile.getY() + 100));
            }
            paths.add(new LineTo(tile.getX() + 100, tile.getY() + 100));
            return paths;
        } else if (tile instanceof Laser) {
            if (((Laser) tile).isVertical()) {
                paths.add(new MoveTo(tile.getX() + 100, tile.getY()));
                paths.add(new LineTo(tile.getX() + 100, tile.getY() + 200));
            } else {
                paths.add(new MoveTo(tile.getX(), tile.getY() + 100));
                paths.add(new LineTo(tile.getX() + 200, tile.getY() + 100));
            }
            return paths;
        }  else {
            return null;
        }
    }

    public boolean findPath(Tile[][] tiles){
        int currX = 0;
        int currY = 0;
        Laser tile = null;

        try {
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    if (tiles[i][j] instanceof Start) {
                        currX = i;
                        currY = j;
                        tile = (Laser) tiles[i][j];
                    }
                }
            }

            assert tile != null;
            if (tile.isVertical()) {
                currX++;
                status = 2;
            } else {
                currY++;
                status = 4;
            }

            while (tiles[currX][currY] instanceof Laser) {
                while (!(tiles[currX][currY] instanceof End)) {
                    if (!(tiles[currX][currY] instanceof Laser)) {
                        return false;
                    }
                    if (tiles[currX][currY] instanceof CurvedLaser) {
                        if (status == 2 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("00")) {
                            if (currX == 0) {//|| currY == tiles.length - 1) {
                                return false;
                            } else {
                                status = 3;
                                ((CurvedLaser)tiles[currX][currY]).setStatus(3);
                                currX--;
                                //currY++;
                            }
                        } else if (status == 2 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("01")) {
                            if (currY == tiles[0].length - 1) {
                                return false;
                            } else {
                                status = 4;((CurvedLaser)tiles[currX][currY]).setStatus(4);
                                currY++;
                            }
                        } else if (status == 2 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("10")) {
                            return false;
                        } else if (status == 2 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("11")) {
                            return false;
                        } else if (status == 3 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("00")) {
                            return false;
                        } else if (status == 3 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("01")) {
                            if (currX == 0) { //|| (currY == 0) {
                                return false;
                            } else {
                                status = 1;
                                ((CurvedLaser)tiles[currX][currY]).setStatus(1);
                                currX--;
                            }
                        } else if (status == 3 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("10")) {
                            return false;
                        } else if (status == 3 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("11")) {
                            if (currX == tiles.length - 1) {// || currY == tiles[0].length) {
                                return false;
                            } else {
                                status = 2;
                                ((CurvedLaser)tiles[currX][currY]).setStatus(2);
                                currX++;
                            }
                        } else if (status == 1 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("00")) {
                            return false;
                        } else if (status == 1 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("01")) {
                            return false;
                        } else if (status == 1 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("10")) {
                            if /*(currX == 0 ||*/ (currY == 0) {
                                return false;
                            } else {
                                status = 3;
                                ((CurvedLaser)tiles[currX][currY]).setStatus(3);
                                //currX--;
                                currY--;
                            }
                        } else if (status == 1 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("11")) {
                            if /*(currX == tiles.length - 1 ||*/ (currY == tiles[0].length - 1) {
                                return false;
                            } else {
                                status = 4;
                                ((CurvedLaser)tiles[currX][currY]).setStatus(4);
                                //currX--;
                                currY++;
                            }
                        } else if (status == 4 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("00")) {
                            if (currY == 0) {
                                return false;
                            } else {
                                status = 1;
                                ((CurvedLaser)tiles[currX][currY]).setStatus(1);
                                //currX++;
                                currX--;
                            }
                        } else if (status == 4 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("01")) {
                            return false;
                        } else if (status == 4 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("10")) {
                            if (currX == tiles.length - 1) { //|| currY == tiles[0].length - 1) {
                                return false;
                            } else {
                                status = 2;
                                ((CurvedLaser)tiles[currX][currY]).setStatus(2);
                                //currX++;
                                currX++;
                            }
                        } else if (status == 4 && ((CurvedLaser) tiles[currX][currY]).getIdNum().equals("11")) {
                            return false;

                        }
                    } else { // if just laser
                        if (status == 1 && ((Laser) tiles[currX][currY]).isVertical()) {
                            if (currX == 0) {
                                return false;
                            } else {
                                currX--;
                            }
                        } else if (status == 1 && !(((Laser) tiles[currX][currY]).isVertical())) {
                            return false;
                        } else if (status == 2 && ((Laser) tiles[currX][currY]).isVertical()) {
                            if (currX == tiles.length - 1) {
                                return false;
                            } else {
                                currX++;
                            }
                        } else if (status == 2 && !(((Laser) tiles[currX][currY]).isVertical())) {
                            return false;
                        } else if (status == 3 && ((Laser) tiles[currX][currY]).isVertical()) {
                            return false;
                        } else if (status == 3 && !(((Laser) tiles[currX][currY]).isVertical())) {
                            if (currY == 0) {
                                return false;
                            } else {
                                currY--;
                            }
                        } else if (status == 4 && ((Laser) tiles[currX][currY]).isVertical()) {
                            return false;
                        } else if (status == 4 && !(((Laser) tiles[currX][currY]).isVertical())) {
                            if (currY == tiles[0].length - 1) {
                                return false;
                            } else {
                                currY++;
                            }
                        }
                    }

                }
                return true;
            }
            return false;

        } catch(Exception ignored){
            return false;
        }
    }


    public void runCircle (Node rect, Tile[][]tiles){
        Path path = new Path();
        path.setOpacity(0);

        ArrayList<Tile> tileArrayList = new ArrayList<>();
        ArrayList<PathElement> pathElements;
        for (Tile[] value : tiles) {
            for (int b = 0; b < tiles.length; b++) {
                tileArrayList.add(value[b]);
            }
        }
        for(int i = 0;i < tileArrayList.size();i++){
            Tile endTile;
            if(tileArrayList.get(i) instanceof End){
                endTile = tileArrayList.get(i);
                if(tileArrayList.get(tileArrayList.size()-1) instanceof End){
                    break;
                }
                tileArrayList.remove(i);
                tileArrayList.add(tileArrayList.size()-1, endTile);
            }

        }
        for (Tile value : tileArrayList) {
            try {
                pathElements = generatePath(value);
                for (PathElement pathElement : pathElements) {
                    path.getElements().add(pathElement);
                }
            } catch (Exception ignored) {
            }

        }
        pathTransition.setPath(path);
        pathTransition.setNode(rect);
        pathTransition.setDuration(new Duration(2000));
        pathTransition.setCycleCount(1);
        pathTransition.play();
    }

    public PathTransition getPathTransition() {
        return pathTransition;
    }
}

