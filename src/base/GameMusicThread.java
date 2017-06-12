package base;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

/**
 * Created by frank2williams on 2/5/41.
 */
public class GameMusicThread implements Runnable {

    public GameMusicThread(){

    }
    public void run(){

        Media music = new Media(getClass().getResource("/game/sounds/Fake.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setStartTime(Duration.seconds(0));
        mediaPlayer.setStopTime(Duration.seconds(117));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

    }
}
