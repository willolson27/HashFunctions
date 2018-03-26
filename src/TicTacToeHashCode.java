import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//TODO Make sure you remove all of the TODO comments from this file before turning itin

public class TicTacToeHashCode extends Board {

 boolean [] winners;  // True if the hash string that maps to this index is a winner, false otherwise
 final int size = 19683;   
 private final String mainInput = "TicTacToeWinners.txt";
 private final static String testInput = "TTT_Tests.txt";
 
  TicTacToeHashCode(String s) {
   super(s);
  // TODO Instantiate/fill winners array.  
   BufferedReader inputFile = null;
   
   try {
   	inputFile = new BufferedReader(new FileReader(mainInput), 1024);
   }
   catch (FileNotFoundException e) {
   	System.out.println(ERROR);
   	System.exit(0);
   }
 
   String[] testArr = new String[442];
   int[] testInts = new int[442];
   winners = new boolean[size];
   String line = "";
   int index = 0;
   int testIX = 0;
   try {
	while ((line = inputFile.readLine()) != null)
	   {
		 testArr[testIX] = line;
		 
		 this.setBoardString(line);
	     index = myHashCode();
	     testInts[testIX] = index;
	 //    System.out.println(index);
	     winners[index] = true;
	//     break;
	     testIX++;
	   }
   } catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
   }
   for (int i = 0; i < winners.length; i++)
		if (winners[i] != true) {
			winners[i] = false; 
		}
//   System.out.println("br");
   for (int i = 0; i < winners.length; i++) {
	//   System.out.println(testArr[i] + " " + testInts[i] + " " + isWin(testArr[i]));
	//   System.out.println(winners[i] + " " + i  );
   }		   
  }
  
  // TODO - write the myHashCode function.  It must create a unique hashcode for all of the 
  //   possible values the game board (3 ^ 9) and it MUST use the super.charAt(row, col) function
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
//	  System.out.println(sum);
      return sum;
		
			  
	
   }
   
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
    
    @Override
    boolean isWin() {
    	return(winners[myHashCode()]);
    }
  
    public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode("Tic Tac Toe");
		
		BufferedReader inputFile = null;
		   
		   try {
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
				Thread.sleep(4000);
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


   
