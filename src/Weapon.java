/**
 * Class Weapon - a weapon in the game.
 *
 * This class is part of "The peacemaker" application. 
 * "The peacemaker" is a simple, text based adventure game. 
 *
 * A "Weapon" represents one single weapon in the game that can be used
 * in fights. Game characters can acquire, use and possess weapons. Weapons can
 * also be found in rooms.
 *
 * @author Vakaris Paulavičius (Student number: 20062023)
 * @version 2020.11.26
 */
public class Weapon
{
    private String name;
    private int damage;
    private String rarity;
    private String type;
    private int size;
    private String description;

    private String[] rarityTypes = {"Common", "Rare", "Almost infeasible to find"};  //Should be changed to a class in the future
    private String[] weaponTypes = {"melee", "ranged"};  //Should be changed to a class in the future

    /**
     * Create a new weapon.
     *
     * @param name Weapon's name.
     * @param damage How much damage this weapon does in one hit.
     * @param rarity Weapon's rarity (0 - common, 1 - rare, 2 - ultra rare).
     * @param size Size of the weapon (how much space it covers).
     * @param type Weapon's type (0 - melee, 1 - ranged).
     */
    public Weapon(String name, int damage, int rarity, int size, int type)
    {
        this.name = name;
        this.damage = damage;
        this.rarity = rarityTypes[rarity];
        this.type = weaponTypes[type];
        this.size = size;
        setDescription();
    }

    /**
     * This method returns the size of the weapon.
     * @return how much space the weapon occupies.
     */
    public int getSize()
    {
        return size;
    }

    /**
     * This method returns weapon's rarity.
     * @return weapon's rarity.
     */
    public String getRarity()
    {
        return rarity;
    }

    /**
     * This method is used to get the damage this weapon does.
     * @return weapon damage.
     */
    public int getDamage()
    {
        return damage;
    }

    /**
     * This method is used to get the type of the weapon.
     * @return weapon type.
     */
    public String getType()
    {
        return type;
    }

    /**
     * This method is used to get the name of the weapon.
     * @return weapon name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method is used to get the description of the weapon.
     * @return weapon description.
     */
    public String getDescription()
    {
        return description;
    }

    // ***** PRIVATE METHODS *****

    /**
     * This method is used to set a new description for this object.
     */
    private void setDescription()
    {
        String line1 = ("Name: " + name),
                line2 = ("Damage: " + damage),
                line3 = ("Size in backpack: " + size),
                line4 = ("Rarity: " + rarity),
                line5 = ("Type: " + type);

        description = line1 + "\n " + line2 + "\n " + line3 + "\n " + line4 + "\n " + line5;
    }
}