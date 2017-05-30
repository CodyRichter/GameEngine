package base;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
            InputStream is = getClass().getResourceAsStream("/game/sounds/BackgroundDraft.wav");
            background = new AudioStream(is);
            //musicData = background.getData();
            //loop = new ContinuousAudioDataStream(musicData);
        } catch(IOException ioe)  {
            System.out.println(ioe.toString()+ " Error Playing Music " + ioe.getMessage());

        }
        //player.start(loop);
    }
}
