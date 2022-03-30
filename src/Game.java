import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
/**
 * Class Game is the main class of this application.
 *
 * This class is part of "The peacemaker" application. 
 * "The peacemaker" is a simple, text based adventure game. 
 *
 * A Game object is used to simulate the actual game. It creates a map, 
 * a character and all other objects. It also considers the input of a player
 * and executes all the instructions.
 *
 * @author Michael Kölling, David J. Barnes and Vakaris Paulavičius (Student number: 20062023).
 * @version 2020.11.28
 */

public class Game
{
    private Parser parser;
    private Trophy goal;
    private Map map;
    private Player player;
    private TextCreator text;

    /**
     * Create a game.
     */
    public Game()
    {
        createMap();
        parser = new Parser();
        text = new TextCreator();
    }

    /**
     * This method is used to start the game.
     * @param args An empty string array.
     */
    public static void main(String args[])
    {
        Game game = new Game();
        game.play();
    }

    // ***** PRIVATE METHODS *****

    /**
     *  Main play routine. Creates a player and then loops until the player either wins the game or dies or enters
     *  the command 'quit'.
     */
    private void play()
    {
        text.printWelcome();
        boolean finished = false;

        //Character creation
        if(!createCharacter())
        {
            finished = true;
        }
        else
        {
            text.printTopBox();
            text.classicPattern(player.getCurrentRoom().getLongDescription());
        }

        //Main game loop
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);

            if(player.getCurrentRoom() == map.getTaskRoom() && !map.taskRoomWasVisited())
            {
                text.taskMessage();
                map.visitTaskRoom();
            }

            if(!player.isAlive())
            {
                text.deathText();
                finished = true;
            }

