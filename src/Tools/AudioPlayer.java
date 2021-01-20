package Tools;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class AudioPlayer {

    MediaPlayer introPlayer;
    Media intro;
    MediaPlayer playImperial;
    Media imperial;

    public AudioPlayer(){
        imperial = new Media(new File("audios/imperial.mp3").toURI().toString());
        playImperial = new MediaPlayer(imperial);
        intro = new Media(new File("audios/intro.mp3").toURI().toString());
        introPlayer = new MediaPlayer(intro);
    }


    public void playImperial(){
        playImperial.play();
    }
    public void stopImperial(){
        playImperial.pause();

    }
}
