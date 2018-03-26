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
	private final String ARR_SIZE = "Array Size: ";
	private final String LOAD_F = "Load Factor: ";
	private final String COLLISION = "No. of Collisions: ";
	private final String AVG_CHAIN = "Average Chain Length: ";
	private final String MAX_CHAIN = "Max Chain Length: ";
	private final String NO_CHAINS = "Number of Chains: ";

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


	public boolean isWin(String s) {
		
		int sum = 0;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'x')
				sum += Math.pow(2, i);
			else if (s.charAt(i) == 'o')
				sum += Math.pow(3, i);
			else
				sum += 0;	
		}
		
		ArrayList<TTT> temp = winners[sum];
		boolean win = false;
		for (TTT tic : temp) {
			if (tic.getBoard().equals(this.getBoardString()))
				win = tic.getWin();
		}
				
	   	return win;
	  		
	    	
    } 
	    
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
	
	public String printResults() { 
		
		String toReturn = "";
		
		toReturn += ARR_SIZE + this.winners.length;
		
		
		
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
			System.out.println(board.getBoardString() + " " + board.tttHashCode() + " " + board.isWin());
	//		Thread.sleep(4000);
		   }
	   } catch (IOException e) {
		e.printStackTrace();
	   }  	
	   

	   }

	}

