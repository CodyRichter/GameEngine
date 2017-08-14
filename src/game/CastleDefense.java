package game;

import base.Unit;
import game.enemies.*;
import game.friendly.Friendly;
import game.friendly.turrets.Turret;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Frank Williams on 5/8/2017.
 */
public class CastleDefense {

    //X-Values Of Board JPanel That Rows Are At And That Units Are Spawned At
    public static final int ROW1Y = (int)(100*(Startup.screenHeight/900));
    public static final int ROW2Y = (int)(300*(Startup.screenHeight/900));
    public static final int ROW3Y = (int)(500*(Startup.screenHeight/900));

    public static final int TURRET_PLACEMENT_LIMIT = (int)(Startup.screenWidth/3)*2;

    //Current Wave Player Is On
    private static int wave = 0;
    private static int enemyAmount = 0;
    public static boolean gameOver = false;
    public static boolean paused = false;
    public static boolean nextWave = false;
    public static boolean waitingForWave = false;
    public static boolean tutorial = true;
    public static boolean scoutHasSpawned = false;
    public static int turretsPlaced = 0;
    public static int turretPlacementLimit = 0;


    public static int highestWave = 0;

    //Lists Holding All Units Spawned In On Board
    public static List<Enemy> enemies = new CopyOnWriteArrayList<Enemy>();
    public static List<Friendly> friendlies = new CopyOnWriteArrayList<Friendly>();
    public static List<Turret> turrets = new CopyOnWriteArrayList<Turret>();

    //Money Player Has In Game - Set This Value To Be The Amount of Starting Money Player Has
    public static final int STARTING_BALANCE = 100;
    private static int balance = STARTING_BALANCE;

    //Amount of Lives Player Has: Player Will Lose a Life If a Unit Gets To The Other Side Of The Board
    private static int lives = 3;

    private static Timer t = new Timer();

    private static ArrayList<Class> availableEnemies = new ArrayList<>();

