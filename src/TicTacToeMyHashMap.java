import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author will olson (Git willolson27)
 * due date march 31 2018
 * Assignment 7
 *
 */
public class TicTacToeMyHashMap  {

	//Fields
	private static final String ERROR = "Error File not Found";
	private Map<String, Boolean> tttMap;
	private ArrayList<Integer> used = new ArrayList<Integer>();
	private int numCollisions = 0;
	private final static String testInput = "TTT_Tests.txt";
	private final String mainInput = "TicTacToeWinners.txt";
	private final String ARR_SIZE = "Array Size: ";
	private final String LOAD_F = "Load Factor: ";
	private final String COLLISION = "No. of Collisions: ";
	private final String AVG_CHAIN = "Average Chain Length: ";
	private final String MAX_CHAIN = "Max Chain Length: ";
	private final String NO_CHAINS = "Number of Chains: ";
	private final String TENTH = "Tenth: ";
	private final String QTR = "Quarter: ";
	private final String CAPACITY = "Capacity: ";



   /**
    * creates a new TicTacToe hash map from the winners file with a TTT object as key and true as value
    * uses the hashCode creates by will olson originally for the TTT_HC and TTT classes
    */
   TicTacToeMyHashMap() {
	   
	   tttMap = new HashMap<String, Boolean>();
	   
	   BufferedReader input = null;
	   
	   try {
		   	input = new BufferedReader(new FileReader(mainInput), 1024);

	   }
	   catch (FileNotFoundException e){
		   System.out.println(ERROR);
	   	   System.exit(0);  
	   }
	   
	   String line = "";
	 
	   int hash = 0;
	   int t = 0;

	   try {
		while ((line = input.readLine()) != null)
		   {
			 hash = line.hashCode();
			 t = hash + 0;
			 if (used.contains(t))
				 numCollisions++;
			 used.add(t);
			 tttMap.put(line, true);
		   }
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	   
	   
   }

   /**
    * This method uses reflect to investigate the objects inside the HashMap
    * @return the capacity of this hashmap
    * @throws NoSuchFieldException 
    * @throws IllegalAccessException
    */
   private int capacity() throws NoSuchFieldException, IllegalAccessException {
      Field tableField = HashMap.class.getDeclaredField("table");
      tableField.setAccessible(true);
      Object[] table = (Object[]) tableField.get(tttMap);
      return table == null ? 0 : table.length;   
   }
   
   /**
	 * print data on this instance of TicTacToeHashCode - number of collisions, number of chains etc
	 * @return -String representation of data on this class
	 */
	public String printResults(Object[] winners) { 
		
		String toReturn = "";
		ArrayList<Double> chains = new ArrayList<Double>();
		int maxChain = 0;
		double avgChain = 0;
		double numBuckets = 0;
		double numItems = 0;
		int[] tenths = new int[10];
		int[] fourths = new int[4];
		String[] nums = {"First", "Second", "Third", "Fourth", "Fifth", 
						"Sixth", "Seventh", "Eighth", "Ninth", "Tenth"};
		
		int l = winners.length;
		for (int i = 0; i < l; i++) {
			numBuckets++;
			numItems++; 
			fourths[i / (l / 4)]++;
		}
		
		numBuckets -= numCollisions;
		
	//	for (int j = 0; j < numBuckets; j++)
		
		int sum = 0;
		for (double i : chains)
			sum += i;
		if (chains.size() != 0)
		avgChain = (sum / chains.size());
		
//		toReturn += ARR_SIZE + winners.length + "\n";
		toReturn += LOAD_F + (numItems / numBuckets) + "\n";
		toReturn += COLLISION + numCollisions + "\n";
		toReturn += NO_CHAINS + chains.size() + "\n";
		toReturn += AVG_CHAIN + avgChain + "\n";
		toReturn += MAX_CHAIN + maxChain + "\n";
		
	
		for (int i = 0; i < tenths.length; i++)
			toReturn += nums[i] +  " " + TENTH + tenths[i] + "\n";
		for (int i = 0; i < fourths.length; i++)
			toReturn += nums[i] + " " + QTR + fourths[i] + "\n";		
				
		
		return toReturn;
		
		
	}
   
   /**
    * tests out this program by creating a new instance of the class and analyzing the map it creates
    * for how well the data is distributed, how many collisions there are, etc.
    * @param args - 
    * @throws java.io.FileNotFoundException
    * @throws NoSuchFieldException
    * @throws IllegalAccessException
    */
   public static void main(String[] args) throws java.io.FileNotFoundException,
                                              NoSuchFieldException, 
                                              IllegalAccessException {

      TicTacToeMyHashMap m = new TicTacToeMyHashMap();
  
      
      System.out.println(m.CAPACITY + m.capacity());
  
      Field tableField = HashMap.class.getDeclaredField("table");
    
      tableField.setAccessible(true);
      Object[] table = (Object[]) tableField.get(m.tttMap);
    
  
      
      System.out.println(m.printResults(table));
      
 
      
   }

}