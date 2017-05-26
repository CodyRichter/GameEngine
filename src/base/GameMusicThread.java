package base;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by frank2williams on 2/5/41.
 */
public class GameMusicThread implements Runnable {

    public GameMusicThread(){

    }
    public void run(){
        AudioPlayer player = AudioPlayer.player;
        AudioStream background;
        AudioData musicData;
        ContinuousAudioDataStream loop = null;

        try {
            background = new AudioStream(getClass().getResourceAsStream("/game/sounds/BackgroundDraft.wav"));
            musicData = background.getData();
            loop = new ContinuousAudioDataStream(musicData);
        } catch(IOException error)  {
            System.out.println("Error Playing Music");

        }
        player.start(loop);
    }
}
