import java.util.Random;
/**
 * Class Mob - a mob in the game.
 *
 * This class is part of "The peacemaker" application.
 * "The peacemaker" is a simple, text based adventure game.
 *
 * A "Mob" represents one single mob in the game. Mobs can be found in different places.
 * A player has to fight mobs to get items, trophies and finally, win the game.
 *
 * @author Vakaris Paulavičius (Student number: 20062023)
 * @version 2020.11.26
 */
public class Mob
{
    private String name;
    private Trophy trophy;
    private int health;
    private int maxHealth;
    private int minDamage;
    private int maxDamage;
    private boolean alive;
    private int chanceToDropTrophy;
    private String description;

    /**
     * Create new mob.
     *
     * @param name Mob's name.
     * @param trophy A Trophy object, that this mob can drop.
     * @param health Mob's initial health.
     * @param minDamage Minimum amount of damage this mob does.
     * @param minDamage Maximum amount of damage this mob does.
     * @param chanceToDropTrophy The chance (0-100%) that this mob will drop the trophy when killed.
     */
    public Mob(String name, Trophy trophy, int maxHealth, int minDamage, int maxDamage, int chanceToDropTrophy)
    {
        this.name = name;
        this.trophy = trophy;
        health = maxHealth;
        this.maxHealth = maxHealth;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.chanceToDropTrophy = chanceToDropTrophy;
        alive = true;
        setDescription();
    }

    /**
     * This method is used to get the name of the mob.
     * @return mob name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method is used to get the current health of the mob.
     * @return current mob health.
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * This method is used to set the current health to a particular amount.
     * If the mob is already dead, set its status to alive and set the current health.
     *
     * @param amount Amount which the health is set to.
     */
    public void setHealth(int amount)
    {
        this.health = amount;
        if(!alive)
        {
            alive = true;
        }
        setDescription();
    }

    /**
     * This method is used to get the maximum health of the mob.
     * @return maximum mob health.
     */
    public int getMaxHealth()
    {
        return maxHealth;
    }

    /**
     * This method is used to get the trophy this mob drops.
     * @return trophy this mob drops
     */
    public Trophy getTrophy()
    {
        return trophy;
    }

    /**
     * This methodis used to set the mob dead
     */
    public void setDead()
    {
        alive = false;
    }

    /**
     * This method returns the state of the mob.
     * @return true if alive, false if dead.
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * This method is used to reduce mob's current health by the amount in parameter.
     * @param amount An amount by which to decrement mob's health.
     */
    public void reduceHealth(int amount)
    {
        if((health-amount) > 0)
        {
            health -= amount;
        }
        else
        {
            setDead();
        }
        setDescription();
    }

    /**
     * This method is used to get a random damage this mob does from the range: (minDamage-maxDamage).
     * @return random amount of damage in a single hit.
     */
    public int getDamage()
    {
        Random damageGenerator = new Random();
        int damage = damageGenerator.nextInt(maxDamage-minDamage) + minDamage;
        return damage;
    }

    /**
     * This method is used to get the minimum damage this mob does.
     * @return mob minimum damage.
     */
    public int getMinDamage()
    {
        return minDamage;
    }

    /**
     * This method is used to get the maximum damage this mob does.
     * @return mob maximum damage.
     */
    public int getMaxDamage()
    {
        return maxDamage;
    }

    /**
     * This method calculates whether this mob drops trophy when killed.
     * @return true if drops the trophy, false if does not.
     */
    public boolean dropsTrophy()
    {
        Random prob = new Random();
        int number = prob.nextInt(100);

        if((number + 1) <= chanceToDropTrophy)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This method is used to get the description of this mob.
     * @return mob description
     */
    public String getDescription()
    {
        return description;
    }

    // ***** PRIVATE METHODS *****

    /**
     * This method is used to set the description for the mob.
     */
    private void setDescription()
    {
        String line1 = ("Name: " + name),
                line2 = ("Damage: " + minDamage + "-" + maxDamage),
                line3 = ("Trophy that this mob might drop: " + trophy.getName()),
                line4 = ("Current health: " + health);

        description = line1 + "\n " + line2 + "\n " + line3 + "\n " + line4;
    }
}
