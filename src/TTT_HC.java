import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TTT_HC extends Board{
	
	private final static String mainInput = "TicTacToeWinners.txt";
	private ArrayList<TTT>[] winners;
	private final int size = 10000;
	private final static String testInput = "TTT_Tests.txt";
	private final static String secondInput = "ALLTTT.txt";

	public TTT_HC(String s) {
		super(s);
		
	    BufferedReader inputFile = null;	   
		
		try {
		   	inputFile = new BufferedReader(new FileReader(mainInput), 1024);
		   }
		   catch (FileNotFoundException e) {
		   	System.out.println(ERROR);
		   	System.exit(0);
		   }
	    winners = new ArrayList[size];
	    String line = "";
		int index = 0;
	   try {
		  while ((line = inputFile.readLine()) != null)
		   {
			 this.setBoardString(line);
		     index = tttHashCode();
		     if (winners[index] == null) {
		    	 winners[index] = new ArrayList<TTT>();
		    	 winners[index].add(new TTT(line, true));
		     }
		     else
		    	 winners[index].add(new TTT(line, true));
		   }
	   } 
	   catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   } 
		
		try {
		   	inputFile = new BufferedReader(new FileReader(secondInput), 1024);
		   }
		   catch (FileNotFoundException e) {
		   	System.out.println(ERROR);
		   	System.exit(0);
		   }

	   	  
	   try {
		  while ((line = inputFile.readLine()) != null)
		   {
			 this.setBoardString(line);
		     index = tttHashCode();
		     if (winners[index] == null) {
		    	 winners[index] = new ArrayList<TTT>();
		    	 winners[index].add(new TTT(line, false));
		     }
		     else
		    	 winners[index].add(new TTT(line, false));
		   }  
	   } 
	   catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   } 
	}
	@Override
	public int myHashCode() {
		return tttHashCode();
	}
	
	public int tttHashCode() {
		
		int sum = 0;

		String s = this.getBoardString();
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'x')
				sum += Math.pow(2, i);
			else if (s.charAt(i) == 'o')
				sum += Math.pow(3, i);
			else
				sum += 0;	
		}
		
		return sum;
	}


/*	public boolean isWin(String s) {
		  
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
	    	
    } */
	    
	@Override
    public boolean isWin() {
		ArrayList<TTT> temp = winners[myHashCode()];
		boolean win = false;
		for (TTT tic : temp) {
			if (tic.getBoard().equals(this.getBoardString()))
				win = tic.getWin();
		}
				
	   	return win;
    }
	
	public static void main (String[] args) {
		
		TTT_HC board = new TTT_HC("Tic Tac Toe");
		BufferedReader inputFile = null;
		   
	   try {
	   	inputFile = new BufferedReader(new FileReader(mainInput), 1024);
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
	//		System.out.println(board.tttHashCode());
	//		System.out.println(board.getBoardString() + " " + board.tttHashCode() + " " + board.isWin());
	//		Thread.sleep(4000);
		   }
	   } catch (IOException e) {
		e.printStackTrace();
	   }  	
	   
	   
	   int index = 0;
	   for (ArrayList<TTT> list : board.winners) {
		   if (list != null)  
		   	for (TTT tic : list)
		   		System.out.println(tic.getBoard() + " " + tic.getWin() + " " + index);
		   index++;
	   }
		
		/*	String[] tests = {"xxooooxxx", "xoo  ox x", "xoxoxoxox"};
		for (String s : tests)
			System.out.println(s + " " + board.tttHashCode(s));
		*/
	}

	@Override
	boolean isWin(String s) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
}
