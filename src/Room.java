import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
/**
 * Class Room - a room in the game.
 *
 * This class is part of "The peacemaker" application. 
 * "The peacemaker" is a simple, text based adventure game. 
 *
 * A "Room" represents one single room in the game. Many rooms have their own mobs,
 * items and weapons. 
 *
 * @author Michael Kölling, David J. Barnes and Vakaris Paulavičius (Student number: 20062023).
 * @version 2020.11.26
 */

public class Room
{
    private String description;

    private HashMap<String, Room> exits;        // stores exits of this room.

    private HashMap<Food, Integer> foodList;        //stores food that is in this room
    private ArrayList<Mob> mobs;         //stores mobs that are in this room
    private HashMap<Weapon, Integer> weapons;            //stores weapons that are in this room
    private HashMap<Trophy, Integer> trophies;          //stores trophies that are in this room

    /**
     * Create a room.
     *
     * @param description Room's short description.
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        foodList = new HashMap<>();
        mobs = new ArrayList<>();
        weapons = new HashMap<>();
        trophies = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     *
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * This method is used to get room's short description.
     * @return short description of the room. 
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * This method is used to get a long description of the room in the form:
     *     You are in the kitchen.
     *     You can go: north west
     * @return a long description of this room
     */
    public String getLongDescription()
    {
        return description + "\n" + getExitString();
    }

    /**
     * This method is used to get a string describing the room's exits, for example
     * "You can go: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = " You can go:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * This method is used to get the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     *
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    /**
     * This method is used to add food objects to the room.
     *
     * @param food Food object to add.
     * @param quantity How many food objects of this kind to add.
     */
    public void addFood(Food food, int quantity)
    {
        if(foodList.containsKey(food))
        {
            int amount = foodList.get(food);
            foodList.put(food, amount+quantity);
        }
        else
        {
            foodList.put(food, quantity);
        }
    }

    /**
     * This method is used to add weapons to the room.
     *
     * @param weapon Weapon object to add.
     * @param quantity How many weapons of this kind to add.
     */
    public void addWeapons(Weapon weapon, int quantity)
    {
        if(weapons.containsKey(weapon))
        {
            int amount = weapons.get(weapon);
            weapons.put(weapon, amount+quantity);
        }
        else
        {
            weapons.put(weapon, quantity);
        }
    }

    /**
     * This method is used to add mobs to the room.
     *
     * @param mob Mob object to add.
     */
    public void addMob(Mob mob)
    {
        mobs.add(mob);
    }

    /**
     * This method is used to add trophies to the room.
     *
     * @param trophy Trophy object to add.
     */
    public void addTrophies(Trophy trophy)
    {
        if(trophies.containsKey(trophy))
        {
            int amount = trophies.get(trophy);
            trophies.put(trophy, amount+1);
        }
        else
        {
            trophies.put(trophy, 1);
        }
    }

