import java.util.*;
import java.io.*;

/**
* The Games class contains methods pertaining to the action of playing the game and checking
* if words placed on the board are valid words in the dictionary and based on Scrabble rules.
* @author (Maya Vora and Max McEvoy)
*/

public class Games {
    Gameboard board;
    String userWord; 
    static int activeTurn;
    private int totalMovesMade = 0;
    private Users[] users = new Users[2]; 
    static char[] alphabetValues = {'A','A','A','A','A','A','A','A','A','B','B','C','C','D','D','D','D','E','E','E','E','E','E','E','E','E','E','E','E','F','F','G','G','G','H','H','I','I','I','I','I','I','I','I','I','J','K','L','L','L','L','M','M','N','N','N','N','N','N','O','O','O','O','O','O','O','O','P','P','Q','R','R','R','R','R','R','S','S','S','S','T','T','T','T','T','T','U','U','U','U','V','V','W','W','X','Y','Y','Z'};
    private int range = 98;
    private static ArrayList<Character> tileBag = new ArrayList<Character>();
    private static ArrayList<String> wordList = new ArrayList<String>();
    private static ArrayList<String> connectedWords = new ArrayList<String>();
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private static ArrayList<Character> playerOneTiles = new ArrayList<Character>();
    private static ArrayList<Character> playerTwoTiles = new ArrayList<Character>();
    
    /**
    * Creates the tile bag where all of the extra character tiles are kept for players to draw.
    */
    public Games()
    {
        this.createTileBag();
    }
    
    /**
    * Initiates the tile bag with the correct amount of letter tiles from which players 
    * will draw from.
    * 
    */
    public void createTileBag(){
        for(int i=0; i<alphabetValues.length; i++){
            tileBag.add(alphabetValues[i]);
        }
    }

    /**
    * Gives the players their 7 tiles randomly by using a random number generator.
    * @param emptyTray The user's tiles.
    */
    public void giveTiles(ArrayList<Character> emptyTray)
    {
        if(tileBag.size() != 0){
            while(emptyTray.size() < 7)
            {
                int randomNumber = (int)(Math.random()*range);
                char randomTile = tileBag.get(randomNumber);
                emptyTray.add(randomTile);
                tileBag.remove(randomTile);
                range--;  
            }
        }
        else {
            System.out.println("No more tiles left! Game over!");
        }
    }

    /**
    * Replaces the users tiles if they want a fresh tray of letters.
    * @param emptyTray The user's tiles. 
    */
    public void replaceAllTiles(ArrayList<Character> emptyTray) 
    {
        int y = emptyTray.size()-1;
        for(int i = y; i >= 0; i--)
        {
            tileBag.add(emptyTray.get(i));
            emptyTray.remove(i);
        }
        giveTiles(emptyTray);
    }

