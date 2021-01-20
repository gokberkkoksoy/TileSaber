package Scenes;

import Tiles.*;
import Tools.AudioPlayer;
import Tools.GridBuilder;
import Tools.PathFinder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;


public class GameScreen {

    BorderPane scenePane;
    Pane gamePane;
    VBox statPane;
    Tile[][] tiles;
    BackgroundImage backgroundImage;
    Text moveText;
    Text moveCount;
    Text levelText;
    Text levelCount;
    public static Button mute;
    final double[] prevX = new double[1];
    final double[] prevY = new double[1];
    final int[] moveCounter = {0};
    Scene scene;
    Rectangle tieFighter;
    Stage primaryStage;
    AudioPlayer audioPlayer;
    static String prev;

    public GameScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        scenePane = new BorderPane();
        gamePane = new Pane();
        tiles = new Tile[4][4];
        scene = new Scene(scenePane, 1100, 800);
        audioPlayer = new AudioPlayer();
        prev = EntryScreen.mute.getText();
    }

    public void setGamePane() throws Exception {
        GridBuilder gridBuilder = new GridBuilder();
        tiles = gridBuilder.generateFloor();
        for (Tile[] tile : tiles) {
            for (Tile value : tile) {
                gamePane.getChildren().add(value);
            }
        }

        tiles = gridBuilder.generateTiles();
        for (Tile[] tile : tiles) {
            for (Tile value : tile) {
                gamePane.getChildren().add(value);
            }
        }

        tieFighter = new Rectangle(50,50,100,100);
        tieFighter.setFill(new ImagePattern(new Image(new File("images/tieFighter.png").toURI().toString())));
        gamePane.getChildren().add(tieFighter);
        scenePane.setCenter(gamePane);
        setStatPane();
        if(mute.getText().equals("UNMUTE")){
            audioPlayer.stopImperial();
        } else {
            audioPlayer.playImperial();
        }

        primaryStage.setScene(scene);

    }

    public void gamePlay() {
        PathFinder pathFinder = new PathFinder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Tile tile = tiles[i][j];

                if (tile.isMovable()) {
                    tile.setOnMousePressed(event -> {
                        prevX[0] = tile.gridPosition(event.getX()) * Tile.TILE_HEIGHT;
                        prevY[0] = tile.gridPosition(event.getY()) * Tile.TILE_WIDTH;

                        gamePane.getChildren().remove(tile);
                        gamePane.getChildren().add(tile);

                        scenePane.getChildren().remove(gamePane);
                        scenePane.getChildren().add(gamePane);

                        tile.setX(tile.tileCenter(event.getX()));
                        tile.setY(tile.tileCenter(event.getY()));

                        tiles[tile.gridPosition(event.getY())][tile.gridPosition(event.getX())] = new Free((tile.gridPosition(event.getX())) * Tile.TILE_HEIGHT, (tile.gridPosition(event.getY()) * Tile.TILE_WIDTH));
                    });

                    tile.setOnMouseDragged(event -> {
                        tile.setX(tile.tileCenter(event.getX()));
                        tile.setY(tile.tileCenter(event.getY()));
                    });

                    tile.setOnMouseReleased(event -> {
                        gamePane.getChildren().remove(tieFighter);
                        gamePane.getChildren().add(tieFighter);
                        if (isInsideGrid(tile.gridPosition(event.getX()), tile.gridPosition(event.getY()))) {
                            if (isTileFree((tiles[tile.gridPosition(event.getY())][tile.gridPosition(event.getX())])) && (isMovedOneGrid(event.getX(), event.getY()))
                                    && isMovedLinear(event.getX(), event.getY())) {
                                tile.setX(tile.gridPosition(event.getX()) * Tile.TILE_HEIGHT);
                                tile.setY(tile.gridPosition(event.getY()) * Tile.TILE_WIDTH);
                                tiles[tile.gridPosition(event.getY())][tile.gridPosition(event.getX())] = tile;
                                if (isInDifferentPlace(tile.gridPosition(event.getX()), tile.gridPosition(event.getY()))) {
                                    moveCounter[0]++;
                                    setStatPane();
                                }
                            } else {
                                tile.setX(prevX[0]);
                                tile.setY(prevY[0]);
                                tiles[tile.gridPosition(prevY[0])][tile.gridPosition(prevX[0])] = tile;
                            }
                        } else {
                            tile.setX(prevX[0]);
                            tile.setY(prevY[0]);
                            tiles[tile.gridPosition(prevY[0])][tile.gridPosition(prevX[0])] = tile;
                        }

                        try {
                            if (pathFinder.findPath(tiles)) {
                                pathFinder.runCircle(tieFighter,tiles);
                                pathFinder.getPathTransition().setOnFinished(event1 -> {
                                    GridBuilder.level++;
                                    if(GridBuilder.level > 5){
                                        GridBuilder.level = 1;
                                        scenePane.getChildren().clear();
                                        ScoreScreen scoreScreen = new ScoreScreen(primaryStage);
                                        audioPlayer.stopImperial();
                                        primaryStage.setScene(scoreScreen.getScene());
                                    } else {
                                        scenePane.getChildren().clear();
                                        try {
                                            setGamePane();
                                            moveCounter[0] = 0;
                                            setStatPane();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        gamePlay();
                                    }

                                });
                            }

                        } catch (Exception ignored) {}
                    });
                }

            }
        }

    }

    public void setStatPane() {
        statPane = new VBox();
        statPane.setSpacing(30);
        statPane.maxWidth(300);
        statPane.setAlignment(Pos.CENTER);
        statPane.setPadding(new Insets(15, 34, 15, 35));

        backgroundImage = new BackgroundImage(new Image(new File("images/background.png").toURI().toString()), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        statPane.setBackground(new Background(backgroundImage));

        levelText = new Text("LEVEL:");
        levelText.setStyle("-fx-fill:#C0C0C0;-fx-font-size: 60;");
        levelCount = new Text(String.valueOf(GridBuilder.level));
        levelCount.setStyle("-fx-fill:#C0C0C0;-fx-font-size: 60;");

        moveText = new Text("MOVES:");
        moveText.setStyle("-fx-fill:#C0C0C0;-fx-font-size: 60;");
        //moveText.setFont(Font.font("News Gothic"));

        moveCount = new Text(String.valueOf(moveCounter[0]));
        moveCount.setStyle("-fx-fill:#C0C0C0;-fx-font-size: 60;");
        //moveCount.setFont(Font.font("Times New Roman"));

        mute = new Button(prev);
        mute.setOnAction(event -> {
            if(mute.getText().equals("MUTE")){
                audioPlayer.stopImperial();
                mute.setText("UNMUTE");
                prev = "UNMUTE";
            } else {
                audioPlayer.playImperial();
                mute.setText("MUTE");
                prev = "MUTE";
            }
        });
        statPane.getChildren().addAll(levelText,levelCount,moveText,moveCount,mute);
        scenePane.setRight(statPane);
    }

    private boolean isMovedLinear(double xPosition, double yPosition) {
        return (Math.abs((int) (prevX[0] / 200) - (int) (xPosition / 200)) + Math.abs((int) (prevY[0] / 200) - (int) (yPosition / 200)) < 2);
    }

    private boolean isMovedOneGrid(double xPosition, double yPosition) {
        return Math.abs(prevX[0] - ((int) xPosition / 200) * 200) <= 200.0 && Math.abs(prevY[0] - ((int) yPosition / 200) * 200) <= 200.0;
    }

    private boolean isInsideGrid(int xPosition, int yPosition) {
        return (xPosition <= 3) && (yPosition <= 3);
    }

    private boolean isTileFree(Tile tile){
        return tile instanceof Free;
    }

    private boolean isInDifferentPlace(double xPosition, double yPosition){
        return (prevX[0] != xPosition * 200) || (prevY[0] != yPosition * 200);
    }

    public Scene getScene() throws Exception {
        return scene;
    }

}
