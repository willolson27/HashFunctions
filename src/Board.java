import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * @author will olson (git : willolson27)
 * Assignment 7
 * Due March 31, 2018
 */
abstract class Board extends JFrame implements ActionListener {

	//fields
	private JButton buttons[][];
	private JLabel lblHashCode;
	private JLabel lblWinTitle;
	private String boardString = "";

	/**
	 * creates a new board object with the given string input
	 * @param title
	 */
	public Board(String title) {
		super(title);
		setupFrame();
	}

	/**
	 * sets the HashCode label to the given hashcode
	 * @param hashcode - int value assigned to the board string in a hash map
	 */
	public void setHashCodeLabel(int hashcode) {
		lblHashCode.setText("" + hashcode);
	}

	/**
	 * sets the winner label to winner or loser based on given string
	 * @param result - string representation of win or loss
	 */
	public void setWinnerLabel(String result) {
		lblWinTitle.setText(result);
	}

	/**
	 * sets the winner label to winner or loser based on given boolean
	 * @param result = boolean representation of win or loss (win = true loss = false)
	 */
	public void setWinnerLabel(boolean result) {
		if (result)
			setWinnerLabel("Winner");
		else
			setWinnerLabel("Loser");
	}

	// required because of abstract method, but not used
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	/**
	 * sets up the outer panel of the board
	 * @return - JPanel that represents the outer panel of the board
	 */
	JPanel setupPanelOne() {
		JPanel panel = new JPanel();
		JLabel lblHCTitle = new JLabel("Hash Code");
		;
		lblHashCode = new JLabel("" + myHashCode());
		lblWinTitle = new JLabel(""); // Will say either Winner or Loser
		setWinnerLabel(TicTacToe.isWin(boardString));
		panel.setLayout(new FlowLayout());
		panel.add(lblHCTitle);
		panel.add(lblHashCode);
		panel.add(lblWinTitle);
		return panel;
	}

	/**
	 * sets up the inner panel (button panel) of the board
	 * @return - JPanel representation of the Tic Tac Toe button panel
	 */
	private JPanel setupPanelTwo() {
		JButton b;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(TicTacToe.ROWS, TicTacToe.COLS));
		buttons = new JButton[TicTacToe.ROWS][TicTacToe.COLS];

		int count = 1;

		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();
				b = new JButton("" + ch);
				boardString += ch;
				b.setActionCommand("" + r + ", " + c);
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton btn = (JButton) e.getSource();
						btn.setText("" + cycleValue(btn.getText().charAt(0)));
						resetBoardString();
						setHashCodeLabel(myHashCode());
						setWinnerLabel(isWin());

					}
				});
				panel.add(b);
				buttons[r][c] = b;
			}

		return panel;
	}

	/**
	 * cycles a given char value ('x', 'o', or ' ') to the next value to the right
	 * @param ch char to be cycled to next
	 * @return 
	 */
	private static char cycleValue(char ch) {
		switch (ch) {
		case 'x':
			return 'o';
		case 'o':
			return ' ';
		default:
			return 'x';
		}
	}

	/**
	 * sets up the main frame of the board and adds the two panels to it
	 */
	private void setupFrame() {
		JPanel panel2 = new JPanel();

		// Setup Frame
		super.setSize(250, 200);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		// Setup Panels
		panel2 = setupPanelTwo(); // panelOne displays a value that requires panelTwo to be ready
		super.add(setupPanelOne());
		super.add(panel2);

		super.setVisible(true);
	}

	/**
	 * gives a random char value between ('x' 'o' and ' ')
	 * @return randomized char value from the three Tic-Tac-Toe possibliites
	 */
	private char randomXO() {
		int rnd = (int) (Math.random() * TicTacToe.CHAR_POSSIBILITIES);
		switch (rnd) {
		case 1:
			return 'x';
		case 2:
			return 'o';
		default:
			return ' ';
		}
	}

	/**
     * creates a hash code index for this instance of the board
	 * @return hash code index for this instance of TTT board
	 */
	abstract int myHashCode();

	abstract boolean isWin(String s);

	abstract boolean isWin();

	/**
	 * Returns the char at the given coordinates from this board
	 * @param row row coordinate for the requested char value
	 * @param col column coordinate for the requested char value
	 * @return char value requested at the given coordinated
	 */
	public char charAt(int row, int col) {
		String value = buttons[row][col].getText();
		if (value.length() > 0)
			return value.charAt(0);
		else
			return '*';
	}
   
	/**
	 * Returns the char at the given coordinates from a given string
	 * @param row row coordinate for the requested char value
	 * @param col column coordinate for the requested char value
	 * @return char value requested at the given coordinated
	 */
   public char charAt(String s, int row, int col) {
     int pos = row * TicTacToe.COLS + col;
     if (s.length() >= pos)
       return s.charAt(pos);
     else
       return '*';   
   }

   /**
    * sets the board display to a given string
    * @param s string to set the board display to
    */
	public void show(String s) {
		int pos = 0;
		String letter;
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = s.charAt(pos);
				switch (ch) {
				case '1':
					letter = "x";
					break;
				case '2':
					letter = "o";
					break;
				case '0':
					letter = " ";
					break;
				default:
					letter = "" + ch;
				}
				buttons[r][c].setText(letter);
				pos++;
			}
	}

	/**
	 * resets the tic tac toe board to its empty state (nothing in any buttons)
	 */
	public void resetBoardString() {
   boardString = "";
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				boardString += buttons[r][c].getText();
			}
	}

	/**
	 * sets the board string of this tic tac toe board to a given string
	 * @param s String to set the tic tac toe board to
	 */
	public void setBoardString(String s) {
		boardString = s;
		show(s);
	}

	/**
	 * retrieves the board string for this tic tac toe board
	 * @return String representation of this tic tac toe board
	 */
	public String getBoardString() {
		return boardString;
	}

	/**
	 * sets the board instance to display a random tictactoe string
	 */
	public void displayRandomString() {
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();
				boardString += ch;
				buttons[r][c].setText("" + ch);
			}
		setHashCodeLabel(myHashCode());
		setWinnerLabel(isWin());
	}

}