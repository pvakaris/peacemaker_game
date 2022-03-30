/**
 * Class Trophy - a trophy in the game.
 *
 * This class is part of "The peacemaker" application. 
 * "The peacemaker" is a simple, text based adventure game. 
 *
 * A "Trophy" represents one single trophy item in the game that a particular mob can drop.
 * All mobs can drop different trophies which, most of the time, are just souvenirs.
 *
 * However, certain trophies are relevant to the game story and a player
 * must have them to win the game.
 *
 * @author Vakaris Paulavičius (Student number: 20062023)
 * @version 2020.11.26
 */
public class Trophy
{
    private String description;
    private String name;
    private int size;
    private boolean mainTrophy;

    /**
     * Create a new trophy.
     *
     * @param name Name of the trophy.
     * @param size How much space units this trophy occupies.
     */
    public Trophy(String name, int size)
    {
        this.name = name;
        this.size = size;
        setDescription();
    }

    /**
     * This method is used to get the name of the trophy.
     * @return trophy name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method is used to get the size of this trophy.
     * @return how much space units it covers.
     */
    public int getSize()
    {
        return size;
    }

    /**
     * This method is used to get the description of this trophy.
     * @return trophy description
     */
    public String getDescription()
    {
        return description;
    }

    // ***** PRIVATE METHODS *****

    /**
     * This method is used to set a new description for this trophy.
     */
    private void setDescription()
    {
        String line1 = ("Name: " + name),
                line2 = ("Size in backpack: " + size);

        description = line1 + "\n " + line2;
    }
}