            if(playerWon())
            {
                finished = true;
            }
        }

        text.printBottomBox();
        text.printGoodbye();
    }

    /**
     * This method processes the given command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            text.classicPattern("There is no such command, sir.");
            return false;
        }

        String commandWord = command.getCommandWord();  //I am using switch to prevent a ton of else if's
        switch(commandWord)
        {
            case "help":
                text.printHelp(parser.getCommands());
                break;

            case "go":
                goRoom(command);
                break;

            case "quit":
                wantToQuit = quit(command);
                break;

            case "look":
                text.printCurrentRoomDetails(player.getCurrentRoom().listMobs(), player.getCurrentRoom().listFood(),
                        player.getCurrentRoom().listWeapons(), player.getCurrentRoom().listTrophies());
                break;

            case "info":
                text.classicPattern(player.getCurrentRoom().getLongDescription());
                break;

            case "eat":
                eatFood(command);
                break;

            case "equip":
                equipWeapon(command);
                break;

            case "list":
                text.backpackItems(player.getBackpack().listFood(), player.getBackpack().listWeapons(), player.getBackpack().listTrophies());
                break;

            case "stats":
                text.line(" Backpack: " + player.getBackpack().getBackpackStats());
                text.classicPattern("Health: " + player.getHealthStats());;
                break;

            case "drop":
                dropItem(command);
                break;

            case "take":
                takeItem(command);
                break;

            case "hit":
                hitMob(command);
                break;

            case "show":
                weaponInfo();
                break;

            case "about":
                aboutObject(command);
                break;
        }
        // else command not recognised.
        return wantToQuit;
    }

    /**
     * This method is used whenever user's command is 'go'. It checks if the player can go to his specified direction
     * and performs actions according to the command. Also, prints appropriate text to the terminal.
     *
     * @param command The command user has input.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            text.classicPattern("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        // Check for mobs in that room
        if(player.getCurrentRoom().hasMobs())
        {
            text.classicPattern("You cannot go to another room unless you have killed all the mobs!");
            return;
        }

        if(player.getPreviousRoom() == null && direction.equals("back"))
        {
            text.classicPattern("This is the starting point, sir. You cannot go back!");
        }
        else if(player.getPreviousRoom() == map.getStartRoom() && direction.equals("back"))
        {
            text.classicPattern("You cannot go back once you've started the adventure.");
        }
        else if(player.getPreviousRoom() == player.getCurrentRoom() && direction.equals("back"))
        {
            text.classicPattern("You've just went back.");
        }
        else if(player.getPreviousRoom() != null && direction.equals("back"))
        {
            player.setCurrentRoom(player.getPreviousRoom());
            text.classicPattern(player.getPreviousRoom().getLongDescription());
        }
        else if(nextRoom == null)
        {
            text.classicPattern("There is no such place!");
        }
        else if(nextRoom == map.getTeleportRoom())
        {
            teleportToRoom();
        }
        else {
            player.setPreviousRoom(player.getCurrentRoom());
            player.setCurrentRoom(nextRoom);
            text.classicPattern(player.getCurrentRoom().getLongDescription());
        }
    }

    /**
     * This method is used to get information about the player's current weapon.
     */
    private void weaponInfo()
    {
        if(player.getCurrentWeapon() != null)
        {
            text.classicPattern("Your current weapon is " + player.getCurrentWeapon().getName() + ". It does " + player.getCurrentWeapon().getDamage() + " damage.");
        }
        else
        {
            text.classicPattern("You currently have no weapon in your hand.");
        }
    }

    /**
     * This method is used to choose a random room which teleport the player to
     */
    private void teleportToRoom()
    {
        player.setPreviousRoom(player.getCurrentRoom());
        player.setCurrentRoom(map.teleportToRoom());
        text.classicPattern(player.getCurrentRoom().getLongDescription());
    }

    /**
     * This method is used to consume food item. It checks if the user's specified food is in his backpack.
     * If yes, it is removed from it and player's health is increased.
     *
     * @param command The command user has input.
     */
    private void eatFood(Command command)
    {
        String name = getObjectName(command);  //Gets object's name according to the entered text line

        Food food = player.getBackpack().getFoodByName(name);
        if(food != null)
        {
            player.getBackpack().removeFood(food);
            player.incrementHealth(food.getValue());
            if(player.getHealth() == player.getMaxHealth())
            {
                text.classicPattern("You ate " + food.getName() + ". Now your health is full.");
            }
            else
            {
                text.classicPattern("You ate " + food.getName() + " and incremented your health by " + food.getValue() + ".");
            }
        }
        else
        {
            text.classicPattern("You have either misspelled the food name or there is no such food in your backpack.");
        }
    }

    /**
     * This method is used to equip a weapon. It checks if the user's specified weapon is in his backpack.
     * If yes, new weapon is equiped, if not, an error message is printed out to the terminal.
     *
     * @param command The command user has input.
     */
    private void equipWeapon(Command command)
    {
        String name = getObjectName(command);
        Weapon weapon = player.getBackpack().getWeaponByName(name);
        if(weapon != null)
        {
            player.setCurrentWeapon(weapon);
            text.classicPattern("You have taken the " + weapon.getName() + ".");
        }
        else
        {
            text.classicPattern("You either made a typing mistake or there is no such weapon in your backpack.");
        }
    }

    /**
     * This method is used to get information about an item. It checks whether the item is in the user's
     * backpack or in the current room. If it is found in one of those, it's description is printed out to the terminal.
     * Else, an error message is printed out (player can only get description of items that are either in his backpack or
     * in the current room).
     *
     * @param command The command user has input.
     */
    private void aboutObject(Command command)
    {
        String name = getObjectName(command);

        //Check whether the item is food and is it in this room
        Food rFood = player.getCurrentRoom().getFoodByName(name);
        if(rFood != null)
        {
            text.classicPattern(rFood.getDescription());
            return;
        }
        //Check whether the item is food and is it in the backpack
        Food bFood = player.getBackpack().getFoodByName(name);
        if(bFood != null)
        {
            text.classicPattern(bFood.getDescription());
            return;
        }

        //Check whether the item is a weapon and is it in this room
        Weapon rWeapon = player.getCurrentRoom().getWeaponByName(name);
        if(rWeapon != null)
        {
            text.classicPattern(rWeapon.getDescription());
            return;
        }
        //Check whether the item is a weapon and is it in the backpack
        Weapon bWeapon = player.getBackpack().getWeaponByName(name);
        if(bWeapon != null)
        {
            text.classicPattern(bWeapon.getDescription());
            return;
        }

        //Check whether the item is a trophy and is it in this room
        Trophy rTrophy = player.getCurrentRoom().getTrophyByName(name);
        if(rTrophy != null)
        {
            text.classicPattern(rTrophy.getDescription());
            return;
        }
        //Check whether the item is a trophy and is it in the backpack
        Trophy bTrophy = player.getBackpack().getTrophyByName(name);
        if(bTrophy != null)
        {
            text.classicPattern(bTrophy.getDescription());
            return;
        }

        //Checks whether the object is mob and is it in this room
        Mob mob = player.getCurrentRoom().getMobByName(name);
        if(mob != null)
        {
            text.classicPattern(mob.getDescription());
            return;
        }

        //Prints this string if the specified object was not found in this room and in the backpack
        if(bTrophy == null && rTrophy == null && bWeapon == null && rTrophy == null && rFood == null && bFood == null && mob == null)
        {
            text.classicPattern("There is no such object neither in this room nor in your backpack.");
        }
    }

    /**
     * This method is used to drop an item from the backpack int he current room. Player's input is checked to determine
     * whether the mentioned item is in backpack and what kind of item it is.
     * If it turns out, that the item is in the backpack, then it is removed from the player's backpack and left in the current room,
     * else, an error message is printed out to the terminal.
     *
     * @param command The command user has input.
     */
    private void dropItem(Command command)
    {
        String name = getObjectName(command);
        //Check whether the item is food and is it in the backpack
        Food food = player.getBackpack().getFoodByName(name);
        if(food != null)
        {
            text.classicPattern("You have dropped " + food.getName() + " in this room.");
            player.getBackpack().removeFood(food);
            player.getCurrentRoom().addFood(food, 1);
            return;
        }
        //Check whether the item is a weapon and is it in the backpack
        Weapon weapon = player.getBackpack().getWeaponByName(name);
        if(weapon != null)
        {
            text.classicPattern("You have dropped " + weapon.getName() + " in this room.");
            player.getBackpack().removeWeapon(weapon);
            player.getCurrentRoom().addWeapons(weapon, 1);
            return;
        }
        //Check whether the item is a trophy and is it in the backpack
        Trophy trophy = player.getBackpack().getTrophyByName(name);
        if(trophy != null)
        {
            text.classicPattern("You have dropped " + trophy.getName() + " in this room.");
            player.getBackpack().removeTrophy(trophy);
            player.getCurrentRoom().addTrophies(trophy);
            return;
        }
        //Prints this string if the specified item was not found in this room
        if(trophy == null && weapon == null && food == null)
        {
            text.classicPattern("You either made a typing mistake or there is no such item in your backpack.");
        }
    }

    /**
     * This method is used to pick up an item from the current room. Player's input is checked to determine
     * whether the mentioned item is in that room and what kind of item it is.
     * If it turns out, that the item is in the room, then it is added to player's backpack (if there is enough space) and removed from
     * the current room, else, an error message is printed out to the terminal.
     *
     * @param command The command user has input.
     */
    private void takeItem(Command command)
    {
        //Checks whether the item's name consists of two words or one
        String name = getObjectName(command);
        //Check whether the item is food and is it in this room
        Food food = player.getCurrentRoom().getFoodByName(name);
        if(food != null)
        {
            if(player.getBackpack().addFood(food))
            {
                text.classicPattern("You have taken " + food.getName() + " from this room.");
                player.getCurrentRoom().removeFood(food);
            }
            else
            {
                text.classicPattern("There is not enough space in your backpack.");
            }
            return;
        }
        //Check whether the item is food and is it in this room
        Weapon weapon = player.getCurrentRoom().getWeaponByName(name);
        if(weapon != null)
        {
            if(player.getBackpack().addWeapon(weapon))
            {
                text.classicPattern("You have taken " + weapon.getName() + " from this room.");
                player.getCurrentRoom().removeWeapon(weapon);
            }
            else
            {
                text.classicPattern("You cannot have two weapons of the same kind.");
            }
            return;
        }
        //Check whether the item is food and is it in this room
        Trophy trophy = player.getCurrentRoom().getTrophyByName(name);
        if(trophy != null)
        {
            if(player.getBackpack().addTrophy(trophy))
            {
                text.classicPattern("You have taken " + trophy.getName() + " from this room.");
                player.getCurrentRoom().removeTrophy(trophy);
            }
            else
            {
                text.classicPattern("There is not enough space in your backpack.");
            }
            return;
        }
        //Prints this string if the specified item was not found in this room
        if(trophy == null && weapon == null && food == null)
        {
            text.classicPattern("You either made a typing mistake or there is no such item in this room.");
        }
    }

    /**
     * This method is used to to simulate a fight whenever user types a command 'hit + (mob name)'.
     * If there is a mob in the current room, that the user specified in his command, appropriate actions are performed and
     * the right text is printed out to the terminal.
     *
     * @param command The command user has input.
     */
    private void hitMob(Command command)
    {
        if(player.getCurrentRoom().hasMobs())
        {
            String name = getObjectName(command);
            Mob mob = player.getCurrentRoom().getMobByName(name);
            if(mob != null)
            {
                int damage = mob.getDamage();
                if(player.getCurrentWeapon() == null)
                {
                    noWeaponCase(mob, damage);
                    return;
                }
                else
                {
                    player.reduceHealth(damage);
                    mob.reduceHealth(player.getCurrentWeapon().getDamage());
                }

                if(!player.isAlive()) return;
                else
                {
                    text.fightText(player.getCurrentWeapon().getDamage(), player.getHealthStats(), mob.getHealth(), damage, mob.getName(), mob.isAlive());
                }

                if(!mob.isAlive())
                {
                    player.getCurrentRoom().removeMob(mob);
                    if(mob.dropsTrophy())
                    {
                        player.getCurrentRoom().addTrophies(mob.getTrophy());
                        text.classicPattern("Huuuraaayyy!! " + mob.getName() + " has dropped the " + mob.getTrophy().getName() + ".");
                    }
                }
            }
            else
            {
                text.classicPattern("There is no such mob in this place!");
            }
        }
        else
        {
            text.classicPattern("There are no mobs in this place... Are you seeing ghosts already?");
        }
    }

    /**
     * This method is used to generate a fight text if a player does not have a weapon in his hand.
     *
     * @param mob A mob the player is fighting.
     * @param damage An amount of damage this mob does.
     */
    private void noWeaponCase(Mob mob, int damage)
    {
        Random rand = new Random();
        String cheek;
        if(rand.nextInt(2) == 0)
        {
            cheek = "left cheek";
        }
        else
        {
            cheek = "right cheek";
        }

        text.line(" You slapped " + mob.getName() + "'s " + cheek + " and did 0 damage.");
        text.classicPattern(mob.getName() + " did " + damage + " damage to you!");
        player.reduceHealth(damage);
    }

    /**
     * This method is used to check whether the player has won.
     * @return true, if the player has won, false if not.
     */
    private boolean playerWon()
    {
        if(player.getCurrentRoom() == map.getEndRoom())
        {
            if(player.getBackpack().hasMainTrophy(goal)) text.winMessage(player.getName());
            else text.noMainTrophyMessage();
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            text.classicPattern("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * This method is used to create a character.
     * @return true if the creation was successfull, false otherwise.
     */
    private boolean createCharacter()
    {
        text.createCharacterText();
        Scanner reader = new Scanner(System.in);
        boolean selected = false;

        System.out.print("> ");
        String inputLine = reader.nextLine();
        if(genderExists(inputLine))
        {
            createPlayerObject(inputLine);
            return true;
        }
        else if(wordWasQuit(inputLine))
        {
            return false;
        }
        else
        {
            while(!selected)
            {
                text.classicPattern("Your command was not recognised. Please try again.");  //print this if the player has failed to input a valid gender or a word 'quit'.
                System.out.print("> ");
                inputLine = reader.nextLine();
                if(genderExists(inputLine))
                {
                    createPlayerObject(inputLine);
                    return true;
                }
                else if(wordWasQuit(inputLine))
                {
                    return false;
                }
            }
            return false;
        }
    }

    /**
     * This method creates a new player object.
     *
     * @param gender User's specified gender.
     */
    private void createPlayerObject(String gender)
    {
        player = new Player(gender, map.getStartRoom(), 40);
        text.characterText(player.getName());
    }

    /**
     * This method checks whether user has input a valid gender.
     *
     * @param gender User's specified gender.
     * @return true if the gender valid, false otherwise.
     */
    private boolean genderExists(String gender)
    {
        if(gender.trim().toLowerCase().equals("male") || gender.trim().toLowerCase().equals("female"))
        {
            return true;
        }
        return false;
    }

    /**
     * This method is used to check whether the word was 'quit'.
     *
     * @param word Word to check
     * @return true if the command was 'quit', false if it was not.
     */
    private boolean wordWasQuit(String word)
    {
        if(word.trim().toLowerCase().equals("quit"))
        {
            return true;
        }
        return false;
    }

    /**
     * This method checks whether the user input consists of two words or three and returns appropriate string.
     *
     * @param command User's input fro the console.
     * @return item or mob name
     */
    private String getObjectName(Command command)
    {
        if(command.hasThirdWord())
        {
            return (command.getSecondWord() + " " + command.getThirdWord()).toLowerCase();
        }
        else if(command.hasSecondWord())
        {
            return command.getSecondWord().toLowerCase();
        }
        else
        {
            return null;
        }
    }

    //Creating map and other objects

    /**
     * Create all the rooms, weapons, trophies, mobs and a backpack.
     */
    private void createMap()
    {
        Room wBeach, eBeach, nBeach, sBeach, cabin, cave, jungle, mountain, lake, dungeon, armory, roof, chestRoom, wc, mainLand, teleport;

        // create the rooms
        wBeach = new Room("You are on the beach of the Entanglement Island.");
        eBeach = new Room("You are on the beach of the Elevation Island.");
        nBeach = new Room("You are on the beach of the Ruthlessness Island.");
        sBeach = new Room("You are on the beach of the Placidity Island.");
        cabin = new Room("You have arrived to the cabin of the old man.");
        cave = new Room("You've just sneeked into a dangerous cave.");
        jungle = new Room("You've just entered the puzzling jungle. Be careful and don't get lost." + "\n " + "The place is full of cannibals and angry bees!");
        mountain = new Room("You have successfully reached the top of the Misty Mountain." + "\n " + "Be aware of the mighty vultures!");
        lake = new Room("You arrived to the magic lake.");
        dungeon = new Room("Shhhhhh..... be quiet chief, you have just entered the dungeon of the" + "\n " + "merciless Captain Toad.");
        armory = new Room("You arrived to the armory.");
        roof = new Room("You got on top of the dungeon.");
        chestRoom = new Room("You've just entered the captain room.");
        wc = new Room("Welcome to the captain's personal toilet. If your pants are already brimming with fear," + "\n " + "rest here, or else, continue the journey like a true warrior!");
        mainLand = new Room("You are on the main land.");
        teleport = new Room("");  //When a player gets to this room, he is teleported to a random room from the array list : 'availableTeleportDestinations';

        Room allRooms[] = new Room[] {wBeach, eBeach, nBeach, sBeach, cabin, cave, jungle, mountain, lake, dungeon, armory, wc, roof, chestRoom, mainLand};
        //Sets the available teleport destinations. Unfortunately, everytime a new room is added, this array must be adjusted.
        Room roomsToTeleportTo[] = new Room[] {wBeach, eBeach, nBeach, sBeach, cabin, cave, jungle, mountain, lake, dungeon, armory, wc};

        // initialise exits

        //South island
        sBeach.setExit("cabin", cabin);
        sBeach.setExit("west", wBeach);
        sBeach.setExit("east", eBeach);

        cabin.setExit("beach", sBeach);

        //East island
        eBeach.setExit("lake", lake);
        eBeach.setExit("mountain", mountain);
        eBeach.setExit("south", sBeach);
        eBeach.setExit("north", nBeach);

        lake.setExit("beach", eBeach);
        lake.setExit("mountain", mountain);

        mountain.setExit("lake", lake);
        mountain.setExit("beach", eBeach);

        //West island
        wBeach.setExit("south", sBeach);
        wBeach.setExit("north", nBeach);
        wBeach.setExit("jungle", jungle);

        cave.setExit("jungle", jungle);
        cave.setExit("teleport", teleport);

        jungle.setExit("cave", cave);
        jungle.setExit("beach", wBeach);

        //North island
        nBeach.setExit("east", eBeach);
        nBeach.setExit("west", wBeach);
        nBeach.setExit("dungeon", dungeon);

        dungeon.setExit("outside", nBeach);
        dungeon.setExit("vault", wc);
        dungeon.setExit("armory", armory);
        dungeon.setExit("boss", chestRoom);

        armory.setExit("back", dungeon);

        wc.setExit("back", dungeon);

        chestRoom.setExit("out", roof);

        mainLand.setExit("adventure", sBeach);

        //Food
        Food apple, toast, steak, magicCarrot, pear;

        apple = new Food(1, "red apple", 20);
        toast = new Food(1, "toast", 15);
        steak = new Food(1, "steak", 35);
        magicCarrot = new Food(2, "magic carrot", 100);
        pear = new Food(1, "sweet pear", 10);

        //Weapons
        Weapon sword, staff, beeGun, bow, axe;

        sword = new Weapon("Iron sword", 20, 0, 3, 0);
        staff = new Weapon("Thunder Staff", 80, 2, 7, 1);
        beeGun = new Weapon("Bee Gun", 75, 2, 7, 1);
        bow = new Weapon("Golden Bow", 35, 1, 5, 1);
        axe = new Weapon("Axe", 25, 0, 4, 0);

        //Trophies
        Trophy skull, feather, sting, chest;

        skull = new Trophy("Cannibal skull", 1);
        feather = new Trophy("Vulture feather", 1);
        sting = new Trophy("Bee sting", 1);
        chest = new Trophy("Treasure chest", 5);

        goal = chest;

        //Mobs
        Mob cannibal1, cannibal2, vulture, bee, captain;

        cannibal1 = new Mob("Cannibal", skull, 50, 5, 25, 70);
        cannibal2 = new Mob("Cannibal", skull, 50, 5, 25, 70);
        vulture = new Mob("Vulture", feather, 40, 10, 30, 65);
        bee = new Mob("Bee", sting, 20, 5, 12, 20);
        captain = new Mob("Captain Toad", chest, 200, 25, 80, 100);

        //Add items to rooms
        cabin.addWeapons(sword, 1);
        cabin.addWeapons(axe, 1);
        cabin.addWeapons(axe, 1);
        cabin.addFood(apple, 5);
        cabin.addFood(toast, 3);
        cabin.addFood(steak, 3);

        jungle.addFood(pear, 4);
        //jungle.addMob(cannibal1);
        //jungle.addMob(cannibal2);
        //jungle.addMob(bee);

        cave.addWeapons(beeGun, 1);

        lake.addFood(magicCarrot, 3);

        mountain.addMob(vulture);
        mountain.addWeapons(staff, 1);

        armory.addWeapons(bow, 7);
        armory.addWeapons(sword, 7);
        armory.addWeapons(axe, 7);

        chestRoom.addMob(captain);
        //=====================
        map = new Map(mainLand, roof, cabin, teleport, allRooms, roomsToTeleportTo); //Creating the actual map with mobs, rooms and items.
    }

}