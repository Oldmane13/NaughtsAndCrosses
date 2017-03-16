import javax.swing.JOptionPane;

public class sortBeginner {

	public static void humanBegins() {
		theBoard.game = new CPUMoveset();
		theBoard.human = CPUMoveset.ONE;
		theBoard.computer = CPUMoveset.TWO;
		theBoard.setStatus("Prove yourself!");
		theBoard.setButtonsEnabled(true);
		theBoard.isPlay = true;
	}
	
	public static void computerBegins(){
		theBoard.game = new CPUMoveset();
		theBoard.human = CPUMoveset.TWO;
		theBoard.computer = CPUMoveset.ONE;
		theBoard.setStatus("Prove yourself!");
		theBoard.isPlay = true;
		theBoard.setButtonsEnabled(true);
	}
	
	public static void whoBegins(){

		new theBoard().setVisible(true);
		int message = JOptionPane.showConfirmDialog(null, "Would you like to begin?","Confirmation",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (message == JOptionPane.YES_OPTION){
			sortBeginner.humanBegins();

		}else if (message == JOptionPane.NO_OPTION){
			System.out.println("Computer has done its turn");
			System.out.println("You're up");
			sortBeginner.computerBegins();
			int []pos = theBoard.game.nextMove(theBoard.computer);
			if(pos!=null) {
				int i = pos[0];
				int j = pos[1];
				theBoard.buttons[i][j].setText(theBoard.chars[theBoard.computer]);
				theBoard.game.setBoardValue(i,j,theBoard.computer);

			}
		}
	}
	
}
