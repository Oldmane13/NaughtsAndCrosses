
public class BoardStatesAndActions {

	public static void gameOver(String s) {
		theBoard.setStatus(s);
		theBoard.setButtonsEnabled(false);
		theBoard.isPlay = false;
	}
	
	public static void click(int i,int j) {
		if(theBoard.game.getBoardValue(i,j)==CPUMoveset.EMPTY) {
			theBoard.buttons[i][j].setText(theBoard.chars[theBoard.human]);
			theBoard.game.setBoardValue(i,j,theBoard.human);
			theBoard.checkState();
			System.out.println("Computer has done its turn.");
			theBoard.computerTurn();

		}
	}
	
	
}
