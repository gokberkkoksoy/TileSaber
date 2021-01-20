package Tools;

import Tiles.*;

import java.io.File;
import java.util.Scanner;

public class GridBuilder {

    private Tile[][] tiles;
    private Tile[][] floor;
    private int[] indexes;
    public static int level = 1;



    public GridBuilder(){
        tiles = new Tile[4][4];
        floor = new Tile[4][4];
        indexes = new int[2];
    }

    public Tile[][] generateTiles() throws Exception {
        Scanner scanner = new Scanner(new File("levels/level" + level + ".txt"));
        while(scanner.hasNext()){
            String input = scanner.nextLine();
            String[] parts = input.split(",");
            int index = Integer.parseInt(parts[0]);
            indexes = getCoordinates(index);
            int xPos = indexes[0];
            int yPos = indexes[1];
            String type = parts[1];
            String property = parts[2];
            switch (type){
                case "Starter":
                    boolean axis = property.equals("Vertical");
                    tiles[xPos][yPos] = new Start(200 * yPos,200* xPos, axis);
                    break;
                case "Empty":
                    if(property.equals("Free")){
                        tiles[xPos][yPos] = new Free(200 * yPos, 200 * xPos);
                    } else if (property.equals("none")){
                        tiles[xPos][yPos] = new Empty(200 * yPos, 200 * xPos);
                    }
                    break;
                case "Pipe":
                    if(property.equals("Vertical")){
                        //axis = true;
                        tiles[xPos][yPos] = new Laser(200 * yPos, 200 * xPos, true, true);
                    } else if (property.equals("Horizontal")){
                        //axis = false;
                        tiles[xPos][yPos] = new Laser(200 * yPos, 200 * xPos, false, true);
                    } else {
                        tiles[xPos][yPos] = new CurvedLaser(200* yPos, 200* xPos, true, property);
                    }
                    break;
                case "End":
                    axis = property.equals("Vertical");
                    tiles[xPos][yPos] = new End(200 * yPos, 200 * xPos, axis);
                    break;
                case "PipeStatic":
                    if(property.equals("Vertical")){
                        //axis = true;
                        tiles[xPos][yPos] = new Laser(200 * yPos, 200 * xPos, true, false);
                    } else if (property.equals("Horizontal")){
                        //axis = false;
                        tiles[xPos][yPos] = new Laser(200 * yPos, 200 * xPos, false, false);
                    } else {
                        tiles[xPos][yPos] = new CurvedLaser(200 * yPos, 200 * xPos, false, property);
                    }
                    break;
            }
        }
        return tiles;
    }

    public Tile[][] generateFloor(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Tile tile = new Free(200 * j, 200 * i);
                floor[i][j] = tile;
            }
        }
        return floor;
    }


    private int[] getCoordinates(int index){
        indexes[0] = (index-1) / 4;
        indexes[1] = (index-1) % 4;
        return indexes;
    }

}
