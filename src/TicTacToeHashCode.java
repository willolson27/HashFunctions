import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * 
 * @author will olson  (git: willolson27)
 * Assignment 7
 * Due MArch 27, 2018
 *
 */

//this one is done

public class TicTacToeHashCode extends Board {

//FIELDs
 boolean [] winners;  // True if the hash string that maps to this index is a winner, false otherwise
 final int size = 19683;   
 private final String mainInput = "TicTacToeWinners.txt";
 private final static String testInput = "TTT_Tests.txt";
 
  /**
   * Create a hash table of tic tac toe based on winner values taken from given file
   * @param s name of file to be read from
   */
  TicTacToeHashCode(String s) {
   super(s);
   
   BufferedReader inputFile = null;
   
   try {
   	inputFile = new BufferedReader(new FileReader(mainInput), 1024);
   }
   catch (FileNotFoundException e) {
   	System.out.println(ERROR);
   	System.exit(0);
   }

   winners = new boolean[size];
   String line = "";
   int index = 0;

   try {
	while ((line = inputFile.readLine()) != null)
	   {
		 this.setBoardString(line);
	     index = myHashCode();
	     winners[index] = true;
	   }
   } catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
   }
   for (int i = 0; i < winners.length; i++)
		if (winners[i] != true) {
			winners[i] = false; 
		}
  }
  
  /**
   * creates a hash code index for this instance of the board
   * @return hash code index for this instance of TTT board
   */
  @Override
    public int myHashCode() {

	  int[][] pows3 = new int [][] { {1, 3, 9}, {27, 81, 243}, {729, 2187, 6561}};
	  
	  char ch = super.charAt(0, 0);
	  int sum = 0;

	 
	  for (int r = 0; r < pows3.length; r++) {
		for (int c = 0; c < pows3[r].length; c++) {
			ch = super.charAt(r, c);
		
	  		if (ch == 'x')
	  			sum += pows3[r][c];
	  		else if (ch == 'o')
	  			sum += 2 * pows3[r][c];
	  	
		}
		
	  }
	  
      return sum;
			  
   }
  
   /**
    * checks if the given string is a winning tic tac toe game
    * @param s String representation of board to be checked
    * @return boolean - true if is win false if loss or invalid
    */
    public boolean isWin(String s) {
  
      int[][] pows3 = new int [][] { {1, 3, 9}, {27, 81, 243}, {729, 2187, 6561}};
  	  
  	  char ch = super.charAt(s, 0, 0);
  	  int sum = 0;

  	 
  	  for (int r = 0; r < pows3.length; r++) {
  		for (int c = 0; c < pows3[r].length; c++) {
  			ch = super.charAt(s, r, c);
  		
  	  		if (ch == 'x')
  	  			sum += pows3[r][c];
  	  		else if (ch == 'o')
  	  			sum += 2 * pows3[r][c];
  	  	
  		}
  		
  	  }
  	  
     return(winners[sum]);
    	
    }
    
    /**
     * checks if this instance of the board is a winning tic tac toe game
     * @return boolean - true if is win false if loss or invalid
     */
    @Override
    boolean isWin() {
    	return(winners[myHashCode()]);
    }
  
    /**
     * test this class by reading in a test file and checking if the winners are in the correct index
     * @param args - class args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode("Tic Tac Toe");
		
		BufferedReader inputFile = null;+	   try {
		   	inputFile = new BufferedReader(new FileReader(testInput), 1024);
		   }
		   catch (FileNotFoundException e) {
		   	System.out.println(ERROR);
		   	System.exit(0);
		   }
		   
		   String line = "";

		   try {
			while ((line = inputFile.readLine()) != null)
			   {
				board.setBoardString(line);
				board.setWinnerLabel(board.isWin());
				board.setHashCodeLabel(board.myHashCode());
				System.out.println(board.getBoardString() + " " + board.myHashCode() + " " + board.isWin());
				Thread.sleep(2000);
			   }
		   } catch (IOException e) {
			e.printStackTrace();
		   }  
		/*
		 while (true) {
		   board.displayRandomString();
		   Thread.sleep(4000);
		 }
		 */
	}
}


   
