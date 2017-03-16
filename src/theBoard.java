import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class theBoard extends JFrame implements ActionListener {

	public static JButton [][]buttons = new JButton[3][3];
	public JButton playButton = new JButton("Play");
	public static JLabel statusLabel = new JLabel("Let's play a game!");
	public static CPUMoveset game = null;
	public static int human = 0;
	public static int computer = 0;
	public static boolean isPlay = false;
	public static String []chars=new String[]{".","X","O"};

	static void setStatus(String s) {
		statusLabel.setText(s);
	}

	public static void setButtonsEnabled(boolean enabled) {
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++) {
				buttons[i][j].setEnabled(enabled);
				if(enabled) buttons[i][j].setText(".");
			}
	}

	public theBoard(){

		setTitle("Naughts and Crosses");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		JPanel centerPanel = new JPanel(new GridLayout(3,3));
		Font font = new Font("Arial",Font.BOLD, 50);

		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++) {
				buttons[i][j] = new JButton(".");
				buttons[i][j].setFont(font);
				buttons[i][j].addActionListener(this);		
				centerPanel.add(buttons[i][j]);
			}

		playButton.addActionListener(this);

		JPanel northPanel = new JPanel();
		northPanel.add(statusLabel);
		
		JPanel southPanel = new JPanel();
		southPanel.add(playButton);
		setButtonsEnabled(false);

		add(northPanel, "North");
		add(centerPanel,"Center");
		add(southPanel, "South");

		setSize(450,450);
		setLocationRelativeTo(null);
	}

	public static void main(String []args) {
		sortBeginner.whoBegins();
	}

	public static void computerTurn() {
		if(!isPlay)
			return;

		int []pos = game.nextMove(computer);
		if(pos!=null) {
			int i = pos[0];
			int j = pos[1];
			buttons[i][j].setText(chars[computer]);
			game.setBoardValue(i,j,computer);
			System.out.println("You're up");
		}
		checkState();
	}

	

	public static void checkState() {
		
		if(game.isWin(human)) {
			BoardStatesAndActions.gameOver("Humanity wins!");
			JOptionPane.showMessageDialog(null,"Congratulations! Thanks for playing!");
		}
		
		if(game.isWin(computer)) {
			BoardStatesAndActions.gameOver("Computers rule over humans this time!");
			JOptionPane.showMessageDialog(null,"oops! Thanks for playing!");
		}
		if(game.nextMove(human)==null && game.nextMove(computer)==null) {
			BoardStatesAndActions.gameOver("Draw!");
			JOptionPane.showMessageDialog(null,"Draw! Thanks for playing!");
		}
	}

	public void click(int i,int j) {
		if(game.getBoardValue(i,j)==CPUMoveset.EMPTY) {
			buttons[i][j].setText(chars[human]);
			game.setBoardValue(i,j,human);
			checkState();
			System.out.println("Computer has done its turn.");
			computerTurn();

		}
	}

	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==playButton) {
			setVisible(false);
			sortBeginner.humanBegins();
		}else {
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					if(event.getSource()==buttons[i][j])
						click(i,j);
		}
	}
}
