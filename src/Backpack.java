import java.util.HashMap;
/**
 * Class Backpack - a player's backpack in the game.
 *
 * This class is part of "The peacemaker" application.
 * "The peacemaker" is a simple, text based adventure game.
 *
 * A "Backpack" represents one single backpack where player's trophies,
 * weapons and food are stored. It has limited space. and is made out of
 * three HashMaps: for food, for weapons and for trophies.
 *
 * @author Vakaris Paulavičius (Student number: 20062023).
 * @version 2020.11.26
 */
public class Backpack
{
    private HashMap<Food, Integer> foodList;        //stores food that is in this backpack
    private HashMap<Weapon, Integer> weapons;            //stores weapons that are in this backpack
    private HashMap<Trophy, Integer> trophies;          //stores trophies that are in this backpack

    private int maxBackpackSize;
    private int currentBackpackSize;

    /**
     * Create a backpack.
     *
     * @param maxSize Maximum size of the backpack
     */
    public Backpack(int maxSize)
    {
        foodList = new HashMap<>();
        weapons = new HashMap<>();
        trophies = new HashMap<>();

        maxBackpackSize = maxSize;
        currentBackpackSize = 0;
    }

    /**
     * This method is used to get the current backpack size.
     * @return backpack's current size
     */
    public int getCurrentBackpackSize()
    {
        return currentBackpackSize;
    }

    /**
     * This method is used to get the maximum backpack size.
     * @return backpack's maximum size
     */
    public int getMaxBackpackSize()
    {
        return maxBackpackSize;
    }

