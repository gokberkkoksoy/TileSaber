package Scenes;


import Tools.VideoPlayer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.File;


public class EntryScreen {

    public static Scene scene;
    public static Button mute;
    String prev = "MUTE"; // Initially


    public EntryScreen(Stage primaryStage) {

        VideoPlayer videoPlayer = new VideoPlayer();
        mute = new Button(prev);
        Button show = new Button("START!");
        show.setLayoutX(505);
        show.setLayoutY(500);
        show.setStyle("-fx-background-color: #29EEF7;-fx-border-color: \t#000000;");
        Pane pane = new Pane();
        Rectangle headLine = new Rectangle(259,100,583,154);
        headLine.setFill(new ImagePattern(new Image(new File("images/tileSaber.png").toURI().toString())));
        pane.getChildren().addAll(videoPlayer.playIntroVideo(),show, mute);
        mute.setOnAction(event -> {
            if(mute.getText().equals("MUTE")){
                //audioPlayer.stopIntro();
                videoPlayer.pauseIntroVoice();
                mute.setText("UNMUTE");
                prev = "UNMUTE";
            } else {
                //audioPlayer.playIntro();
                videoPlayer.playIntroVoice();
                mute.setText("MUTE");
                prev = "MUTE";
            }
        });

        scene = new Scene(pane,1100,550);
        show.setOnAction(event -> {
            GameScreen gameScreen = new GameScreen(primaryStage);
            try {
                primaryStage.setScene(gameScreen.getScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //audioPlayer.stopIntro();
            videoPlayer.pauseIntroVideo();

            try {
                gameScreen.setGamePane();
            } catch (Exception ignored) {}
            gameScreen.gamePlay();
        });

    }

    public Scene getScene() {
        return scene;
    }

}
