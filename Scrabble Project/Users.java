import java.util.*;

/**
* Represents the two users that play the game along with their names and tiles.
* @author (Max McEvoy and Maya Vora)
*/

public class Users
{
    private String userName;
    private static ArrayList<Character> tiles = new ArrayList<Character>();
    private static ArrayList<Character> playerOneTiles = new ArrayList<Character>();
    private static ArrayList<Character> playerTwoTiles = new ArrayList<Character>();
    private Users[] users;

    /**
    * Initializes the user to have their own name and tiles.
    * @param name The name of the user.
    * @param pieces The tiles specific to the user.
    */
    public Users(String name, ArrayList<Character> pieces)
    {
        userName = name;
        tiles = pieces;
    }
    
    /**
    * Displays the name the user entered.
    * @return The name the player entered for themself.
    */
    public String getName()
    {
        return userName;
    }

    /**
    * Displays the tiles the user has in their inventory.
    * @return The user's tiles.
    */
    public static ArrayList<Character> getTiles()
    {
        if(Games.getActiveTurn() == 1){
            return playerOneTiles;
        }
        else if(Games.getActiveTurn() == 2){
            return playerTwoTiles;
        }
        return null;
    }

}