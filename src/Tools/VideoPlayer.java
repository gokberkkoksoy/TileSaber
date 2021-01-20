package Tools;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.io.File;

public class VideoPlayer {

    private MediaPlayer endVideoPlayer;
    private MediaView endView;
    private MediaPlayer introVideoPlayer;
    private MediaView introView;
    public VideoPlayer(){
        Media endVideo = new Media(new File("videos/endVideo.mp4").toURI().toString());
        endVideoPlayer = new MediaPlayer(endVideo);
        endView = new MediaView(endVideoPlayer);
        Media introVideo = new Media(new File("videos/introVideo.mp4").toURI().toString());
        introVideoPlayer = new MediaPlayer(introVideo);
        introView = new MediaView(introVideoPlayer);
    }

    public MediaView playEndVideo(){
        endView.setFitHeight(800);
        endView.setFitWidth(1100);
        endVideoPlayer.play();
        return endView;
    }

    public void pauseEndVideo(){
        endVideoPlayer.pause();
    }
    public void pauseEndVoice(){
        endVideoPlayer.setVolume(0);
    }
    public void playEndVoice(){
        endVideoPlayer.setVolume(100);
    }

    public MediaView playIntroVideo(){
        introView.setFitWidth(1100);
        introView.setFitHeight(800);
        introVideoPlayer.play();
        return introView;
    }

    public void pauseIntroVoice(){
        introVideoPlayer.setVolume(0);
    }
    public void playIntroVoice() {
        introVideoPlayer.setVolume(100);
    }
    public void pauseIntroVideo(){
        introVideoPlayer.pause();
    }



}