    /**
     * This method checks if there are any mobs in this room.
     * @return true if there are, false if there are not.
     */
    public boolean hasMobs()
    {
        if(mobs.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This method is used to remove food objects from the room.
     *
     * @param item Food object to remove.
     */
    public void removeFood(Food item)
    {
        if(foodList.get(item) == 1)
        {
            foodList.remove(item);
        }
        else
        {
            int quantity = foodList.get(item);
            foodList.put(item, quantity -1);
        }
    }

    /**
     * This method is used to remove trophies from the room.
     *
     * @param item Trophy object to remove.
     */
    public void removeTrophy(Trophy item)
    {
        if(trophies.get(item) == 1)
        {
            trophies.remove(item);
        }
        else
        {
            int quantity = trophies.get(item);
            trophies.put(item, quantity -1);
        }
    }

    /**
     * This method is used to remove weapons from the room.
     *
     * @param item Weapon object to remove.
     */
    public void removeWeapon(Weapon item)
    {
        if(weapons.get(item) == 1)
        {
            weapons.remove(item);
        }
        else
        {
            int quantity = weapons.get(item);
            weapons.put(item, quantity -1);
        }
    }

    /**
     * This method is used to remove mobs from the room.
     *
     * @param mob Mob to remove.
     */
    public void removeMob(Mob mob)
    {
        mobs.remove(mob);
    }

    /**
     * This method is used to get a food object from this room using its name.
     *
     * @param name Food object's name.
     * @return food object if it is in this room, null if the object was not found.
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
     * This method is used to get a mob from this room using its name.
     *
     * @param name Mob's name.
     * @return mob object if it is in this room, null if the object was not found.
     */
    public Mob getMobByName(String name)
    {
        for(Mob mob : mobs)
        {
            if(mob.getName().toLowerCase().equals(name)) return mob;
        }
        return null;
    }

    /**
     * This method is used to get a weapon from this room using its name.
     *
     * @param name Weapon's name.
     * @return weapon object if it is in this room, null if the object was not found.
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
     * This method is used to get a trophy from this room using its name.
     *
     * @param name Trophy's name.
     * @return trophy object if it is in this room, null if the object was not found.
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
     * This method is used to get all the mobs that are in this room.
     * @return a string with all the mobs that are in the room.
     */
    public String listMobs()
    {
        if(mobs.isEmpty())
        {
            return " There are no mobs in this place. We are safe, for now...";
        }
        else
        {
            String str = " " + randomNegativeResponse();
            //We put all the mobs into the hashmap to count their number using their names.
            HashMap<String, Integer> mobCount = new HashMap<>();
            mobCount = countMobs(mobs);
            int index = 0;
            for(String key: mobCount.keySet())
            {
                if(mobs.size() == 1 && mobCount.get(key) == 1)
                {
                    str += " " + (mobCount.get(key) + " " + key + " is coming your way!");
                }
                else
                {
                    if(index == (mobCount.size()-1))
                    {
                        if(mobCount.get(key) == 1)
                        {
                            str += " " + (mobCount.get(key) + " " + key + " are coming your way!");
                        }
                        else
                        {
                            str += " " + (mobCount.get(key) + " " + key + "s are coming your way!");
                        }
                    }
                    else
                    {
                        if(mobCount.get(key) == 1)
                        {
                            str += " " + (mobCount.get(key) + " " + key + ",");
                        }
                        else
                        {
                            str += " " + (mobCount.get(key) + " " + key + "s,");
                        }
                    }
                }
                index ++;
            }
            return str;
        }
    }

    /**
     * This method is used to get all the food items that are in this room.
     * @return a string with all the food products that are in the room.
     */
    public String listFood()
    {
        if(foodList.isEmpty())
        {
            return " There is no food in this place!";
        }
        else
        {
            String str = " " + randomPositiveResponse();

            int index = 0;
            for(Food key: foodList.keySet())
            {
                if(foodList.size() == 1 && foodList.get(key) == 1)
                {
                    str += " There is "+ (foodList.get(key) + " " + key.getName() + " on the tree stump.");
                }
                else
                {
                    if(index == (foodList.size()-1))
                    {
                        if(foodList.get(key) == 1)
                        {
                            str += " " + (foodList.get(key) + " " + key.getName() + " are on the tree stump.");
                        }
                        else
                        {
                            str += " " + (foodList.get(key) + " " + key.getName() + "s are on the tree stump.");
                        }
                    }
                    else
                    {
                        if(foodList.get(key) == 1)
                        {
                            str += " " + (foodList.get(key) + " " + key.getName() + ",");
                        }
                        else
                        {
                            str += " " + (foodList.get(key) + " " + key.getName() + "s,");
                        }
                    }
                }
                index ++;
            }
            return str;
        }
    }

    /**
     * This method is used to get all the weapons that are in this room.
     * @return a string with all the weapons that are in the room.
     */
    public String listWeapons()
    {
        if(weapons.isEmpty())
        {
            return " No weapons out here!";
        }
        else
        {
            String str = " " + randomPositiveResponse();

            int index = 0;
            for(Weapon key: weapons.keySet())
            {
                if(weapons.size() == 1 && weapons.get(key) == 1)
                {
                    str += " There is "+ (weapons.get(key) + " " + key.getName() + " laying on the ground.");
                }
                else
                {
                    if(index == (weapons.size()-1))
                    {
                        if(weapons.get(key) == 1)
                        {
                            str += " " + (weapons.get(key) + " " + key.getName() + " are laying on the ground.");
                        }
                        else
                        {
                            str += " " + (weapons.get(key) + " " + key.getName() + "s are laying on the ground.");
                        }
                    }
                    else
                    {
                        if(weapons.get(key) == 1)
                        {
                            str += " " + (weapons.get(key) + " " + key.getName() + ",");
                        }
                        else
                        {
                            str += " " + (weapons.get(key) + " " + key.getName() + "s,");
                        }
                    }
                }
                index ++;
            }
            return str;
        }
    }

    /**
     * This method is used to get all the trophies that are in this room.
     * @return a string with all the trophies that are in the room.
     */
    public String listTrophies()
    {
        if(trophies.isEmpty())
        {
            return " No trophies have been found in this location.";
        }
        else
        {
            String str = " " + randomPositiveResponse();

            int index = 0;
            for(Trophy key: trophies.keySet())
            {
                if(trophies.size() == 1 && trophies.get(key) == 1)
                {
                    str += " There is "+ (trophies.get(key) + " " + key.getName() + " levitating in the air.");
                }
                else
                {
                    if(index == (trophies.size()-1))
                    {
                        if(trophies.get(key) == 1)
                        {
                            str += " " + (trophies.get(key) + " " + key.getName() + " are levitating in the air.");
                        }
                        else
                        {
                            str += " " + (trophies.get(key) + " " + key.getName() + "s are levitating in the air.");
                        }
                    }
                    else
                    {
                        if(trophies.get(key) == 1)
                        {
                            str += " " + (trophies.get(key) + " " + key.getName() + ",");
                        }
                        else
                        {
                            str += " " + (trophies.get(key) + " " + key.getName() + "s,");
                        }
                    }
                }
                index ++;
            }
            return str;
        }
    }

    // ***** PRIVATE METHODS *****

    /**
     * This method is used to count the number of mobs. It takes an array list as a parameter
     * and puts all the objects into the HasMap with their quantity as values.
     *
     * @param mobList A list of mobs that exist in that room
     * @return a HashMap with mob names and their quantity in this room. <String, Integer>.
     */
    private HashMap countMobs(ArrayList<Mob> mobList)
    {
        HashMap<String,Integer> results = new HashMap<>();
        for(Mob mob : mobList)
        {
            if(results.containsKey(mob.getName()))
            {
                int quantity = results.get(mob.getName());    //If another mob of the same name is found, the quantity is incremented by one.
                results.put(mob.getName(), quantity+1);
            }
            else
            {
                results.put(mob.getName(), 1);
            }
        }
        return results;
    }

    /**
     * This method is used to randomly select a positive response.
     * @return a positive response.
     */
    private String randomPositiveResponse()
    {
        Random rand = new Random();
        String choices[] = {"Superb!", "Fastastic!", "I think we can call it a day.", "Is it Chistmas already?", "Look!", "Oh yes!",
                "Today is one lucky day for us, captain!", "I can't believe my digital eyes!"};
        return choices[rand.nextInt(choices.length)];
    }

    /**
     * This method is used to randomly select a negative response.
     * @return a negative response.
     */
    private String randomNegativeResponse()
    {
        Random rand = new Random();
        String choices[] = {"Watch out!", "We've got a problem, captain!", "Oh no!", "Be careful!", "Take a weapon!", "Take cover"};
        return choices[rand.nextInt(choices.length)];
    }
}