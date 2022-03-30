import java.util.Scanner;
/**
 * Class Parser - user's input analyser in the game.
 *
 * This class is part of "The peacemaker" application. 
 * "The peacemaker" is a simple, text based adventure game. 
 *
 * A "Parser" has a purpose of reading the player's input from the terminal,
 * and determing what are the further actions.
 *
 * @author Michael Kölling, David J. Barnes and Vakaris Paulavičius (Student number: 20062023).
 * @version 2020.11.26
 */
public class Parser
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser()
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * This method is used to get the next command from the user.
     * @return The next command from the user.
     */
    public Command getCommand()
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;
        String word3 = null;

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext())
        {
            word1 = tokenizer.next();      // get first word
            if(tokenizer.hasNext())
            {
                word2 = tokenizer.next();      // get second word
                if(tokenizer.hasNext())
                {
                    word3 = tokenizer.next();      //get third word
                }
            }
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(commands.isCommand(word1)) {
            return new Command(word1, word2, word3);
        }
        else {
            return new Command(null, word2, word3);
        }
    }

    /**
     * This method is used to get all valid commands.
     * @return a string with all command that are valid.
     */
    public String getCommands()
    {
        return commands.getAll();
    }
}