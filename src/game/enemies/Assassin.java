package game.enemies;

import game.CastleDefense;

/**
 * Enemy Unit: Peasant
 *
 * @author Cody Richter 
 * @version 1.0
 */
public class Assassin extends Enemy
{
    public boolean hasJumped = false;

    public Assassin()
    {
        super(6, 1, 1, 2,200, null);
        delayBetweenAttacks = 0.5;
        this.setSprite("assassin");
    }

    public void changeRow()
    {
        double currentRow = this.getY();
        if (currentRow == CastleDefense.ROW1Y)
        {
           this.setRow(2);
        }
        else if (currentRow == CastleDefense.ROW2Y)
        {
            double num = Math.random();
            if (num < .50)
            this.setRow(1);
            else
                this.setRow(3);
        }
        else if (currentRow == CastleDefense.ROW3Y)
        {
            this.setRow(2);
        }
        hasJumped = true;
        this.setSprite("assassin2");
    }

    public String toString(){return "Assassin";}

}
