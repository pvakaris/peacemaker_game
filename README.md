***
## About this project
This is my first non "Hello World" Java project implemented in my first year studying Computer Science. 

At that moment, I did not know about things like inheritance, what is good-structured maintainable code. 

I am uploading this project to indicate where I started and where I am now in coding. 

Everything is left as it was. I haven't changed a single line. Have fun. :)

P.S. Written using BlueJ.

***


* Project: "The Peacemaker"
* Authors: Vakaris Paulavičius (Student number: 20062023).
* Version: 1.1
* Date:    2020.11.28

* "The peacemaker" is a simple text-based adventure game.
  Here a player travels throughout a fictitious map with a goal to defeat the the main boss
  and bring the peace to the village as well as a chest full of coins.

* To start this application, call the main class method of the "Game" class and enter no parameters.
  In the beginning, create your own character and proceed to the journey.
  
* To win the game, a player must go "out" of the boss room with a chest in his backpack.

* All the relevant details are explained when a player enters the old man's cabin. It can be found in the South island.
  
* There is a primitive map among the project files which will hopefully help the user to imagine the game's world more easily.
  It is called: "mapV1.png";

* Existing islands: the North island (Ruthlessness island)  //where the boss is
                    the South island (Placidity island) //where the old man lives
                    the West island (Entanglement island) //jungle, cave, teleport
                    the East island (Elevation island) //lake, mountain

* Purpose of a command:

       go:     Used to go to the next available place.
       quit:   Used to quit the game.
       help:   Used to get all available commands.
       hit:    Used to hit a mob.
       eat:    Used to consume a food product and increment health.
       look:   Used to look around the current place and get information about items that are in that place.
       take:   Used to pick up an item from the player's current location.
       drop:   Used to drop an item from the backpack at the player's current location.
       info:   Used to get the name of the current place and all available exits.
       list;   Used to get a list of items that are in the backpack.
       stats:  Used to get health and backpack stats.
       equip:  Used to equip a weapon that is in the backpack.
       about   Used to get a description of an item that is in the backpack or in the current room. It can also be used to get a description of a mob that is currently in that room.
       show:   Used to get information about the weapon that is currently equiped.

* How to use a command:
       go:     > go [place] (exactly as it is written);
       quit:   > quit;
       help:   > help;
       hit:    > hit [mob's name];
       eat:    > eat [food item's name];
       look:   > look;
       take  : > pickup [item's name];
       drop:   > drop [item's name];
       info:   > info;
       list;   > list;
       stats:  > stats;
       equip:  > equip [item's name];
       about   > about [item's name];
       show:   > show;
