import java.util.ArrayList;
import java.util.Random;
/**
 * Class Map - a map in the game.
 *
 * This class is part of "The peacemaker" application.
 * "The peacemaker" is a simple, text based adventure game.
 *
 * A "Map" represents a game map. It holds a list of rooms throughout which
 * a player can travel. An object of this class also has instance fields for
 * relevant rooms such as the start room, the finish room, the task room (where the
 * player is given a task in that map) and the teleport room (when a player enters this
 * room, he is taken to another available random room).
 *
 * @author Vakaris Paulavičius (Student number: 20062023)
 * @version 2020.11.30
 */
public class Map
{
    // instance variables - replace the example below with your own
    private ArrayList<Room> availableTeleportDestinations;
    private ArrayList<Room> rooms;

    private Room startRoom;
    private Room endRoom;
    private Room taskRoom;
    private Room teleportRoom;

    private boolean taskRoomVisited;

    /**
     * Create a map and initialize the speacial rooms.
     */
    public Map(Room startRoom, Room endRoom, Room taskRoom, Room teleportRoom, Room[] allRooms, Room[] teleportDestinations)
    {
        this.startRoom = startRoom;
        this.endRoom = endRoom;
        this.taskRoom = taskRoom;
        this.teleportRoom = teleportRoom;
        taskRoomVisited = false;
        availableTeleportDestinations = new ArrayList<>();
        rooms = new ArrayList<>();
        addRooms(allRooms);
        setTeleportDestinations(teleportDestinations);
    }

    /**
     * This method is used to get the start room of this map.
     * @return a Room which is the start room in this map.
     */
    public Room getStartRoom()
    {
        return startRoom;
    }

    /**
     * This method is used to get the end room of this map.
     * @return a Room which is the end room in this map.
     */
    public Room getEndRoom()
    {
        return endRoom;
    }

    /**
     * This method is used to get the teleport room of this map.
     * @return a Room which is the teleport room in this map.
     */
    public Room getTeleportRoom()
    {
        return teleportRoom;
    }

    /**
     * This method is used to get the task room of this map.
     * @return a Room which is the task room in this map.
     */
    public Room getTaskRoom()
    {
        return taskRoom;
    }

    /**
     * This method is used to add a single room to the map.
     *
     * @param room A Room which to add.
     */
    public void addRoom(Room newRoom)
    {
        rooms.add(newRoom);
    }

    /**
     * This method is used to add an array of rooms to the map.
     *
     * @param newRooms A Room array which objects to add.
     */
    public void addRooms(Room[] newRooms)
    {
        for(int i = 0 ; i < newRooms.length ; i ++)
        {
            rooms.add(newRooms[i]);
        }
    }

    /**
     * This method is used to add all the available locations to the array list 'availableTeleportDestinations'.
     *
     * @param rooms[] An array with Rooms available to teleport to.
     */
    public void setTeleportDestinations(Room rooms[])
    {
        for(int i = 0 ; i < rooms.length ; i ++)
        {
            availableTeleportDestinations.add(rooms[i]);
        }
    }

    /**
     * This method pick a random room from the list "availableTeleportDestinations" and returns it.
     * @return a Room which the player teleport to.
     */
    public Room teleportToRoom()
    {
        Random rnd = new Random();
        return availableTeleportDestinations.get(rnd.nextInt(availableTeleportDestinations.size()));
    }

    /**
     * This method returs if the taskRoom was visited.
     * @return true if it was, false otherwise.
     */
    public boolean taskRoomWasVisited()
    {
        return taskRoomVisited;
    }

    /**
     * This method is used to set the instance variable taskRoomVisited to true.
     */
    public void visitTaskRoom()
    {
        taskRoomVisited = true;
    }

    //Methods below were not used yet. For future updates.

    /**
     * This method is used to set the start room for this map.
     *
     * @param room A Room which is the start room in this map.
     */
    public void setStartRoom(Room room)
    {
        startRoom = room;
    }

    /**
     * This method is used to set the task room for this map.
     *
     * @param room A Room which is the task room in this map.
     */
    public void setTaskRoom(Room room)
    {
        taskRoom = room;
    }

    /**
     * This method is used to set the end room for this map.
     *
     * @param room A Room which is the end room in this map.
     */
    public void setEndRoom(Room room)
    {
        endRoom = room;
    }

    /**
     * This method is used to set the teleport room for this map.
     *
     * @param room A Room which is the teleport room in this map.
     */
    public void setTeleportRoom(Room room)
    {
        teleportRoom = room;
    }
}