    /**
     * This method is used to add a weapon to the backpack.
     *
     * @param item Weapon to be added.
     * @return true, if the weapon can be added, false if it cannot be added.
     */
    public boolean addWeapon(Weapon item)
    {
        if(inWeapons(item))
        {
            return false;  //A player can't have more than one weapon of the same kind
        }
        else
        {
            if(isSpaceInBackpack(item.getSize()))
            {
                weapons.put(item, 1);
                currentBackpackSize += item.getSize();
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * This method is used to add a food object to the backpack.
     *
     * @param item Food product to be added.
     * @return true, if the food product can be added, false if it cannot be added.
     */
    public boolean addFood(Food item)
    {
        if(inFoodList(item))
        {
            if(isSpaceInBackpack(item.getSize()))
            {
                int quantity = foodList.get(item);
                foodList.put(item, quantity +1);
                currentBackpackSize += item.getSize();
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if(isSpaceInBackpack(item.getSize()))
            {
                foodList.put(item, 1);
                currentBackpackSize += item.getSize();
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * This method is used to add a trophy to the backpack.
     *
     * @param item Trophy to be added.
     * @return true, if the trophy can be added, false if cannot it be added.
     */
    public boolean addTrophy(Trophy item)
    {
        if(inTrophies(item))
        {
            if(isSpaceInBackpack(item.getSize()))
            {
                int quantity = foodList.get(item);
                trophies.put(item, quantity +1);
                currentBackpackSize += item.getSize();
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if(isSpaceInBackpack(item.getSize()))
            {
                trophies.put(item, 1);
                currentBackpackSize += item.getSize();
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * This method is used to remove a trophy from the backpack.
     *
     * @param item Trophy to be removed.
     * @return true, if the trophy is in the backpack, false if it is not.
     */
    public boolean removeTrophy(Trophy item)
    {
        if(inTrophies(item))
        {
            if(trophies.get(item) == 1)
            {
                trophies.remove(item);
                currentBackpackSize -= item.getSize();
            }
            else
            {
                int quantity = trophies.get(item);
                trophies.put(item, quantity -1);
                currentBackpackSize -= item.getSize();
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This method is used to remove a food object from the backpack.
     *
     * @param item Food object to be removed.
     * @return true, if the food object is in the backpack, false if it is not.
     */
    public boolean removeFood(Food item)
    {
        if(inFoodList(item))
        {
            if(foodList.get(item) == 1)
            {
                foodList.remove(item);
                currentBackpackSize -= item.getSize();
            }
            else
            {
                int quantity = foodList.get(item);
                foodList.put(item, quantity -1);
                currentBackpackSize -= item.getSize();
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This method is used to remove a weapon from the backpack.
     *
     * @param item Weapon to be removed.
     * @return true, if the weapon is in the backpack, false if it is not.
     */
    public boolean removeWeapon(Weapon item)
    {
        if(inWeapons(item))
        {
            if(weapons.get(item) == 1)
            {
                weapons.remove(item);
                currentBackpackSize -= item.getSize();
            }
            else
            {
                int quantity = weapons.get(item);
                weapons.put(item, quantity -1);
                currentBackpackSize -= item.getSize();
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This method is used to get a food object from the backpack using its name.
     *
     * @param name Food object's name.
     * @return food object if it is in the backpack, null if the object was not found.
     */
    public Food getFoodByName(String name)
    {
        for(Food key : foodList.keySet())
        {
            if(key.getName().toLowerCase().equals(name)) return key;
        }
        return null;
    }

    /**
     * This method is used to get a weapon object from the backpack using its name.
     *
     * @param name Weapon's name.
     * @return weapon object if it is in the backpack, null if the object was not found.
     */
    public Weapon getWeaponByName(String name)
    {
        for(Weapon key : weapons.keySet())
        {
            if(key.getName().toLowerCase().equals(name)) return key;
        }
        return null;
    }

    /**
     * This method is used to get a trophy object from the backpack using its name.
     *
     * @param name Trophy's name
     * @return trophy object if it is in the backpack, null if the object was not found.
     */
    public Trophy getTrophyByName(String name)
    {
        for(Trophy key : trophies.keySet())
        {
            if(key.getName().toLowerCase().equals(name)) return key;
        }
        return null;
    }

    /**
     * This method is used to get all the trophies that are in the backpack.
     * @return a string with all the trophies that are in the backpack.
     */
    public String listTrophies()
    {
        if(trophies.isEmpty())
        {
            return "None.";
        }
        else
        {
            int index = 0;
            String str = "";
            for(Trophy key: trophies.keySet())
            {
                if(index == (trophies.size()-1))
                {
                    if(trophies.get(key) == 1)
                    {
                        str += (trophies.get(key) + " " + key.getName() + ".");
                    }
                    else
                    {
                        str += (trophies.get(key) + " " + key.getName() + "s.");
                    }
                }
                else
                {
                    if(trophies.get(key) == 1)
                    {
                        str += (trophies.get(key) + " " + key.getName() + ", ");
                    }
                    else
                    {
                        str += (trophies.get(key) + " " + key.getName() + "s, ");
                    }
                }
                index ++;
            }
            return str;
        }
    }

    /**
     * This method is used to get all the food products that are in the backpack.
     * @return a string with all the food products that are in the backpack.
     */
    public String listFood()
    {
        if(foodList.isEmpty())
        {
            return "None.";
        }
        else
        {
            int index = 0;
            String str = "";
            for(Food key: foodList.keySet())
            {
                if(index == (foodList.size()-1))
                {
                    if(foodList.get(key) == 1)
                    {
                        str += (foodList.get(key) + " " + key.getName() + ".");
                    }
                    else
                    {
                        str += (foodList.get(key) + " " + key.getName() + "s.");
                    }
                }
                else
                {
                    if(foodList.get(key) == 1)
                    {
                        str += (foodList.get(key) + " " + key.getName() + ", ");
                    }
                    else
                    {
                        str += (foodList.get(key) + " " + key.getName() + "s, ");
                    }
                }
                index ++;
            }
            return str;
        }
    }

    /**
     * This method is used to get all the weapons that are in the backpack.
     * @return a string with all the weapons that are in the backpack.
     */
    public String listWeapons()
    {
        if(weapons.isEmpty())
        {
            return "None.";
        }
        else
        {
            int index = 0;
            String str = "";
            for(Weapon key: weapons.keySet())
            {
                if(index == (weapons.size()-1))
                {
                    if(weapons.get(key) == 1)
                    {
                        str += (key.getName() + ".");
                    }
                    else
                    {
                        str += (key.getName() + "s.");
                    }
                }
                else
                {
                    if(weapons.get(key) == 1)
                    {
                        str += (key.getName() + ", ");
                    }
                    else
                    {
                        str += (key.getName() + "s, ");
                    }
                }
                index ++;
            }
            return str;
        }
    }

    /**
     * This method is used to check if the main trophy is in the backpack.
     * Used to determine whether the player has won.
     * @return true if the main trophy is in the player's backpack, false if it is not.
     */
    public boolean hasMainTrophy(Trophy trophy)
    {
        if(trophies.containsKey(trophy)) return true;
        else return false;
    }

    /**
     * This method is used to get backpack details.
     * @return current backpack size / maximum backpack size
     */
    public String getBackpackStats()
    {
        return getCurrentBackpackSize() + "/" + getMaxBackpackSize();
    }

    //******* PRIVATE METHODS *******

    /**
     * Checks whether the weapon is already in the backpack.
     *
     * @param item Weapon object.
     * @return true if it is, false otherwise.
     */
    private boolean inWeapons(Weapon item)
    {
        return weapons.containsKey(item);
    }

    /**
     * Checks whether the trophy is already in the backpack.
     *
     * @param item Trophy object.
     * @return true if it is, false otherwise.
     */
    private boolean inTrophies(Trophy item)
    {
        return trophies.containsKey(item);
    }

    /**
     * Checks whether the food object is already in the backpack.
     *
     * @param item Food object.
     * @return true if it is, false otherwise.
     */
    private boolean inFoodList(Food item)
    {
        return foodList.containsKey(item);
    }

    /**
     * Checks if there is enough space in the backpack for a particular item.
     * @param size Item's size.
     */
    private boolean isSpaceInBackpack(int size)
    {
        if((currentBackpackSize + size) <= maxBackpackSize)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
