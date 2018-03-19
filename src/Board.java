import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

abstract class Board extends JFrame implements ActionListener {

   private JButton buttons[][];
   private JLabel lblHashCode;
   private JLabel lblWinTitle;
   
   private String boardString = "";
   
   public Board(String title) {
      super(title);
      setupFrame();
   }
   
   // TODO renamed from setHashCode to setHashCodeLabel
   public void setHashCodeLabel(int hashcode) {
      lblHashCode.setText("" + hashcode);
   }
   
   // TODO renamed from setWinner to setWinnerLabel
   public void setWinnerLabel(String result) {
      lblWinTitle.setText(result);
   }
   
      public void setWinnerLabel(boolean result) {
      if (result)
         setWinnerLabel("Winner");
      else
         setWinnerLabel("Loser");
   }
    //  required because of abstract method, but not used   
   @Override
   public void actionPerformed(ActionEvent e) { }

   JPanel setupPanelOne() {
      JPanel panel = new JPanel();
      JLabel lblHCTitle = new JLabel("Hash Code");;
      lblHashCode = new JLabel("" + myHashCode());
      lblWinTitle = new JLabel(""); // Will say either Winner or Loser 
      setWinnerLabel(TicTacToe.isWin(boardString));
      panel.setLayout(new FlowLayout());    
      panel.add(lblHCTitle);
      panel.add(lblHashCode);  
      panel.add(lblWinTitle);  
      return panel;
   }

   private JPanel setupPanelTwo() {
      JButton b;
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(TicTacToe.ROWS,TicTacToe.COLS));
      buttons = new JButton[TicTacToe.ROWS][TicTacToe.COLS];
       
      int count = 1;
      
      for (int r = 0; r < TicTacToe.ROWS; r++) 
         for (int c = 0; c < TicTacToe.COLS; c++) {
            char ch = randomXO();           
            b = new JButton("" + ch);
            boardString += ch;
            b.setActionCommand("" + r + ", " + c);
            b.addActionListener(
               new ActionListener(){  
                  public void actionPerformed(ActionEvent e){  
                     JButton btn = (JButton) e.getSource();
                     btn.setText("" + cycleValue(btn.getText().charAt(0)));
                     resetBoardString();
                     lblHashCode.setText("" + myHashCode());
                     //TODO - should this set the winner label text
                     
                  }  
               });              
            panel.add(b);
            buttons[r][c] = b;           
         }
   
      return panel;
   }
   
   private static char cycleValue(char ch) {
      switch (ch) {
         case 'x' : 
            return 'o';
         case 'o' : 
            return ' ';
         default  : 
            return 'x';
      }
   }
   
   private void setupFrame() {
      JPanel panel2 = new JPanel();
              
      // Setup Frame
      super.setSize(250, 200);  
      super.setLocationRelativeTo(null);  
      super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      super.setLayout(new BoxLayout(getContentPane(),BoxLayout.PAGE_AXIS));  
      
      // Setup Panels
      panel2 = setupPanelTwo();  //panelOne displays a value that requires panelTwo to be ready
      super.add(setupPanelOne());     
      super.add(panel2);  
         
      super.setVisible(true);  
   }

   private char randomXO() {
      int rnd = (int) (Math.random()*TicTacToe.CHAR_POSSIBILITIES);
      switch(rnd) {
         case 1 : 
            return 'x';
         case 2 : 
            return 'o';
         default: 
            return ' ';
      }
   }
   
   abstract int myHashCode();
   abstract boolean isWin(String s);

   public char charAt(int row, int col) {
      String value = buttons[row][col].getText();
      if (value.length() > 0)
         return value.charAt(0);
      else
         return '*';
   }
   
   public void show(String s) {
      int pos = 0;
      String letter;
      for (int r = 0; r < TicTacToe.ROWS; r++)
         for (int c = 0; c < TicTacToe.COLS; c++){
            char ch = s.charAt(pos);
            switch (ch) {
               case '1' : letter = "x"; 
                  break;
               case '2' : letter = "o"; 
                  break;
               case '0'  : letter = " "; 
                  break;
               default : letter = "" + ch;                                                                                                                                                                                                                                                                                        xx: letter = " ";
            }       
            buttons[r][c].setText(letter);
            ch++;
         }
   }
   //TODO Added this method
   public void resetBoardString() {
      for (int r = 0; r < TicTacToe.ROWS; r++)
         for (int c = 0; c < TicTacToe.COLS; c++){
            boardString += buttons[r][c].getText();
         }
   }
   // TODO Added this Method
   public void setBoardString(String s) {
      boardString = s;
      show(s);
   }
   
   public String getBoardString(String s) {
     return boardString;
   }
   // TODO Added this message
   public void displayRandomString() {
      for (int r = 0; r < TicTacToe.ROWS; r++) 
         for (int c = 0; c < TicTacToe.COLS; c++) {
            char ch = randomXO();           
            boardString += ch;
            buttons[r][c].setText(""+ch);
         }
      setHashCodeLabel(myHashCode());

     setWinnerLabel(isWin(boardString));
     
   }
   
   
}