    /**
    * Displays the current players tiles.
    * @param user The current player's tiles being displayed.
    */
    public void displayTiles(Users user){
        System.out.println(user.getName() + "'s turn!");
        System.out.print("Your letters: ");
        if(user == users[0]){
            for(int i = 0; i < playerOneTiles.size(); i++){
                System.out.print(playerOneTiles.get(i));
                System.out.print(" ");
            }
            System.out.println();
        }
        else if(user == users[1]){
            for(int i = 0; i < playerTwoTiles.size(); i++){
                System.out.print(playerTwoTiles.get(i));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    /**
    * Creates the scrabble dictionary by reading the dicitonary file and adding all the
    * words into an arraylist called wordList.
    * @throws IOException if an IO error occurs while reading the file.
    */
    public static void createDictionary() throws IOException
    {
      Scanner dictionary = new Scanner(new File ("Dictionary.txt"));
      while(dictionary.hasNext())
      {
          wordList.add(dictionary.next());
      }
      dictionary.close();
    }

    /**
    * Checks if the word that the user entered is an actual word in the dictionary.
    * @param userWord The word that the user entered.
    * @return true if it is an actual word and false if it's not.
    */
    public static boolean isValid(String userWord)
    {
        if(wordList.indexOf(userWord) != -1){
            return true;
        }
        else{
            return false;
        }
    }

    /**
    * Runs the game Scrabble.
    * @param activeTurn Indicates whichever player's turn it is.
    * @param board The board in which the game is being played on.
    */
    public void playGame(int activeTurn, Gameboard board){
        Users currentUser = null;
        if(activeTurn == 1){
            currentUser = users[0];
            giveTiles(playerOneTiles);
        }
        else if(activeTurn == 2){
            currentUser = users[1];
            giveTiles(playerTwoTiles);
        }
        Gameboard.printBoard();
        displayTiles(currentUser);
        System.out.println("Hello " + currentUser.getName() + ". Follow all the directions or your turn will be up! Enter a word in all caps! To skip your turn, enter *. To refresh all your tiles, enter # (this counts as a turn as well). To quit, enter %. ");
        if(totalMovesMade == 0){
            System.out.println("Remember, the first word must start at the center square!");
        }
        System.out.print("WORD: ");
        Scanner in = new Scanner(System.in);
        String inputWord = in.nextLine();
        
        if(inputWord.equals("*")){
            System.out.println("Skip!");
            playGame(changeTurn(), board);
        }
        else if(inputWord.equals("#")){
           if(activeTurn == 1){
               replaceAllTiles(playerOneTiles);
           }
           else if(activeTurn == 2){
                replaceAllTiles(playerTwoTiles);
           }
           playGame(changeTurn(), board);
        }
        else if(inputWord.equals("%")){
            System.out.println("Game over!");
            System.out.println("User 1's score: " + playerOneScore);
            System.out.println("User 2's score: " + playerTwoScore);
            if(playerOneScore > playerTwoScore)
            {
                System.out.println("User one wins!");
            }
            if(playerOneScore < playerTwoScore)
            {
                System.out.println("User two wins!");
            }
            if(playerOneScore == playerTwoScore)
            {
                System.out.println("It's a tie!");
            }
        }
        else{
            System.out.println("What letters will you be using, NOT INCLUDING those already on the board? Type in all caps, no spaces between.");
            String lettersNeeded = in.nextLine();
            for(int i = 0; i < lettersNeeded.length(); i++){
                char y = lettersNeeded.charAt(i);
                if(activeTurn == 1){
                    if(playerOneTiles.indexOf(y) == -1){
                        System.out.println("You tried to cheat with letters you don't have!");
                        playGame(changeTurn(), board);
                    }
                }
                else if(activeTurn == 2){
                    if(playerTwoTiles.indexOf(y) == -1){
                        System.out.println("You tried to cheat with letters you don't have!");
                        playGame(changeTurn(), board);
                    }
                } 
            }
            System.out.println("Enter a direction. For down: 1, for right: 2.");
            int direc = in.nextInt();
            if(direc != 1 && direc != 2){
                System.out.println("That is not a valid direction! Your turn is up.");
                playGame(changeTurn(), board);
                
            }
            System.out.println("Column number?");
            int col = in.nextInt();
            if(col < 0 || col > 14){
                System.out.println("That is not a valid column!");
                playGame(changeTurn(), board);
            }
            System.out.println("Row number?");
            int row = in.nextInt();
            if(row < 0 || row > 14){
                System.out.println("That is not a valid row!");
                playGame(changeTurn(), board);
            }
            Turn moveMade = new Turn(inputWord, direc, col, row);
            if(canMakeMove(currentUser, moveMade, board))
            {
                board.placeWord(moveMade);
                
                for(int k = 0; k < lettersNeeded.length(); k++)
                {
                    if(activeTurn == 1){
                        char y = lettersNeeded.charAt(k);
                        int x = lettersNeeded.indexOf(y);
                        playerOneTiles.remove(x);
                    }
                    else if(activeTurn == 2){
                        char y = lettersNeeded.charAt(k);
                        int x = lettersNeeded.indexOf(y);
                        playerTwoTiles.remove(x);
                    }
                }
                totalMovesMade++;
                if(activeTurn == 1){
                    playerOneScore += Userscore.calculateScore(currentUser, moveMade, board);
                    System.out.println(currentUser.getName() + "'s score is: " + playerOneScore);
                }
                else if(activeTurn == 2){
                    playerTwoScore += Userscore.calculateScore(currentUser, moveMade, board);
                    System.out.println(currentUser.getName() + "'s score is: " + playerTwoScore);
                }
                playGame(changeTurn(), board); 
                
            }
            else{
                playGame(changeTurn(), board); 
            }  
        }
    }
    
    /**
    * Changes whose turn it is to make their move.
    * @return The current turn.
    */
    public int changeTurn(){
        if (activeTurn == 1 || activeTurn == 0){
            activeTurn = 2;
        }
        else if(activeTurn == 2){
            activeTurn = 1;
        }
        return activeTurn;
    }

    /**
    * Getter method that retrieves the active turn.
    * @return The current turn.
    */
    public static int getActiveTurn()
    {
        return activeTurn;
    }


    /**
    * Takes input from user and creates a player based on the name they entered.
    */
    public void createNewUsers()
    {
        Scanner input = new Scanner(System.in);
        for(int i = 0; i < users.length; i++)
        {
            System.out.println("Enter your name, User" + (i+1) +":");
            ArrayList<Character> pieces = new ArrayList<Character>();
            users[i] = new Users(input.next(), pieces);
        }
    }

    /**
    * Runs tests to see if the user can make the move they are trying to by checking whether 
    * or not the user has the correct tiles, is in bounds of the board, if it's their turn,
    * if the user's word is touching another word, and if the user is placing their word
    * across the center piece if it's the first turn.
    * @param currentUser The player whose turn it is.
    * @param turn The directions for the word to be placed and checked.
    * @param board The gameboard being played on and being changed.
    * @return True if the word can be placed and false if it can't and fails the tests.
    */
    public boolean canMakeMove(Users currentUser, Turn turn, Gameboard board)
    {
        String userWord = turn.getUserWord();
        int row = turn.getStartRow();
        int column = turn.getStartCol();
        int direction = turn.getDirection();
        boolean isTilePresent = false;

        if((direction == Turn.RIGHT && (column + userWord.length() > 14)) || (direction == Turn.DOWN && (row + userWord.length() > 14))){
            System.out.println("Invalid move: Out of bounds!");
            return false;
        }

        if(!isValid(userWord)){
            System.out.println("This word does not exist! Make another word.");
            return false;
        }

        if(direction == Turn.RIGHT)
        {
            if((board.accessTile(row, column + userWord.length() + 1) != '□' && board.accessTile(row, column + userWord.length() + 1) != '+' && board.accessTile(row, column + userWord.length() + 1) != '$' && board.accessTile(row, column + userWord.length() + 1) != '*' && board.accessTile(row, column + userWord.length() + 1) != '#' && board.accessTile(row, column + userWord.length() + 1) != '/') || (board.accessTile(row, column - 1) != '□' && board.accessTile(row, column - 1) != '+' && board.accessTile(row, column - 1) != '$' && board.accessTile(row, column - 1) != '*' && board.accessTile(row, column - 1) != '#' && board.accessTile(row, column - 1) != '/'))
            {
                return false;
            }
        }
        else if(direction == Turn.DOWN)
        {
            if((board.accessTile(row + userWord.length() + 1, column) != '□' && board.accessTile(row + userWord.length() + 1, column) != '$' && board.accessTile(row + userWord.length() + 1, column) != '/' && board.accessTile(row + userWord.length() + 1, column) != '*' && board.accessTile(row + userWord.length() + 1, column) != '#' && board.accessTile(row + userWord.length() + 1, column) != '+') || (board.accessTile(row - 1, column) != '□' && board.accessTile(row - 1, column) != '$' && board.accessTile(row - 1, column) != '*' && board.accessTile(row - 1, column) != '#' && board.accessTile(row - 1, column) != '+' && board.accessTile(row - 1, column) != '/'))
            {
                System.out.println("It isnt working here 1");
                return false;
            }
        }
        

        if(totalMovesMade == 0){
            if(row != 7 || column != 7){
                System.out.println("The first move must touch the center square!");
                System.out.println("It isnt working here 5");
                return false;
            }
        }
        
        if(totalMovesMade >= 1){
            for(int i = 0; i < userWord.length(); i++){
                if(direction == Turn.RIGHT){
                    if((board.accessTile(row, column + i) != '□' && board.accessTile(row, column + i) != '*' && board.accessTile(row, column + i) != '+' && board.accessTile(row, column + i) != '$' && board.accessTile(row, column + i) != '#' && board.accessTile(row, column + i) != '/') && board.accessTile(row, column + i) == userWord.charAt(i))
                        {
                        isTilePresent = true;
                        }
                }
                else if(direction == Turn.DOWN){
                    if((board.accessTile(row + i, column) != '□' && board.accessTile(row +i, column) != '*' && board.accessTile(row + i, column) != '+' && board.accessTile(row + i, column) != '$' && board.accessTile(row + i, column) != '#' && board.accessTile(row + i, column) != '/') && board.accessTile(row + i, column) == userWord.charAt(i))
                        {  
                        isTilePresent = true;
                        }  
                }
            }
                
            if(isTilePresent == false)
            {
                System.out.println("New word has to touch an already existing one!");
                return false;
            }   
            
        }
        checkConnectedWords(userWord, row, column, direction, board);
        if(validateConnectedWords(connectedWords) == false)
        {
            System.out.println("The connected word you tried to make is not a word!");
            return false;
        }
        turn.realMove = true;
        Turn.isMoveValid(turn);
        turn.otherWordsCreated = connectedWords;
        
        return true;
    }

    /**
    * Creates an ArrayList of the words that are connected together after the 
    * user has placed their word on the board (the words that are formed after the first move).
    * @param userWord The word being examined that was entered by the user.
    * @param row The row the word being examined starts in.
    * @param col The column the word being examined starts in.
    * @param direction The direction the user entered.
    * @param board The scrabble board being examined.
    * @return An ArrayList containing all the connected words.
    */
    public ArrayList<String> checkConnectedWords(String userWord, int row, int col, int direction, Gameboard board)
    {
        ArrayList<String> connectedWords = new ArrayList<>();
        if(direction == Turn.RIGHT)
        {
            for(int i = col; i < col + userWord.length(); i++)
            {
                StringBuilder connecWords = new StringBuilder();
                if(board.isSpaceEmpty(row, i))
                {
                    int j = row;
                    while(j > 0 && board.isSpaceEmpty(j - 1, i))
                    {
                        j--;
                    }
                    while(j < 15 && !board.isSpaceEmpty(j ,i))
                    {
                        connecWords.append(board.accessTile(j,i));
                        j++;
                    }
                    if(connecWords.length() > 1)
                    {
                        connectedWords.add(connecWords.toString());
                    }
                }
            }
        }
        else if(direction == Turn.DOWN)
        {
            for(int k = row; k < row + userWord.length(); k++)
            {
                StringBuilder newConnecWords = new StringBuilder();
                if(board.isSpaceEmpty(k, col))
                {
                    int j = col;
                    while(j > 0 && board.isSpaceEmpty(k, j - 1))
                    {
                        j--;
                    }
                    while(j < 15 && !board.isSpaceEmpty(k, j))
                    {
                        newConnecWords.append(board.accessTile(k,j));
                        j++;
                    }
                    if(newConnecWords.length() > 1)
                    {
                        connectedWords.add(newConnecWords.toString());
                    }
                }
            }
        }
        return connectedWords;
    }

    /**
    * Goes through the ArrayList of connected words on the board and checks to make sure
    * they are real words present in the dictionary.
    * @param connectWordss The ArrayList being examined that has all the connected words in
    * it.
    * @return true if the word is present in the dictionary and false if it is not present.
    */
    private boolean validateConnectedWords(ArrayList<String> connectWordss)
    {
        connectedWords = connectWordss;
        for(String word: connectedWords)
        {
            if(!isValid(word))
            {
                System.out.println(word + " is not a valid secondary word!");
                return false;
            }
        }
        return true;
    }
}