    public static void main(int control) {
        if (control == 0 && nextWave) {
            nextWave = false;
            waitingForWave = true;
            scoutHasSpawned = false;
            if (Startup.VERBOSE) System.out.println("WAITING FOR WAVE");
            nextWave();
        } else if (control > 0) {
            waitingForWave = true;
            if (!scoutHasSpawned)
            {
                spawnScout();
                scoutHasSpawned = true;
            }

            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    int icontrol = control;

                    System.out.println("Enemy Spawn Started");
                    int enemyType = (int) (Math.random() * availableEnemies.size());
                    try {
                        if (Startup.VERBOSE)
                            System.out.println("SPAWNING ENEMY OF TYPE " + availableEnemies.get(enemyType).getName());
                        Object e = availableEnemies.get(enemyType).newInstance();
                        int row = (int) (Math.random() * 3) + 1;
                        if (e instanceof Enemy) {
                            ((Enemy) e).spawn(row);
                            System.out.println(e.toString() + " Spawned in row " + row);
                        }
                        icontrol--;
                        enemyAmount = control; //to keep track while paused
                        waitingForWave = false;
                    } catch (InstantiationException ie) {
                        System.out.println("InstantiationException when spawning enemy: " + ie.getMessage());
                    } catch (IllegalAccessException iae) {
                        System.out.println("IllegalAccessException when spawning enemy: " + iae.getMessage());
                    }
                    main(icontrol);
                    System.out.println();
                }
            }, ((Startup.ADMINMODE && Startup.ACTIONMODE) ? 1500 : 5000));

        }
    }

    public static void nextWave(){
        if (waitingForWave) return;
        if (tutorial) return;
        for (Friendly f : friendlies){
            if (!f.isDead() && f.isSiegeWeapon()){
                if (f instanceof Turret){}
                else {
                    double multiplier = f.getCurrentHealth() / f.getMaxHealth();
                    int amount = (int) (f.getUnitCost(f) * multiplier);
                    addMoney(amount);
                    if (Startup.VERBOSE) System.out.println(f + "HAS REACHED END OF BOARD. KILLING...");
                    if (!f.toString().equals("") && !f.toString().equalsIgnoreCase("scout")) {
                        Startup.b.sendNotification("Recieved $" + amount + " For " + f.toString());
                    }

                    f.kill();
                }
            }
        }
        wave++;
        addMoney(50 * wave);
        enemies.clear();
        friendlies.clear();
        Startup.b.unitList.clear();

        for (Turret t:turrets)
        {
            if (t.isDead())
                turrets.remove(t);
        }

        for (Unit u: turrets)
            Startup.b.unitList.add(u);

        if(Startup.VERBOSE) System.out.println("WAVE SETUP");
        if (wave >= 0 && wave < 10 && !availableEnemies.contains(Peasant.class)){
            if(Startup.VERBOSE) System.out.println("PEASANT AVAILABLE");
            availableEnemies.add(Peasant.class);
        }
        if (wave >= 3 && !availableEnemies.contains(Archer.class)) {
            if(Startup.VERBOSE) System.out.println("ARCHER AVAILABLE");
            availableEnemies.add(Archer.class);
        }
        if (wave >= 5 && !availableEnemies.contains(Barbarian.class)) {
            if(Startup.VERBOSE) System.out.println("BARBARIAN AVAILABLE");
            availableEnemies.add(Barbarian.class);
        }
        if (wave >= 7 && !availableEnemies.contains(Cavalry.class)){
            if(Startup.VERBOSE) System.out.println("CAVALRY AVAILABLE");
            availableEnemies.add(Cavalry.class);
        }
        if (wave >= 9 && !availableEnemies.contains(Assassin.class)){
            if(Startup.VERBOSE) System.out.println("ASSASSIN AVAILABLE");
            availableEnemies.add(Assassin.class);
            if(Startup.VERBOSE) System.out.println("PEASANT -NOT- AVAILABLE");
            availableEnemies.remove(Peasant.class);
            if(Startup.VERBOSE) System.out.println("ENRAGED PEASANTS AVAILABLE");
            availableEnemies.add(EnragedPeasant.class);
        }

        enemyAmount = 3 * wave;
        System.out.println("Wave " + wave + " Started \n");
        Startup.b.sendNotification("Wave " + wave + " Started!");
        main(enemyAmount);
        turretPlacementLimit = wave/2;
        Startup.menu.repaint();

    }

    public static void addEnemy(Enemy e){
        enemies.add(e);
    }

    public static void addFriendly(Friendly f){friendlies.add(f);}

    public static void addTurret(Turret t){turrets.add(t);}



    public static void doAction(Friendly u)
    {
        //Basic Conditions That Need To Be Met: Unit Has Health Remaining And Isn't At Edge Of Board
        if (u.getX() < Startup.b.getWidth()-((int)(Startup.widthFactor * 50)))
        {
            //Will Exit Method If Unit Is Currently Completing an Action
            if (u.isInAction()) return;

            u.currentlyAttacking = false;
            for (int i = (int)u.getX()+((int)(Startup.widthFactor * 50)); i < (((int)(Startup.widthFactor * 50))+(int)u.getX())+(u.getRange())*((int)(Startup.widthFactor * 20)); i++)
            {

                for(Enemy e : enemies) {
                    if (!e.isDead() && e.getX() == i && e.getY() == u.getY() && !e.isProjectile()) {
                        u.attack(e);
                        if(Startup.VERBOSE) System.out.println(u + " ATTACKING " + e);
                        u.currentlyAttacking = true;
                    } else {}
                }
            }

            if (!u.currentlyAttacking && (!u.isSiegeWeapon() || nextWave)) {
                u.move();
               // if(Startup.VERBOSE) System.out.println(u + " moving");
            } else if (u.isSiegeWeapon() && (nextWave || waitingForWave)){
                u.move();
            }

        }
        else if (u.getX() >= Startup.b.getWidth()-(((int)(Startup.widthFactor * 50))))
        {
            double multiplier = u.getCurrentHealth()/u.getMaxHealth();
            int amount = (int)(u.getUnitCost(u)*multiplier);
            addMoney(amount);
            if(Startup.VERBOSE) System.out.println(u + "HAS REACHED END OF BOARD. KILLING...");
            if (!u.toString().equals("") && !u.toString().equalsIgnoreCase("scout"))
            {
                Startup.b.sendNotification("Recieved $" + amount + " For " + u.toString());
            }

            u.kill();
        }
        nextWave = true;
        for (Friendly f : friendlies){
            if (!f.isDead() && !f.isProjectile()){
                nextWave = false;
            }
        }
        for (Enemy e : enemies){
            if (!e.isDead()){
                nextWave = false;
            }
        }
    }

    public static void doAction(Turret t)
    {
            //Will Exit Method If Unit Is Currently Completing an Action
            if (t.isInAction()) return;

            t.currentlyAttacking = false;
            for (int i = (int)t.getX()+((int)(Startup.widthFactor * 50)); i < (((int)(Startup.widthFactor * 50))+(int)t.getX())+(t.getRange())*((int)(Startup.widthFactor * 20)); i++)
            {

                for(Enemy e : enemies) {
                    if (!e.isDead() && e.getX() == i && e.getY() == t.getY() && !e.isProjectile()) {
                        t.attack(e);
                        if(Startup.VERBOSE) System.out.println("[Turret] " + t + " ATTACKING " + e);
                        t.currentlyAttacking = true;
                    } else {}
                }
            }
    }

    public static void doAction(Enemy u)
    {
        //Basic Conditions That Need To Be Met
        if (u.getX() > 0)
        {
            nextWave = false;
            //Will Exit Method If Unit Is Currently Completing an Action
            if (u.isInAction()) return;

            u.currentlyAttacking = false;
            if (u instanceof Assassin)
            {
                Assassin a = (Assassin) u;
                if (!a.hasJumped) {
                    for (int i = (int) a.getX() - 30 * ((int) (Startup.widthFactor * 20)); i < (int) a.getX(); i++) {
                        for (Friendly f : friendlies) {
                            if (!a.hasJumped && !f.isDead() && f.getX() + ((int) (Startup.widthFactor * 50)) == i && a.getY() == f.getY() && !f.isProjectile()) {
                                a.changeRow();
                                if (Startup.VERBOSE) System.out.println(a + " MOVING ROWS " + f);
                            } else {

                            }
                        }
                    }
                }

            }
            for (int i = (int)u.getX() - ((int)u.getRange())*((int)(Startup.widthFactor * 20)); i < (int)u.getX(); i++)
            {

                for(Friendly f : friendlies) {
                    if (!f.isDead() && f.getX() + ((int)(Startup.widthFactor * 50)) == i && u.getY() == f.getY()  && !f.isProjectile()) {
                        u.attack(f);
                        u.currentlyAttacking = true;
                        if(Startup.VERBOSE) System.out.println(u + " ATTACKING " + f);
                    } else {

                    }
                }

                for(Turret t : turrets) {
                    if (!t.isDead() && t.getX() + ((int)(Startup.widthFactor * 50)) == i && u.getY() == t.getY()  && !t.isProjectile()) {
                        u.attack(t);
                        u.currentlyAttacking = true;
                        if(Startup.VERBOSE) System.out.println(u + " ATTACKING " + t);
                    } else {

                    }
                }
            }

            if (!u.currentlyAttacking) {
                u.move();
                //if(Startup.VERBOSE) System.out.println(u + " moving");
            }
        }
        else if (u.getX() <= 0 && !u.isDead() && !u.isProjectile())
        {
            if(Startup.VERBOSE) System.out.println(u + "HAS REACHED END OF BOARD. GAME OVER.");
            loseLife();
            u.kill();
        }
        else if (u.getX() <= 0 && !u.isDead() && u.isProjectile())
        {
            if (Startup.VERBOSE) System.out.println(u + " (PROJECTILE) HAS REACHED THE END OF BOARD. KILLING...");
            u.kill();

        }
    }

    public static void pause(){
        paused = true;
        if (Startup.VERBOSE) System.out.println("Paused with " + enemyAmount + " left");
        t.cancel();
    }
    public static void resume(){
        paused = false;
        t = new Timer();
        main(enemyAmount);
    }

    public static void loseLife()
    {
        lives--;
        if (lives <= 0) {
            endGame();
        }
        Startup.menu.repaint();
    }


    public static void endGame() {
        gameOver = true;
        Startup.saveData[0] = "T";
        Startup.saveData[1] = "" + wave;
        Startup.b.repaint();
        SaveEditor.writeToFile(Startup.saveFile, Startup.saveData);
    }

    //
    //
    // Economy
    //
    //

    /**
     * Takes Money From Player's Balance
     */
    public static void subtractMoney(int amount)
    {
        if (amount < 0) {}
        if (amount > balance) balance = 0;
        else balance -= amount;
        Startup.b.repaint();
    }

    /**
     * Adds Money To Player's Balance
     */
    public static void addMoney(int amount)
    {
        if (amount < 0) {}
        else balance += amount;
        Startup.menu.repaint();
    }

    /*
     * Sets Player's Money To a Value
     */
    public static void setMoney(int amount)
    {
        if (amount < 0) return;
        balance = amount;
        Startup.menu.repaint();
    }

    public static int getBalance()
    {
        return balance;
    }

    public static int getWave() {return wave;}

    public static int getLives() {return lives;}

    public static boolean checkTurretSpawn(double x, int row)
    {
        boolean result = true;
        if (turretsPlaced >= turretPlacementLimit) return false;
        for (Turret existingTurret: turrets)
        {
            if ((x > (existingTurret.getX() + 110) || x < (existingTurret.getX() - 110)) || existingTurret.getY() != CastleDefenseBoard.getCoordinateFromRow(row))
            {
                result = true;
            }
            else return false;
        }
        return true;
    }

    private static class WaveScout extends Friendly {
        public WaveScout(){
            super(1,0,1,2);
            this.setSprite("knight");
        }

        public static int getUnitCost()
        {
            return 0;
        }

        @Override
        public String toString() {
            return "Scout";
        }
    }

    public static void spawnScout()
    {
        WaveScout s = new WaveScout();
        s.spawn(2);
    }

}
