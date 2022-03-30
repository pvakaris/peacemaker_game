import java.util.Random;
/**
 * Class Player - a player in the game.
 *
 * This class is part of "The peacemaker" application. 
 * "The peacemaker" is a simple, text based adventure game. 
 *
 * A "Player" object represents a character that a user is playing as.
 * It has basic attributes such as name, health and status.
 * An object of this class also follows it's current weapon, current and
 * previous locations. Every player instance has a backpack of a particular
 * size which is determined when creating a player.
 *
 * @author Vakaris Paulavičius (Student number: 20062023)
 * @version 2020.11.26
 */
public class Player
{
    // instance variables - replace the example below with your own
    private String name;
    private final int maxHealth = 100;
    private int currentHealth;
    boolean alive;

    private Room currentRoom;
    private Room previousRoom;
    private Weapon currentWeapon;

    private Backpack backpack;

    /**
     * Create a new player.
     *
     * @param gender Player's gender according to which a name is created.
     */
    public Player(String gender, Room currentRoom, int backpackSize)
    {
        name = setName(gender);  //Giving a name according to the provided gender
        currentHealth = 100;
        alive = true;
        this.currentRoom = currentRoom;
        previousRoom = null;
        currentWeapon = null;
        backpack = new Backpack(backpackSize);
    }

    /**
     * This method is used to get the name of the player object.
     * @return player's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method is used to get player's current health
     * @return player's current health.
     */
    public int getHealth()
    {
        return currentHealth;
    }

    /**
     * This method is used to get player's backpack.
     * @return the backpack of this player.
     */
    public Backpack getBackpack()
    {
        return backpack;
    }

    /**
     * This method is used to get player's maximum health
     * @return player maximum health.
     */
    public int getMaxHealth()
    {
        return maxHealth;
    }

    /**
     * This method checks if the player is alive.
     * @return true if alive, false if dead
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * This method sets the player's status dead.
     */
    public void setDead()
    {
        alive = false;
    }

    /**
     * This method is used to increment player's health.
     *
     * @param amount Amount by which to increment player's health.
     */
    public void incrementHealth(int amount)
    {
        if((currentHealth + amount) <= 100)
        {
            currentHealth += amount;
        }
        else
        {
            currentHealth = maxHealth;
        }
    }

    /**
     * This method is used to decrement player's health.
     *
     * @param amount Amount by which to decrement player's health.
     */
    public void reduceHealth(int amount)
    {
        if((currentHealth - amount) > 0)
        {
            currentHealth -= amount;
        }
        else
        {
            setDead();
        }
    }

    /**
     * This method is used to get player's health details.
     * @return player's current health / player's maximum health.
     */
    public String getHealthStats()
    {
        return currentHealth + "/" + maxHealth;
    }

    /**
     * This method is used to change the room a player currently is in.
     *
     * @param newRoom A new Room the player is entering.
     */
    public void setCurrentRoom(Room newRoom)
    {
        currentRoom = newRoom;
    }

    /**
     * This method is used to change player's previous room.
     *
     * @param newRoom A Room the player has left.
     */
    public void setPreviousRoom(Room newRoom)
    {
        previousRoom = newRoom;
    }

    /**
     * This method is used to set the player's current weapon to the new one.
     *
     * @param newWeapon A Weapon the player has just equiped.
     */
    public void setCurrentWeapon(Weapon newWeapon)
    {
        currentWeapon = newWeapon;
    }

    /**
     * This method is used to get player's current weapon.
     * @return player's current weapon.
     */
    public Weapon getCurrentWeapon()
    {
        return currentWeapon;
    }

    /**
     * This method is used to get player's current room.
     * @return the room player currently is in.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * This method is used to get player's previous room.
     * @return the room player was in before the current room.
     */
    public Room getPreviousRoom()
    {
        return previousRoom;
    }
    //******* PRIVATE METHODS *******

    /**
     * This method is used to pick a random name according to the gender.
     * @return randomly generated name.
     */
    private String setName(String gender)
    {
        if(gender.toLowerCase().equals("male")) return randomMaleName();
        else if(gender.toLowerCase().equals("female")) return randomFemaleName();
        return null;
    }

    /**
     * This method is used to pick a random male name from the array.
     * @return randomly chosen male name.
     */
    private String randomMaleName()
    {
        Random rand = new Random();
        String names[] = {"John Zena", "Captain Africa", "Bill Doors", "Elon Mist", "Jackie Jack"};
        return names[rand.nextInt(names.length)];
    }

    /**
     * This method is used to pick a random female name from the array.
     * @return randomly chosen female name.
     */
    private String randomFemaleName()
    {
        Random rand = new Random();
        String names[] = {"Marilyn Momrow", "Super lady", "Marie Curry", "Hillmary Cliffton", "Angelina Jokelee"};
        return names[rand.nextInt(names.length)];
    }
}