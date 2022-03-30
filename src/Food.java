/**
 * Class Food - a food product in the game.
 *
 * This class is part of "The peacemaker" application.
 * "The peacemaker" is a simple, text based adventure game.
 *
 * A "Food" represents one single food item in the game that can be used
 * to restore player's health. Game characters can have, eat and pickup
 * food products from different locations.
 *
 * @author Vakaris Paulavičius (Student number: 20062023)
 * @version 2020.11.26
 */
public class Food
{
    private int size;
    private int value;
    private String name;
    private String description; //Description of this food product

    /**
     * Create a new food object.
     *
     * @param size How much space units this food product covers.
     * @param name Name of this food product.
     * @param value How much health it restores.
     */
    public Food(int size, String name, int value)
    {
        this.size = size;
        this.name = name;
        this.value = value;
        setDescription();
    }

    /**
     * This method is used to get the size of this food product.
     * @return how much space units it covers.
     */
    public int getSize()
    {
        return size;
    }

    /**
     * This method is used to get the name of this food.
     * @return food product name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method is used to get the amount of health this food product restores.
     * @return how much health this object restores
     */
    public int getValue()
    {
        return value;
    }

    /**
     * This method is used to get the description of the food object
     * @return food product description
     */
    public String getDescription()
    {
        return description;
    }

    // ***** PRIVATE METHODS *****

    /**
     * This method is used to set a new description for the food object
     */
    private void setDescription()
    {
        String line1 = ("Name: " + name),
                line2 = ("Size in backpack: " + size),
                line3 = ("Restores health: " + value);

        description = line1 + "\n " + line2 + "\n " + line3;
    }
}
