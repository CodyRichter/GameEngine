package game.enemies;

import game.Enemy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Frank Williams on 5/8/2017.
 */
public class BasicEnemy extends Enemy {
    public BasicEnemy() throws IOException{
        super(1, 1, 1, 1, null);
        this.setSprite("basic");
    }

    public void testMovement(){
        move();
    }


    @Override
    public void attack() {

    }

    @Override
    public void onDeath() {

    }
}
