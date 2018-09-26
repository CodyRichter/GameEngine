# GameEngine
Project
-------------------------------------------

Castle Defense is a wave based game that involves both
friendly and enemy units battling to cross the screen.
In order to progress to the next wave, all the enemies
must be killed, and all friendlies must reach the right
side. However, when enemies reach the far side, its
game over. 


**To see released versions and download executable jar, Go To:**
*https://github.com/Senarii/GameEngine/releases*


# Controls:

Select Row: Arrow Up/Arrow Down

Spawn Units: Number Keys [1-9]

Exit Game: [Esc]

# Basic Gameplay

When you begin a game of Castle Defense, you'll be walked through a brief tutorial explaining the controls of the game, and the basic rules of how to play. Use the number keys [1-9] to spawn in units, and the arrow keys (arrow up/down) to select a row to spawn the units into.

When the tutorial is finished, you will begin on wave one.

To move on to the next wave, all enemy units must be eliminated, and your friendly units must reach the other side of the screen.


# Defensive Structures

## Overview

In Castle Defense 2.0, it is now possible to place turrets and defenses to protect your base. These static defenses will remain between waves, allowing them to engage enemy units before your soldiers can reach them. Additionally, defenses such as the Barricade will help provide a barrier between fast-moving enemies and your castle, allowing you time to respond to the threat.

### Placing Defenses
To go into Defense Placement Mode, press [D] while in game. This is a toggle that will allow you to place turrets instead of normal units. Unlike Unit Placement Mode, you are able to place down the turrets at any point along a lane, instead of just at the end. The unit list shown when [U] is pressed contains the types of turrets able to be placed, and this menu will update depending on the placement mode that is enabled.


## Cannon
The Cannon is the best all-around defense against every type of enemy. It has a good range, respectable damage output, short reload, and low cost to spawn. While its health pool is low, it is able to use its range to fire over a barricade and hit enemies attacking the barricade. Also, as the turret remains alive between waves, it is good at defending your back lines against quick enemies, such as Cavalry.

    Health: 10
    Damage: 2
    Range: 30
    Reload Time: 3 Seconds
    Spawning Cooldown: 45 Seconds
    Cost: $350

## Barricade
The Barricade is a glorified wall that can do ANYTHING a wall can, except for take more than 30 damage. Its large health lets it take punishment over multiple waves without being destroyed, and it can be used to shield vulnerable defenses like the cannon. When undefended, it will die quickly, however with even just 1 turret placed behind it, it quickly transforms into an impenetrable fortification.

    Health: 30
    Spawning Cooldown: 60 Seconds
    Cost: $500

## X-Bow
The X-Bow acts as superb long-range artillery capable of hitting enemies from 50 away. Its long reload period makes it ineffective at close-ranged combat, but when placed at the back of lines, it can deal consistent damage to enemies over time, with little risk to the turret itself. Featuring a unique 3-round burst of high-velocity arrows, the turret can quickly thin out clustered swarms of enemies attacking.

    Health: 15
    Damage: 2 (Per Arrow)
    Burst Fire: 3 Arrows
    Range: 50
    Reload Time: 10 Seconds
    Spawning Cooldown: 60 Seconds
    Cost: $750

# Friendly Units

## Militia
The Militia is a basic, disposable unit effective against weak enemies such as Peasants, and to delay enemy attacks on a row. It is very cost effective, and has a short cool down, making it an ideal disposable unit.

    Health: 5
    Damage: 1
    Speed: 1
    Range: 1
    Attack Delay: 1.3 Seconds
    Spawning Cooldown: 3 Seconds
    Cost: $50

## Infantry
Infantry is a powerful unit best suited to attacking multiple enemies or tanking damage with its above average health pool. Additionally, its high damage allows it to destroy light enemies before they are given an opportunity to respond.

    Health: 10
    Damage: 3
    Speed: 1
    Range: 1
    Attack Delay: 2 Seconds
    Spawning Cooldown: 10 Seconds
    Cost: $200

## Knight
The Knight is the fastest unit you have at your disposal, capable of crushing multiple enemies in a row. However, its speed is also its largest weakness, and the unit can easily outrun the spawn rate of the enemy, and be you'll be stuck letting it cool down. Due to the high alpha damage, its effective at destroying isolated units, but it struggles dealing with massed enemies.

    Health: 12
    Damage: 4
    Speed: 3
    Range: 1
    Attack Delay: 3 Seconds
    Spawning Cooldown: 20 Seconds
    Cost: $350

## Crossbowman
The Crossbowman is an excellent second-line fighter capable of firing a large amount of arrows downrange at enemies from a distance. While their low health pool makes them susceptible to direct attacks, their long range is able to offset this. Additionally, their range allows them to fire from behind defenses, which protects the unit from damage.

    Health: 6
    Damage: 2
    Speed: 1
    Range: 20
    Attack Delay: 3 Seconds
    Spawning Cooldown: 10 Seconds
    Cost: $250

# Enemy Units

## Peasant
The Peasant is the most basic enemy unit, featuring a small health pool, weak attack, and lack of any interesting traits whatsoever. However, Peasants can be dangerous when spawned in large numbers, or when acting as shields for ranged units.

    Health: 3
    Damage: 1
    Speed: 1
    Range: 1
    Attack Delay: 1 Second
    First Seen: Wave 1

## Archer
The Archer is a dangerous ranged unit that shoots arrows. While they can be swiftly dealt with alone, the Archer can be extremely dangerous when sheltered behind other units.

    Health: 5
    Damage: 1
    Speed: 1
    Range: 20
    Attack Delay: 2 Seconds
    First Seen: Wave 3

## Barbarian
The Barbarian is a heavily armored infantry unit which sacrifices speed and attack damage for health. Capable of taking out multiple Militia units alone, it is a threat to be reckoned with.

    Health: 14
    Damage: 2
    Speed: 1
    Range: 20
    Attack Delay: 2 Seconds
    First Seen: Wave 5

## Cavalry
The Cavalry excels in destroying lightly armored units, and in sneaking through undefended rows. While it isn't much of a threat once locked in combat, it can be devastating when it appears in a lightly guarded lane.

    Health: 7
    Damage: 2
    Speed: 3
    Range: 2
    Attack Delay: 1 Second
    First Seen: Wave 7

## Assassin
The Assassin is a master of disguise, capable of jumping between rows to avoid incoming units. When he spots approaching units, he will jump to an adjacent row and continue his advance, effectively dodging the troops. However, his low health pool makes him susceptible to armored units such as the Knight or Infantry.

    Health: 6
    Damage: 1
    Speed: 2
    Range: 1
    Attack Delay: 1/2 Second
    First Seen: Wave 9





Authors:

Cody R.

Frank W.
