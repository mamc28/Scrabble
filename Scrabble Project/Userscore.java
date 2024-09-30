import java.util.*;
/**
* The Userscore class contains the method that takes care of calculating the score of the
* words placed on the board. 
* @author (Maya Vora and Max McEvoy)
*/
public class Userscore
{
    
    private static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static int[] points = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    private static int currentScore = 0;

    /**
    * Calculates the score based on the person's word that they are placing on the board.
    * @param user The user whose turn it is.
    * @param turn Gets the information from the current players turn.
    * @param board The scrabble board to be looked at for the word.
    * @return The users current score.
    */
    public static int calculateScore(Users user, Turn turn, Gameboard board)
    {
        String wordToScore = Turn.getUserWord();
        int direction = Turn.getDirection();
        int theRow = Turn.getStartRow();
        int colOne = Turn.getStartCol();
        int i = 0;
        
        if(direction == Turn.RIGHT)
        {
            for(int k = colOne; k <= colOne + wordToScore.length()-1; k++) 
            {
                if(board.board[theRow][k] == board.board[0][3] || board.board[theRow][k] == board.board[0][11] || board.board[theRow][k] == board.board[2][6] || board.board[theRow][k] == board.board[2][8] || board.board[theRow][k] == board.board[3][7] || board.board[theRow][k] == board.board[3][0] || board.board[theRow][k] == board.board[3][14] || board.board[theRow][k] == board.board[6][2] || board.board[theRow][k] == board.board [6][6] || board.board[theRow][k] == board.board[6][8] || board.board[theRow][k] == board.board[7][3] || board.board[theRow][k] == board.board[7][11] || board.board[theRow][k] == board.board[8][2] || board.board[theRow][k] == board.board[8][6] || board.board[theRow][k] == board.board[8][8] || board.board[theRow][k] == board.board[11][0] || board.board[theRow][k] == board.board[11][7] || board.board[theRow][k] == board.board[11][14] || board.board[theRow][k] == board.board[12][6] || board.board[theRow][k] == board.board[12][8] || board.board[theRow][k] == board.board[14][3] || board.board[theRow][k] == board.board[14][11])
                { 
                    char currentLetter = wordToScore.charAt(i);
                    int location = letters.indexOf(currentLetter);
                    int tileValue = points[location];
                    currentScore = currentScore + (2*tileValue);
                    i++;
                    if(i == wordToScore.length())
                    {
                        return currentScore;
                    }
                }  
                    //Triple letter spaces.

                else if(board.board[theRow][k] == board.board[1][5] || board.board[theRow][k] == board.board[1][9] || board.board[theRow][k] == board.board[5][1] || board.board[theRow][k] == board.board[5][5] || board.board[theRow][k] == board.board[5][9] || board.board[theRow][k] == board.board[5][13] || board.board[theRow][k] == board.board[9][1] || board.board[theRow][k] == board.board[9][5] || board.board[theRow][k] == board.board[9][9] || board.board[theRow][k] == board.board[9][13] || board.board[theRow][k] == board.board[13][5] || board.board[theRow][k] == board.board[13][9])
                {
                    char currentLetter = wordToScore.charAt(i); 
                    int location = letters.indexOf(currentLetter);
                    int tileValue = points[location];
                    currentScore = currentScore + (3*tileValue);
                    i++;
                    if(i == wordToScore.length())
                    {
                        return currentScore;
                    }
                }
                    //Triple word spaces.

                else if(board.board[theRow][k] == board.board[0][0] || board.board[theRow][k] == board.board[0][7] || board.board[theRow][k] == board.board[0][14] || board.board[theRow][k] == board.board[7][0] || board.board[theRow][k] == board.board[7][14] || board.board[theRow][k] == board.board[14][0] || board.board[theRow][k] == board.board[14][7] || board.board[theRow][k] == board.board[14][14])
                {

                    char currentLetter = wordToScore.charAt(i);
                    int location = letters.indexOf(currentLetter);
                    int tileValue = points[location];
                    currentScore += tileValue;
                    i++;
                    if(i == wordToScore.length()){
                        currentScore*=3;
                        return currentScore;
                    }
                }

                else if(board.board[theRow][k] == board.board[1][1] || board.board[theRow][k] == board.board[2][2] || board.board[theRow][k] == board.board[3][3] || board.board[theRow][k] == board.board[4][4] || board.board[theRow][k] == board.board[10][10] || board.board[theRow][k] == board.board[11][11] || board.board[theRow][k] == board.board[12][12] || board.board[theRow][k] == board.board[13][13] || board.board[theRow][k] == board.board[1][14] || board.board[theRow][k] == board.board[2][13] || board.board[theRow][k] == board.board[3][12] || board.board[theRow][k] == board.board[4][11])
                {
                    char currentLetter = wordToScore.charAt(i);
                    int location = letters.indexOf(currentLetter);
                    int tileValue = points[location];
                    currentScore += tileValue;
                    i++;
                    if(i == wordToScore.length()){
                        currentScore*=2;
                        return currentScore;
                    }
                }

                else{
                    char currentLetter = wordToScore.charAt(i); 
                    int location = letters.indexOf(currentLetter);
                    int tileValue = points[location];
                    currentScore += tileValue;
                    i++;
                    if(i == wordToScore.length()){
                        return currentScore;
                    }
                }
            }
        }
                    
        if(direction == Turn.DOWN)
        {
            for(int k = theRow; k <= theRow + wordToScore.length()-1; k++)
            { 
                if(board.board[colOne][k] == board.board[0][3] || board.board[colOne][k] == board.board[0][11] || board.board[colOne][k] == board.board[2][6] || board.board[colOne][k] == board.board[2][8] || board.board[colOne][k] == board.board[3][7] || board.board[colOne][k] == board.board[3][0] || board.board[colOne][k] == board.board[3][14] || board.board[colOne][k] == board.board[6][2] || board.board[colOne][k] == board.board[6][6] || board.board[colOne][k] == board.board[6][8] || board.board[colOne][k] == board.board[7][3] || board.board[colOne][k] == board.board[7][11] || board.board[colOne][k] == board.board[8][2] || board.board[colOne][k] == board.board[8][6] || board.board[colOne][k] == board.board[8][8] || board.board[colOne][k] == board.board[11][0] || board.board[colOne][k] == board.board[11][7] || board.board[colOne][k] == board.board[11][14] || board.board[colOne][k] == board.board[12][6] || board.board[colOne][k] == board.board[12][8] || board.board[colOne][k] == board.board[14][3] || board.board[colOne][k] == board.board[14][11]){ 
                    char currentLetter = wordToScore.charAt(i); 
                    int location = letters.indexOf(currentLetter);
                    int tileValue = points[location];
                    currentScore = currentScore + (2*tileValue);
                    i++;
                    if(i == wordToScore.length()){
                        return currentScore;
                    }
                }
                else if(board.board[colOne][k] == board.board[1][5] || board.board[colOne][k] == board.board[1][9] || board.board[colOne][k] == board.board[5][1] || board.board[colOne][k] == board.board[5][5] || board.board[colOne][k] == board.board[5][9] || board.board[colOne][k] == board.board[5][13] || board.board[colOne][k] == board.board[9][1] || board.board[colOne][k] == board.board[9][5] || board.board[colOne][k] == board.board[9][9] || board.board[colOne][k] == board.board[9][13] || board.board[colOne][k] == board.board[13][5] || board.board[colOne][k] == board.board[13][9]){
                        char currentLetter = wordToScore.charAt(i); 
                        int location = letters.indexOf(currentLetter);
                        int tileValue = points[location];
                        currentScore = currentScore + (3*tileValue);
                        i++;
                        if(i == wordToScore.length()){
                            return currentScore;
                        }
                    }
                else if(board.board[colOne][k] == board.board[0][0] || board.board[colOne][k] == board.board[0][7] || board.board[colOne][k] == board.board[0][14] || board.board[colOne][k] == board.board[7][0] || board.board[colOne][k] == board.board[7][14] || board.board[colOne][k] == board.board[14][0] || board.board[colOne][k] == board.board[14][7] || board.board[colOne][k] == board.board[14][14]){
                    char currentLetter = wordToScore.charAt(i);
                    int location = letters.indexOf(currentLetter);
                    int tileValue = points[location];
                    currentScore += tileValue;
                    i++;
                    if(i == wordToScore.length()){
                        currentScore*=3;
                        return currentScore;
                    }
                }

                else if(board.board[colOne][k] == board.board[1][1] || board.board[colOne][k] == board.board[2][2] || board.board[colOne][k] == board.board[3][3] || board.board[colOne][k] == board.board[4][4] || board.board[colOne][k] == board.board[10][10] || board.board[colOne][k] == board.board[11][11] || board.board[colOne][k] == board.board[12][12] || board.board[colOne][k] == board.board[13][13] || board.board[colOne][k] == board.board[1][14] || board.board[colOne][k] == board.board[2][13] || board.board[colOne][k] == board.board[3][12] || board.board[colOne][k] == board.board[4][11]){
                    char currentLetter = wordToScore.charAt(i);
                    int location = letters.indexOf(currentLetter);
                    int tileValue = points[location];
                    currentScore += tileValue;
                    i++;
                    if(i == wordToScore.length()){
                        currentScore*=2;
                        return currentScore;
                    }
                }

                else
                {
                    char currentLetter = wordToScore.charAt(i); 
                    int location = letters.indexOf(currentLetter);
                    int tileValue = points[location];
                    currentScore += tileValue;
                    i++;
                    if(i == wordToScore.length()){
                        return currentScore;
                    }
                }
            }
        }
        System.out.println(currentScore);
        return currentScore;
    }
}

    