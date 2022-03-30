/**
        * This class is part of the "Peacemaker" application.
        * "Peacemaker" is a simple, text based adventure game.
        *
        * This class holds an enumeration of all command words known to the game.
        * It is used to recognise commands as they are typed in.
        *
        * @author  Michael Kölling, David J. Barnes and Vakaris Paulavičius (Student number: 20062023).
        * @version 2020.11.26
        */
public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
            "go", "quit", "help", "hit", "eat", "look", "take", "drop",
            "info", "list", "stats", "equip", "about", "show"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word.
     * @param aString first word from the terminal's input.
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * This method is used to get all valid commands.
     * @return all valid commands in a string.
     */
    public String getAll()
    {
        String str = "";
        for(String command: validCommands) {
            str += (command + "  ");
        }
        return str;
    }
}
