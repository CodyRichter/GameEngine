package base;

import game.Main;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Abstract Class: Unit
 *
 * @author Cody Richter
 * @version 1.0
 */
public abstract class Unit {
    //Attributes For Each base.Unit
    protected double maxHealth = 1; //Maximum Health base.Unit can have
    protected double currentHealth = 1; //Current Health level of unit
    protected double damage = 1; //Amount of Damage Caused per Attack of base.Unit
    protected double attackRange = 1; //Attacking Range of base.Unit
    protected double moveSpeed = 1; //Movement Speed of base.Unit
    protected double delayBetweenAttacks = 1; //Delay Between Attacks (Seconds)
    protected int currentRow = 0;
    protected boolean doingAction = false;
    public boolean currentlyAttacking = false;
    protected boolean isDead = false;
    protected boolean areaAttack = false;
    protected boolean isProjectile = false;


    //Location Information For Each Unit
    //protected Rectangle2D bounds;
    protected Point2D pos;

    //base.Unit Sprite Information
    protected BufferedImage sprite;


    protected boolean isEnemy; //Defines Unit Allegiance

    protected Unit() {

    }


    /**
     * This Will Create A New Unit (Friendly or Enemy).
     * This Is The Constructor To Use In The Friendly and Enemy Sub-Classes.
     * Remember, The Variables Are Protected, So They CAN Be Modified From Any
     * Sub-Class.
     *
     * @param healthLevel
     * @param damageLevel
     * @param range
     * @param speed
     * @param spriteToLoad
     * @param currentPosition
     */
    protected Unit(int healthLevel, int damageLevel, int range, int speed, BufferedImage spriteToLoad, Rectangle2D currentPosition) {
        maxHealth = healthLevel;
        currentHealth = healthLevel;
        damage = damageLevel;
        attackRange = range;
        moveSpeed = speed;
        pos = new Point2D.Double();

        if (Main.VERBOSE) System.out.println(this + " CONSTRUCTED");
        if (spriteToLoad != null && currentPosition != null) {
            sprite = spriteToLoad;
            //bounds = currentPosition;
        }
    }

