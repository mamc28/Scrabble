/**
* The main class runs our game.
* @author(Max McEvoy n Maya Vora)
*/
public class Main 
{
    /**
    * Our main method. What is being used to run the game from the games class.
    * @param args The command line arguments.
    */
    public static void main(String[] args) 
    {
        Games game = new Games();
        try{
            game.createDictionary();
        }
        catch(Exception e)
        {
            System.out.println("There is an issue.");
        }
        Gameboard board = new Gameboard();
        game.createNewUsers();
        game.createTileBag();
        game.playGame(1, board);
    }
}