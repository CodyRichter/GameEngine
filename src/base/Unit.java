package base;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Contains All Methods for Enemies
 * 
 * @author Cody Richter
 * @version 1.0
 */
public abstract class Unit
{
    //Attributes For Each base.Unit
    protected double maxHealth = 1; //Maximum Health base.Unit can have
    protected double currentHealth = 1; //Current Health level of unit
    protected double damage = 1; //Amount of Damage Caused per Attack of base.Unit
    protected double attackRange = 1; //Attacking Range of base.Unit
    protected double moveSpeed = 1; //Movement Speed of base.Unit
    protected double delayBetweenAttacks = 1; //Delay Between Attacks

    //Location Information For Each Unit
    //protected Rectangle2D bounds;
    protected Point2D pos;

    //base.Unit Sprite Information
    protected BufferedImage sprite;


    protected boolean isEnemy; //Defines Unit Allegiance

    protected Unit()
    {

    }

    /**
     * Creates Unit base.Unit With Given Attributes
     */
    protected Unit(int healthLevel, int damageLevel, int range, int speed, BufferedImage spriteToLoad, Rectangle2D currentPosition)
    {
        maxHealth = healthLevel;
        currentHealth = healthLevel;
        damage = damageLevel;
        attackRange = range;
        moveSpeed = speed;
        pos = new Point2D.Double();

        if (spriteToLoad != null && currentPosition != null){
            sprite = spriteToLoad;
            //bounds = currentPosition;
        }
    }

    //
    // Abstract Per-base.Unit Methods
    //

    /**
     * Attacks Target 
     */
    public abstract void attack();

    /**
     * When base.Unit Dies
     */
    public abstract void kill();

    //
    // Setters
    //

    /**
     * Sets base.Unit's Health To A Specific Amount
     */
    public void setHealth(double newHealth)
    {
        if (newHealth > maxHealth) //Prevents base.Unit From Getting More Health Than Max Amount
        {
            currentHealth = maxHealth;
        }
        else if (newHealth <= 0) //Automatically Kills base.Unit If Health Is Set To 0 Or Below
        {
            kill();
        }
        else //If Parameters Are Valid, Set base.Unit Health To Desired Value
        {
            currentHealth = newHealth;
        }
    }

    /**
     * Damages base.Unit By a Given Amount
     */
    public void damage(double amount)
    {
        currentHealth -= amount;
        if (currentHealth <= 0) //If base.Unit Takes More Damage Than Health, Kill base.Unit
            kill();
    }

    public void heal(double amount)
    {
        currentHealth += amount;
        if (currentHealth > maxHealth) //If base.Unit Is Healed By More Than Max Health, Heal Fully
            currentHealth = maxHealth;
    }


    /*
    Will Move Unit's Point2D That Represents The Position On The Unit - Different Implementation For Friendly and Enemy
     */
    public abstract void move();

    //
    // Getters
    //

    public double getMaxHealth() //Returns Maximum Health of base.Unit
    {
        return maxHealth;
    }

    public double getCurrentHealth() //Returns Current Health of base.Unit
    {
        return currentHealth;
    }

    public double getDamage() //Returns Attack Damage of base.Unit
    {
        return damage;
    }

    public double getRange() //Returns Attack Range of base.Unit
    {
        return attackRange;
    }

    /**
     * Gets Sprite
     */
    public BufferedImage getSprite()
    {
        return sprite;
    }

    public void setSprite(BufferedImage spriteToLoad) {
        sprite = spriteToLoad;
    }

    public void setSprite(String fileName){
        InputStream in = getClass().getResourceAsStream("/game/images/" + fileName + ".bmp");
        try {
            sprite = ImageIO.read(in);
        } catch (IOException ioe){
            System.out.println("IO Exception " + ioe.getMessage());
        }
        //bounds.setRect(0,0, 50, 100);
    }

    public double getX(){return pos.getX();}

    public double getY(){return pos.getY();}


    /**
     * Spawns Unit In a Given Row
     */
    public abstract void spawn(int row);

}