    /**
     * Attacks Target "u" - Causes Damage Relative To Area Attack And
     * The Attack Power Of Attacking Unit. Takes Into Account If The
     * Attacking Unit Is Currently In Action.
     *
     * @param u
     */
    public void attack(Unit u) {
        if ((!this.hasAreaAttack() && doingAction)) return;
        u.damage(damage);
        doingAction = true;

        //Sets Timer For Cooldown
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                doingAction = false;
                currentlyAttacking = false;
            }
        }, (int) (delayBetweenAttacks * 1000));

    }


    /**
     * Abstract Method That Will Be Run Whenever a Unit
     * Is Killed. This Must Remove The Unit From All
     * Updating Lists It Is In, and set isDead = true.
     */
    public abstract void kill();

    //
    // Setters
    //

    /**
     * Sets The Unit's Health Directly To A New Value
     * Will Safely Set Value To Account For Max/Min
     * Health Values.
     *
     * @param newHealth
     */
    public void setHealth(double newHealth) {
        if (newHealth > maxHealth) //Prevents base.Unit From Getting More Health Than Max Amount
        {
            currentHealth = maxHealth;
        } else if (newHealth <= 0) //Automatically Kills base.Unit If Health Is Set To 0 Or Below
        {

        } else //If Parameters Are Valid, Set base.Unit Health To Desired Value
        {
            currentHealth = newHealth;
        }
    }

    /**
     * Will Damage The Unit By A Given Amount.
     * This Will "Safely Damage" The Unit And Take
     * Into Account Minimum Health
     *
     * @param amount
     */
    public void damage(double amount) {
        currentHealth -= amount;
        if (currentHealth <= 0) {
            kill();
        }

    }

    /**
     * Will Heal The Unit By A Given Amount.
     * This Will Safely Heal The Unit And Take
     * Into Account Maximum Health
     *
     * @param amount
     */
    public void heal(double amount) {
        currentHealth += amount;
        if (currentHealth > maxHealth) //If base.Unit Is Healed By More Than Max Health, Heal Fully
            currentHealth = maxHealth;
    }


    /**
     * Moves The Unit Relative To Movement Speed
     * And In The Correct Direction Based on
     * Allegiance. This is Called In The Runnable,
     * So It Must Be Efficient!
     * Remember, Unit Positions Are Based On The Top
     * Left Coordinate Of The Sprite.
     */
    public abstract void move();

    /**
     * Sets The Unit's Sprite From a Currently Loaded BufferedImage
     *
     * @param spriteToLoad
     */
    public void setSprite(BufferedImage spriteToLoad) {
        sprite = spriteToLoad;
    }

    /**
     * Sets The Unit's Sprite From a Given
     * File Path. Will Load Image File as a
     * BufferedImage.
     *
     * @param fileName
     */
    public void setSprite(String fileName) {
        InputStream in = getClass().getResourceAsStream("/game/images/" + fileName + ".bmp");
        try {
            sprite = ImageIO.read(in);
        } catch (IOException ioe) {
            System.out.println("IO Exception when setting sprite: " + ioe.getMessage());
        } catch (IllegalArgumentException iae) {
            System.out.println("IllegalArgumentException when setting sprite: " + iae.getMessage());
        }
    }


    /**
     * Will Spawn In Unit In A Given Row. When
     * Implemented, This Should Add The Unit To
     * The Correct ArrayLists Based on Allegiance.
     *
     * @param row
     */
    public abstract void spawn(int row);

    //
    // Getters
    //

    /**
     * Returns Max Health of a Unit
     *
     * @return
     */
    public double getMaxHealth() {
        return maxHealth;
    }

    /**
     * Returns Current Health of a Unit
     *
     * @return
     */
    public double getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Returns The Amount of Damage a Unit
     * Causes Per Attack.
     *
     * @return
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Returns The Attack Range of a Unit
     *
     * @return
     */
    public double getRange() {
        return attackRange;
    }

    /**
     * Returns The Unit's Sprite as a BufferedImage
     *
     * @return
     */
    public BufferedImage getSprite() {
        return sprite;
    }

    /**
     * Returns Unit's X-Coordinate. This Corresponds To The
     * X In the Position Object Of The Unit.
     *
     * @return
     */
    public double getX() {
        return pos.getX();
    }

    /**
     * Returns Unit's Y-Coordinate. This Corresponds To The
     * Y In the Position Object Of The Unit.
     *
     * @return
     */
    public double getY() {
        return pos.getY();
    }

    /**
     * Returns If Unit Is Currently Doing An Action
     * With The Runnable Task. If This Is True, It
     * Is Usually Wise To Cancel Any Other Potential
     * Moves.
     *
     * @return
     */
    public boolean isInAction() {
        return doingAction;
    }

    /**
     * Returns If The Unit Is Currently Dead. If The
     * Unit Is Dead, Cancel All Moves The Unit Makes,
     * and Delete All References To The Unit ASAP.
     *
     * @return
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Returns If The Unit Has an Area Attack.
     * An Area Attack Will Allow One Attack
     * Animation To Damage All Enemies In Front Of
     * The Unit, Instead of Just One.
     *
     * @return
     */
    public boolean hasAreaAttack() {
        return areaAttack;
    }

    /**
     * Returns The Unit's Name To Display Above
     * The Sprite. Return "" To Not Display A
     * Name Above The Unit's Sprite.
     *
     * @return
     */
    public abstract String toString();

    /**
     * Returns Whether The Unit Is a Projectile.
     * Projectiles Are Spawned From Other Units,
     * and Have a Limited Range of Actions.
     *
     * @return
     */
    public boolean isProjectile() {
        return isProjectile;
    }

}
