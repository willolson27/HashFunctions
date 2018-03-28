import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TicTacToeHashMap  {
	
	
	private static final String ERROR = "Error File not Found";
	private Map<String, Boolean> tttMap;
	private final static String testInput = "TTT_Tests.txt";

// TODO Define a hash map to store the winning strings as Key and true as Value

   TicTacToeHashMap() {
	   tttMap = new HashMap<String, Boolean>();
   }

// TODO This method uses reflect to investigate the objects inside the HashMap
// You should be able to update this with your information and determine 
// Information about capacity (different than size()) and what is stored in the cells

   private int capacity() throws NoSuchFieldException, IllegalAccessException {
      Field tableField = HashMap.class.getDeclaredField("table");
      tableField.setAccessible(true);
      Object[] table = (Object[]) tableField.get(tttMap);
      return table == null ? 0 : table.length;   
   }
   
   // TODO using the same code to get the table of entries as in the capacity method,
   // create a method that will evaluate the table as directed in the assignment.
   // note - if an entry is not null, then it has a value, it may have more than one value
   // see if you can determine how many values it has.  Using the debugger will assist.

   public static void main(String[] args) throws java.io.FileNotFoundException,
                                              NoSuchFieldException, 
                                              IllegalAccessException {

      TicTacToeHashMap m = new TicTacToeHashMap();
  
  // TODO read in and store the strings in your hashmap, then close the file
      BufferedReader inputFile = null;
	   
	   try {
	   	inputFile = new BufferedReader(new FileReader(testInput), 1024);
	   }
	  catch (FileNotFoundException e) {
	   	System.out.println(ERROR);
	   	System.exit(0);
	   }
		   
	   String line = "";
	   int hash = 0;

	   
	   try {
		while ((line = inputFile.readLine()) != null)
		   {
			hash = line.hashCode();
			if (m.tttMap.get(hash) != null) 
				m.tttMap.put(line, new TTT(line,false));
		   }
	   } catch (IOException e) {
		e.printStackTrace();
	   }  	
  // TODO print out the capacity using the capacity() method
      
      System.out.println(m.capacity());
  // TODO print out the other analytical statistics as required in the assignment
  
   }

}