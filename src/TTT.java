/**
 * 
 * @author will olson (git willolson27)
 * due marhc 31
 * assingment 7
 *
 */
public class TTT {

	//Fields
	private final String boardString;
	private final boolean isWin;
	
	/**
	 * Constructor for a new TTT game, based on a given board string and boolean win state
	 * @param s String that the board is to be set to
	 * @param b win state of the board
	 */
	public TTT(String s, boolean b) {
		boardString = s;
		isWin = b;
	}
	
	/**
	 * Constructor for a new TTT game, based on a given board string
	 * @param s String that the board is to be set to
	 */
	public TTT(String s) {
		isWin = true;
		boardString = s;
	}
	
	/**
	 * retrieves the board string for this instance of the TTT class
	 * @return board string for this class
	 */
	public String getBoardString() {
		return boardString;
	}
	
	/**
	 * returns the win state for this TTT
	 * @return boolean true if win false if not
	 */
	public boolean getWin() {
		return isWin;
	}
	
	/**
	 * creates a hashCode for this TTT - uses a custom algorithm instead of java's lame one
	 * @return the HashCode for this TTT
	 */
	@Override
	public int hashCode() {
		
		int sum = 0;

		String s = this.getBoardString();
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'x')
				sum += Math.pow(2, i);
			else if (s.charAt(i) == 'o')
				sum += i * 3;
			else
				sum += 0;	
		} 
		
		return sum;
	}
	
}
