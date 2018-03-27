import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 
 * @author Will Olson (git: willolson27)
 *
 */
public class TTT_HC extends Board{
	
	//FIELDS
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

	/**
	 * fills an array of winner values based on a given file input
	 * @param s - name of input file
	 */
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
	
	/**
	 * calculates a hash index for this instance of the TTT board
	 * @return Hash Code for the ttt board
	 */
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


	/**
	 * determines whether a given TTT string is a win or not
	 * @return boolean - true if is win false if not
	 */
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
	    
	/**
	 * determines whether this instance of the TTT board is a win or not
	 * @return boolean - true if is win false if not
	 */
	@Override
    public boolean isWin() {
		ArrayList<TTT> temp = winners[myHashCode()];
		boolean win = false;
		if (temp != null)
			for (TTT tic : temp) {
				if (tic.getBoard().equals(this.getBoardString()))
					win = tic.getWin();
		}
				
	   	return win;
    }
	
	/**
	 * print data on this instance of TTT_HC - number of collisions, number of chains etc
	 * @return -String representation of data on this class
	 */
	public String printResults() { 
		
		String toReturn = "";
		int numCollisions = 0;
		ArrayList<Double> chains = new ArrayList<Double>();
		int maxChain = 0;
		double avgChain = 0;
		int numBuckets = 0;
		int numItems = 0;
		
		for (int i = 0; i < this.winners.length; i++) {
			if (winners[i] != null)
				if (winners[i].size() == 1) {
					numBuckets++;
					numItems++; 
				}
				else {
					chains.add((double) winners[i].size());
					if (winners[i].size() > maxChain)
						maxChain = winners[i].size();
					numItems += winners[i].size();
					numBuckets++;
					numCollisions += (winners[i].size() - 1);
				}	
			
		}
		
		int sum = 0;
		for (double i : chains)
			sum += i;
		avgChain = (sum / chains.size());
		
		toReturn += ARR_SIZE + this.winners.length + "\n";
		toReturn += LOAD_F + (numItems / numBuckets) + "\n";
		toReturn += COLLISION + numCollisions + "\n";
		toReturn += NO_CHAINS + chains.size() + "\n";
		toReturn += AVG_CHAIN + avgChain + "\n";
		toReturn += MAX_CHAIN + maxChain + "\n";
		
		
		
		return toReturn;
		
		
	}
	
	public static void main (String[] args) throws InterruptedException {
		
		TTT_HC board = new TTT_HC("Tic Tac Toe");
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
			boolean w = board.isWin();
			board.setWinnerLabel(board.isWin());
			board.setHashCodeLabel(board.myHashCode());
	//		System.out.println(board.tttHashCode());
	//		System.out.println(board.getBoardString() + " " + board.tttHashCode() + " " + w);
		//	Thread.sleep(4000);
		   }
	   } catch (IOException e) {
		e.printStackTrace();
	   }  	
	   System.out.println(board.printResults());

	   }

	}

