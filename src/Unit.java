import java.awt.geom.*;
import java.awt.image.BufferedImage;
/**
 * Contains All Methods for Enemies
 * 
 * @author Cody Richter
 * @version 1.0
 */
public abstract class Unit
{
    //Attributes For Each Unit
    protected double maxHealth = 1; //Maximum Health Unit can have
    protected double currentHealth = 1; //Current Health level of unit
    protected double damage = 1; //Amount of Damage Caused per Attack of Unit
    protected double attackRange = 1; //Attacking Range of Unit
    protected double moveSpeed = 1; //Movement Speed of Unit
    protected double delayBetweenAttacks = 1; //Delay Between Attacks

    //Location Information For Each Unit
    private Rectangle2D bounds;
    private Point2D pos;

    //Unit Sprite Information
    private BufferedImage sprite;

    private static boolean ISENEMY; //Defines Unit Allegiance
    protected Unit()
    {

    }

    /**
     * Creates Unit Unit With Given Attributes
     */
    protected Unit(int healthLevel, int damageLevel, int range, int speed, BufferedImage spriteToLoad, Rectangle2D currentPosition)
    {
        maxHealth = healthLevel;
        currentHealth = healthLevel;
        damage = damageLevel;
        attackRange = range;
        moveSpeed = speed;
        if (spriteToLoad != null && currentPosition != null){
            sprite = spriteToLoad;
            bounds = currentPosition;
        }
    }

    //
    // Abstract Per-Unit Methods
    //

    /**
     * Attacks Target 
     */
    public abstract void attack();

    /**
     * When Unit Dies
     */
    public abstract void onDeath();

    //
    // Setters
    //

    /**
     * Sets Unit's Health To A Specific Amount
     */
    public void setHealth(double newHealth)
    {
        if (newHealth > maxHealth) //Prevents Unit From Getting More Health Than Max Amount
        {
            currentHealth = maxHealth;
        }
        else if (newHealth <= 0) //Automatically Kills Unit If Health Is Set To 0 Or Below
        {
            onDeath();
        }
        else //If Parameters Are Valid, Set Unit Health To Desired Value
        {
            currentHealth = newHealth;
        }
    }

    /**
     * Damages Unit By a Given Amount
     */
    public void damage(double amount)
    {
        currentHealth -= amount;
        if (currentHealth <= 0) //If Unit Takes More Damage Than Health, Kill Unit
            onDeath();
    }

    public void heal(double amount)
    {
        currentHealth += amount;
        if (currentHealth > maxHealth) //If Unit Is Healed By More Than Max Health, Heal Fully
            currentHealth = maxHealth;
    }

    /**
     * Moves Unit's Location Relative To Movement Speed
     * 
     * Changes The Point2D Which Represents The Bounding Box Rectangle Which Is the Hitbox
     */
    public void move()
    {
        pos.setLocation(pos.getX() + moveSpeed ,  pos.getY());
    }

    //
    // Getters
    //

    public double getMaxHealth() //Returns Maximum Health of Unit
    {
        return maxHealth;
    }

    public double getCurrentHealth() //Returns Current Health of Unit
    {
        return currentHealth;
    }

    public double getDamage() //Returns Attack Damage of Unit
    {
        return damage;
    }

    public double getRange() //Returns Attack Range of Unit
    {
        return attackRange;
    }

    /**
     * Get's Sprite's Bounds as a Rectangle2D
     */
    public Rectangle2D getBounds()
    {
        // If Pos is null and sprite isn't, make rectangle with bounds, otherwise return current rectangle with bounds
        return bounds == null && sprite != null ? new Rectangle2D.Double(0,0,sprite.getWidth(), sprite.getHeight()) : bounds;
    }

    /**
     * Gets Sprite
     */
    public BufferedImage getSprite()
    {
        return sprite;
    }

}
