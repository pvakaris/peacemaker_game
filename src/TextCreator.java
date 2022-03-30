/**
 * Class TextCreator is used to create pseudo interface in the terminal.
 *
 * This class is part of "The peacemaker" application.
 * "The peacemaker" is a simple, text based adventure game.
 *
 * This class is used to print text to the terminal in various patterns,
 * to create a primitive interface for a better user experience.
 * Use of this class also prevents clutter in the game class.
 *
 * However, this class needs a lot of upgrading, because whenever a new command is added,
 * all the patterns must be changed. In addition, if the text line is long enought, it can go out of bounds
 * in the terminal.
 *
 * @author Vakaris Paulavičius (Student number: 20062023)
 * @version 2020.11.26
 */
public class TextCreator
{
    /**
     * Print out the opening message for the player.
     */
    public void printWelcome()
    {
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("*********************           Welcome to the Peacemaker          *********************");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("|             Bring the village peace by defeating the brutal Captain Toad             |");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println();
    }

    /**
     * Print out the closing message for the player.
     */
    public void printGoodbye()
    {
        System.out.println("                       Thanks for your time, soldier. See you soon!                     ");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Print top box.
     */
    public void printTopBox()
    {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("|                   Type 'help' if you want to know available commands                 |");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("|                                                                                      |");
    }

    /**
     * Print bottom box.
     */
    public void printBottomBox()
    {
        System.out.println("|                                                                                      |");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Print details about the current room.
     *
     * @param mobs A string with mobs that are in that room.
     * @param food A string with food products that are in that room.
     * @param weapons A string with weapons that are in the room.
     * @param trophies A string with trophies that are in this. room.
     */
    public void printCurrentRoomDetails(String mobs, String food, String weapons, String trophies)
    {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println(" M: " + mobs);
        System.out.println(" //");
        System.out.println(" F: " + food);
        System.out.println(" //");
        System.out.println(" W: " + weapons);
        System.out.println(" //");
        System.out.println(" T: " + trophies);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println();
    }

    /**
     * Print game win message.
     */
    public void winMessage(String string)
    {
        System.out.println();
        System.out.println("                       *** " + string + " has won! The village celebrates.  ***");
        System.out.println();
    }

    /**
     * Print player creation text.
     */
    public void createCharacterText()
    {
        System.out.println("               Now it is time to create your own character. Type 'female'  ");
        System.out.println("                    or 'male', to choose your character's gender.          ");
        System.out.println("                                       OR                                  ");
        System.out.println("                         Type 'quit' to exit this game now.                ");
        System.out.println();
    }

    /**
     * Print text in a pattern specifically designed for the terminal interface.
     */
    public void classicPattern(String string)
    {
        System.out.println(" " + string);
        System.out.println();
    }

    /**
     * Print a single line. This method is just for consistency.
     */
    public void line(String string)
    {
        System.out.println(string);
    }

    /**
     * Print items that are currently in the player's backpack.
     *
     * @param food A string with food products that are in player's backpack.
     * @param weapons A string with weapons that are in the backpack.
     * @param trophies A string with trophies that are in the backpack.
     */
    public void backpackItems(String food, String weapons, String trophies)
    {
        System.out.println(" Your items: ");
        System.out.println("   Food: "+ food);
        System.out.println("   Weapons: "+ weapons);
        System.out.println("   Trophies: "+ trophies);
        System.out.println();
    }

    /**
     * Print text with character's name after user creates it.
     *
     * @param name Character's name.
     */
    public void characterText(String name)
    {
        System.out.println();
        System.out.println("                  Your character '" + name + "' was successfully created!");
    }

    /**
     * Print text after completing the game, but without the main trophy.
     */
    public void noMainTrophyMessage()
    {
        System.out.println();
        System.out.println("               You lost, because you got out of the dungeon without a chest.");
        System.out.println("                      Now the people of your village will starve.");
    }

    /**
     * Print text when player enters the old man's cabin.
     */
    public void taskMessage()
    {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println(" The old man: ");
        System.out.println();
        System.out.println("* Hello warrior, nice to finally meet you. I am happy to hear that there still are");
        System.out.println("  brave people who are not afraid to fight for good. Captain Toad has been terrorizing");
        System.out.println("  our peaceful village for over a decade now.");
        System.out.println();
        System.out.println("* Your task is to travel to the North island and defeat him. He usually hides in his");
        System.out.println("  dungeon.");
        System.out.println();
        System.out.println("* In this cabin, you can find some food and weapons. However, to defeat this fiend,");
        System.out.println("  you'll have to find higher-level weapons and better food.");
        System.out.println();
        System.out.println("* I suggest visiting the East and the West islands. There you might find something,");
        System.out.println("  but be careful, these islands are full of dangerous creatures.");
        System.out.println();
        System.out.println("* One final thing, after you defeat the captain, which I am sure you will, take the");
        System.out.println("  chest with you before leaving. That money belongs to the village people.");
        System.out.println("^ I wish you all the best!");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println();
    }

    /**
     * Print fight text in a specific pattern.
     *
     * @param playerDamage The amount of damage player's weapon does in one hit.
     * @param playerStats Player's health details.
     * @param mobHealth Health of the mob after player's hit.
     * @param mobDamage The amount of damage a mob does in one hit.
     * @param mobName Mob's name.
     * @param isAlive Current mob status: alive/dead.
     */
    public void fightText(int playerDamage, String playerStats, int mobHealth, int mobDamage, String mobName, boolean mobAlive)
    {
        System.out.println(" ## ## ## ## ## ## ## ## ## ##");
        System.out.println(" You did " + playerDamage + " damage to the " + mobName + ".");
        System.out.println(" *    *    *");
        System.out.println(" " + mobName + " did " + mobDamage + " damage to you.");
        System.out.println(" //    //    //    //    //");
        System.out.println(" Your current health is: " + playerStats + ".");
        System.out.println(" *    *    *");
        if(mobAlive)
        {
            System.out.println(" " + mobName + " has " + mobHealth + " health left.");
        }
        else
        {
            System.out.println(" You have slained the " + mobName + ".");
        }
        System.out.println(" ## ## ## ## ## ## ## ## ## ##");
        System.out.println();
    }

    /**
     * Print text if player dies.
     */
    public void deathText()
    {
        System.out.println("                              You died. Better luck next time.");
    }

    /**
     * Print out available commands.
     *
     * @param commands A string with all valid commands.
     */
    public void printHelp(String commands)
    {
        System.out.println("   These are some useful commands that might help:");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.print("|  ");
        System.out.println(commands + " |");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println();
    }
}
