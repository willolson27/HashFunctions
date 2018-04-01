import java.util.Arrays;
/**
 * 
 * @author will olson (git: willolson27)
 *Assignment 7
 *Due March 31, 2018
 *
 */
public class TicTacToe {
	
  //FIELDS
  public final static int ROWS = 3;
  public final static int COLS = 3;
  public final static int POSSIBILITIES = (int) Math.pow(3,9);
  public final static int CHAR_POSSIBILITIES = 3; // x, o or space
  
  /**
   * returns the number of characters in a 2D array that are equal to a given char
   * @param b array to be looked through
   * @param ch char to be checked for
   * @return number of times ch occurs in b
   */
  private static int numChars(char[][] b, char ch) {
  int total = 0;
    for (int r = 0; r < ROWS; r++)
      for (int c = 0; c < COLS; c++)
        if (ch == b[r][c]) 
        total++;
    return total;
  }
  
  /**
   * checks that the TTT string is valid - at least 3 xs and 2 os, or 3 os and 2 xs,
   * at least one more x or one more o
   * @param board - board to be looked through
   * @return - boolean for whether or not the board is valid (true if so false if not)
   */
  public static boolean valid(char[][] board) {
  
    int numX = numChars(board, 'x');
    int numO = numChars(board, 'o');
    if (! (numX > 2 || numO > 2)) return false;
    if ((numX == numO + 1) || (numO == numX + 1)) return true;
    return false;
  }
  
  /**
   * converts a 2d char array (a board) to its string representation
   * @param b board to be converted
   * @return string reprsentation of given board
   */
   public static String boardToString(char[][] b) {
     String result = "";
     for (int r = 0; r < ROWS; r++) {
       for (int c = 0; c < COLS; c++) 
         result += b[r][c];
  //     result += "\n";
       }
     return result;
   }
 
   	/**
   	 * converts a given board string to a char array that represents the board
   	 * @param board board string to be converted
   	 * @return char array that represents the boar
   	 */
    public static char[][] stringToBoard(String board) {
      char[][] b = new char[ROWS][COLS];
     int index = 0;
     for (int r = 0; r < ROWS; r++) {
       for (int c = 0; c < COLS; c++) 
         b[r][c] = whichLetter(board.charAt(index++));
       }
     return b;
   }

   /**
    * Checks which letter the given input char corresponds to
    * @param ch char to be checked (should be a  number)
    * @return letter that ch corresponds to
    */
   public static char whichLetter(char ch) {
      switch (ch) {
         case '1' : return 'x';
         case '2' : return 'o';
         case '0'  : return ' ';
         default: return ch;
       }
   }
     
   /** 
    * creates a board from a board string with numbers
    * @param s number string to be converted
    * @return board representation of the given string
    */
   public static char[][] makeBoard(String s) {
   char[][] b = new char[ROWS][COLS];
   int ch = 0;
   for (int r = 0; r < ROWS; r++)
     for (int c = 0; c < COLS; c++){         
       b[r][c] = whichLetter(s.charAt(ch));
       ch++;
     }
   return b;
   }
   
   	/**
   	 * s is a 9 character string, composed of 0s, 1s, and 2s.  Add 1 to the last char, adjusting
     * all the rest of the characters as necessary.
   	 * @param s - String to be added to
   	 * @return adjusted String
   	 */
      private static String addOne(String s) {
    	  
      boolean carry = false;
      char ch[] = s.toCharArray();
      ch[ch.length-1] =  (char)((int)(ch[ch.length-1])+1);
      for (int n = ch.length-1; n >=0; n--) {
         if (carry) ch[n] = (char)((int)(ch[n])+1);
         if (ch[n] == '3') {
            carry = true;
            ch[n] = '0';
         }
         else
            carry = false;      
      }
      return new String(ch);
   }
   
   /**
    * fills the board with random? values
    * @return a board filled by the method
    */
   public static String[] fillValues() {
      String strBoard = "000000000";
      String[] values = new String[POSSIBILITIES];
      int index = 0;
      values[index++] = strBoard;
      while (!strBoard.equals("222222222") ) {
         strBoard = addOne(strBoard);
         values[index++] = strBoard;
      }
      return values;
   }
   
   /**
    * checks if there is a win diagonally along the board
    * @param board 2d char array that represents the board
    * @return boolean - true if win, false if not
    */
   private static boolean diagonalWin(char[][] board) {
   
     if ((board[0][0] == 'x' && board[1][1] == 'x' && board[2][2] == 'x') || 
         (board[0][0] == 'o' && board[1][1] == 'o' && board[2][2] == 'o')) {
         return true;
         }
     else
       if ((board[0][2] == 'x' && board[1][1] == 'x' && board[2][0] == 'x') ||
           (board[0][2] == 'o' && board[1][1] == 'o' && board[2][0] == 'o')) {
           return true;
        }
     return false;
   }
   
   /**
    * checks if there is a win across the rows of the board
    * @param board 2d char array that represents the board
    * @return boolean - true if win, false if not
    */
   private static boolean rowWin(char[][] board) {
      char ch;
      for (int r = 0; r < ROWS; r++) {
        ch = board[r][0];
        for (int c = 0; c < COLS; c++) 
          if (ch != board[r][c]) return false;
        } 
        return true;
      } 
   
   /**
    * checks if there is a win across the columns of the board the board
    * @param board 2d char array that represents the board
    * @return boolean - true if win, false if not
    */
   private static boolean colWin(char[][] board) {
      char ch;
      for (int c = 0; c < COLS; c++) {
        ch = board[0][c];
        for (int r = 0; r < ROWS; r++) 
          if (ch != board[r][c]) return false;
        } 
        return true;
      } 

   /**
    * checks if there is a win diagonally, horizontally, or vertical along the board
    * @param board 2d char array that represents the board
    * @return boolean - true if win, false if not
    */
   public static boolean isWin(char[][]b) {
     return valid(b) && (rowWin(b) || colWin(b) || diagonalWin(b));
     }
     
   /**
    * checks if there is a win in the given board string
    * @param s string that represents a board
    * @return boolean - true if win, false if not
    */
   public static boolean isWin(String s) {
     char[][] b = stringToBoard(s);
     return isWin(b);
     }
}
