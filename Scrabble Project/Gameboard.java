import java.util.*;
/**
* Represents the board that the scrabble game is played on that include methods pertaining to the board.
* @author(Max McEvoy n Maya Vora)
*/
public class Gameboard {

public static char[][] board; 
private static final int BOARD_WIDTH = 15; 
private static final int BOARD_LENGTH = 15; 
private static char doubleWord = '+'; 
private static char tripleWord = '$'; 
private static char centerBoard = '*'; 
private static char tripleLetter = '#'; 
private static char doubleLetter = '/'; 
static ArrayList<String> wordList = new ArrayList<String>();

/**
* Constructs a gameboard of a specific length and width.
*/
    public Gameboard() 
    {    
      board = new char[BOARD_WIDTH][BOARD_LENGTH];
        for(int i = 0; i < BOARD_WIDTH; i++)
        {
            for(int k = 0; k < BOARD_LENGTH; k++)
            {
                board[i][k] = '□';
            }
        }
    board[7][7] = centerBoard;
    board[0][0] = tripleWord;
    board[0][7] = tripleWord;
    board[0][14] = tripleWord;
    board[7][0] = tripleWord;
    board[7][14] = tripleWord;
    board[14][0] = tripleWord;
    board[14][7] = tripleWord;
    board[14][14] = tripleWord; 
    board[1][5] = tripleLetter;
    board[1][9] = tripleLetter;
    board[5][1] = tripleLetter;
    board[5][5] = tripleLetter;
    board[5][9] = tripleLetter;
    board[5][13] = tripleLetter;
    board[9][1] = tripleLetter;
    board[9][5] = tripleLetter;
    board[9][9] = tripleLetter;
    board[9][13] = tripleLetter;
    board[13][5] = tripleLetter;
    board[13][9] = tripleLetter;
    board[1][1] = doubleWord;
    board[2][2] = doubleWord; 
    board[3][3] = doubleWord; 
    board[4][4] = doubleWord; 
    board[10][10] = doubleWord; 
    board[11][11] = doubleWord; 
    board[12][12] = doubleWord; 
    board[13][13] = doubleWord;  
    board[1][14] = doubleWord;
    board[2][13] = doubleWord;
    board[3][12] = doubleWord;
    board[4][11] = doubleWord;
    board[0][3] = doubleLetter;
    board[0][11] = doubleLetter;
    board[2][6] = doubleLetter;
    board[2][8] = doubleLetter;
    board[3][7] = doubleLetter;
    board[3][0] = doubleLetter;
    board[3][14] = doubleLetter;
    board[6][2] = doubleLetter;
    board[6][6] = doubleLetter;
    board[6][8] = doubleLetter;
    board[7][3] = doubleLetter;
    board[7][11] = doubleLetter;
    board[8][2] = doubleLetter;
    board[8][6] = doubleLetter;
    board[8][8] = doubleLetter;
    board[11][0] = doubleLetter;
    board[11][7] = doubleLetter;
    board[11][14] = doubleLetter;
    board[12][6] = doubleLetter;
    board[12][8] = doubleLetter;
    board[14][3] = doubleLetter;
    board[14][11] = doubleLetter;
    }

    /**
    * Prints out the scrabble board.
    */
    public static void printBoard()
    {
        System.out.println("0  1  2  3  4  5  6  7  8  9 10 11 12 13 14");
        for(int i = 0; i < BOARD_WIDTH; i++){
            for(int k = 0; k < BOARD_LENGTH; k++){                
                System.out.print(board[i][k] + "  ");
                if(k == BOARD_LENGTH-1){
                    System.out.print(i);
                }
            }
            System.out.println();
        }
    }


    /**
    * Places the word that a user wants to make onto the board.
    * @param turn Provides information about the user and their word and where they want it
    * on the board.
    */
    public void placeWord(Turn turn){
        if(turn.getRealMove() == true){
            String chosenWord = turn.userWord;
            int row = turn.getStartRow();
            int col = turn.getStartCol();

            for(int i = 0; i < chosenWord.length(); i++){
                if(turn.getDirection() == Turn.RIGHT){
                    board[row][col+i] = chosenWord.charAt(i);
                }
                else if(turn.getDirection() == Turn.DOWN){
                    board[row + i][col] = chosenWord.charAt(i);
                }
            }
        }
    }
    
    /**
    * Accesses a certain space on the board and returns the tile.
    * @param row The row to be looked at.
    * @param col The column to be looked at.
    * @return A char that represents the space on the board 
    */
    public char accessTile(int row, int col)
    {
        return this.board[row][col];
    }

    /**
    * Evaluates the status of a space on the board.
    * @param row The current row being examined.
    * @param col The current column being examined.
    * @return A boolean that returns true if the space is empty and false if there is a letter tile
    * on it.
    */
    public boolean isSpaceEmpty(int row, int col)
    {
        if(board[row][col] == '□' || board[row][col] == '+' || board[row][col] == '$' || board[row][col] == '*' || board[row][col] == '#' || board[row][col] == '/')
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}