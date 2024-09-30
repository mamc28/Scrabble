import java.util.*;

/**
* The Turn class contains methods that  pertain to the user input commands and keeps 
* track of the number of moves.
* @author (Max McEvoy and Maya Vora)
*/

public class Turn{
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static int totalMovesMade = 0;
    static String userWord;
    private static int startRow;
    private static int startCol;
    private static int direction;
    boolean realMove;
    ArrayList<String> otherWordsCreated;

    /**
    * Initializes the user's input commands into variables to be used in the methods that
    * come later in the class.
    * @param word The word the user desires.
    * @param dir The direction the user desires when forming their word.
    * @param startingColumn The column the user desires to start at when forming their word.
    * @param startingRow The row the user desires to start at when forming their word.
    */

    public Turn(String word, int dir, int startingColumn, int startingRow){
        userWord = word;
        direction = dir;
        startRow = startingRow;
        startCol = startingColumn;
        realMove = false;
    }

    /**
    * Displays the starting row
    * @return The row the user entered to start at.
    */
    public static int getStartRow()
    {
        return startRow;
    }

    /**
    * Displays the starting column.
    * @return The column the user entered to start at.
    */
    public static int getStartCol()
    {
        return startCol;
    }

    /**
    * Gets the word the user typed.
    * @return The word that the user entered to be placed on the board.
    */
    public static String getUserWord()
    {
        return userWord;
    }
    
    /**
    * Gets the direction the user specified.
    * @return The direction that the user entered to be used for placement on the board.
    */
    public static int getDirection()
    {
        return direction;
    }

    /**
    * Keeps track of the total moves made to ensure the first move is on the middle space of the board. 
    * @return The number of total moves made.
    */
    public static int movesMadeSoFar(){
        return totalMovesMade;
    }

    /**
    * Getter method that evaluates whether or not the move the player made is valid.
    * @return True if the move is valid and false if it isn't.
    */
    public boolean getRealMove()
    {
        return realMove;
    }

    /**
    * Checks to make sure the move is actually playable.
    * @param turn The turn object representing the move to be validated.
    */
    public static void isMoveValid(Turn turn){
        if(turn.realMove == true){
            System.out.println("Valid move!");
        }
        else {
            System.out.println("Invalid move!");
        }
    }
}