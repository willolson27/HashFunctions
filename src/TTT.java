
public class TTT {

	private final String boardString;
	private final boolean isWin;
	
	public TTT(String s, boolean b) {
		boardString = s;
		isWin = b;
	}
	
	public String getBoard() {
		return boardString;
	}
	
	public boolean getWin() {
		return isWin;
	}
	
}
