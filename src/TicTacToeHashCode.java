//TODO Make sure you remove all of the TODO comments from this file before turning itin

public class TicTacToeHashCode extends Board {

 boolean [] winners;  // True if the hash string that maps to this index is a winner, false otherwise
    
  TicTacToeHashCode(String s) {
   super(s);
  // TODO Instantiate/fill winners array.  
  }
  
  // TODO - write the myHashCode function.  It must create a unique hashcode for all of the 
  //   possible values the game board (3 ^ 9) and it MUST use the super.charAt(row, col) function
  @Override
    public int myHashCode() {

	  int[][] pows3 = new int [][] { {1, 3, 9}, {27, 81, 243}, {729, 2187, 6561}};
	  
	  char ch = ' ';
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
   
    public boolean isWin(String s) {
    // return the value in the winner array for the hash chode of the board string sent in.
    return true;
    }
  
   public static void main(String[] args) throws InterruptedException {
      TicTacToeHashCode board = new TicTacToeHashCode ("Tic Tac Toe");
      while (true) {
      
       //TODO this line no longer works
       //  String currentBoard = board.boardValues[(int)(Math.random()* board.boardValues.length)];
         
         board.displayRandomString();
         board.setHashCodeLabel(board.myHashCode());
         // TODO Update this line to call your isWin method.
         board.setWinner(TicTacToe.isWin(currentBoard));
         
         Thread.sleep(4000);      
      }
   }
 }  
