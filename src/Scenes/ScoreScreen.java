package Scenes;

import Tools.VideoPlayer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class ScoreScreen {
    Scene scene;
    Button mute;

    public ScoreScreen(Stage primaryStage){
        VideoPlayer videoPlayer = new VideoPlayer();
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color:#000000");
        HBox bottomPane = new HBox();
        bottomPane.setSpacing(75);
        HBox topPane = new HBox();
        Text id = new Text("Created by Gokberk Koksoy and Serenay Demir");
        mute = new Button(GameScreen.prev);

        topPane.setAlignment(Pos.CENTER);
        topPane.setSpacing(75);
        id.setStyle("-fx-fill:#FFFF00;-fx-font-size: 40;");
        Text greet = new Text("Thank you for playing!");
        greet.setStyle("-fx-fill:#FFFF00;-fx-font-size: 40;");
        Button restart = new Button("Restart");
        topPane.getChildren().addAll(mute,id);
        pane.setTop(topPane);
        bottomPane.getChildren().addAll(greet, restart);
        bottomPane.setAlignment(Pos.CENTER);
        if(mute.getText().equals("MUTE")){
            pane.setCenter(videoPlayer.playEndVideo());
        } else {
            pane.setCenter(videoPlayer.playEndVideo());
            videoPlayer.pauseEndVoice();
        }
        pane.setBottom(bottomPane);
        mute.setOnAction(event -> {
            if(mute.getText().equals("MUTE")){
                videoPlayer.pauseEndVoice();
                mute.setText("UNMUTE");
                EntryScreen.mute.setText("UNMUTE");
            } else {
                videoPlayer.playEndVoice();
                mute.setText("MUTE");
                EntryScreen.mute.setText("MUTE");
            }
        });



        scene = new Scene(pane, 1100,800);
        restart.setOnAction(event -> {
            try {
                //EntryScreen entryScreen = new EntryScreen(primaryStage);
                videoPlayer.pauseEndVideo();
                GameScreen gameScreen = new GameScreen(primaryStage);
                primaryStage.setScene(gameScreen.getScene());
                gameScreen.setGamePane();
                gameScreen.gamePlay();
            } catch (Exception ignored) {
            }

        });
    }

    public Scene getScene() {
        return scene;
    }
}
