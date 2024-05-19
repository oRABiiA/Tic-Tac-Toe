package game_src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
	int boardWidth = 600;
	int boardHeight = 650; // 50px for the text panel on top

	JFrame frame = new JFrame("Tic-Tac-Toe");
	JLabel textLabel = new JLabel();
	JPanel textPanel = new JPanel(); // a panel for the text
	JPanel boardPanel = new JPanel(); // a panel for the play board

	JButton[][] board = new JButton[3][3]; // make a 3x3 grid button
	String playerX = "X";
	String playerO = "O";
	String currentPlayer = playerX; // the first player is always the player that starts with "X"

	boolean gameOver = false;

	public TicTacToe() {
		frame.setVisible(true);
		frame.setSize(boardWidth, boardHeight);
		frame.setLocationRelativeTo(null); // opens the window at the center of the screen
		frame.setResizable(false); // makes the window non resizable
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exits the application when close window is pressed
		frame.setLayout(new BorderLayout()); // create a layout for the window

		// label settings
		textLabel.setBackground(Color.darkGray);
		textLabel.setForeground(Color.white);
		textLabel.setFont(new Font("Arial", Font.BOLD, 50));
		textLabel.setHorizontalAlignment(JLabel.CENTER); // sets the text in the center
		textLabel.setText("Tic-Tac-Toe");
		textLabel.setOpaque(true);

		// panel settings
		textPanel.setLayout(new BorderLayout());
		textPanel.add(textLabel);

		frame.add(textPanel, BorderLayout.NORTH); // set the panel to the high-end of the window

		boardPanel.setLayout(new GridLayout(3, 3)); // makes the panel a 3x3 grid
		boardPanel.setBackground(Color.darkGray);

		frame.add(boardPanel);

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				JButton tile = new JButton();
				board[row][col] = tile;
				boardPanel.add(tile);
				tile.setBackground(Color.darkGray);
				tile.setForeground(Color.white);
				tile.setFont(new Font("Arial", Font.BOLD, 120));
				tile.setFocusable(false); // no keyboard events

				tile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (gameOver) {
							return;
						}
						JButton tile = (JButton) event.getSource();
						if (tile.getText().equals("")) { // update the tile only if not pressed
							tile.setText(currentPlayer); // set the text to who selected the tile
							checkWinner();
							if (!gameOver) {
								currentPlayer = currentPlayer == playerX ? playerO : playerX; // update the next player
								textLabel.setText(currentPlayer + "'s turn");
							}

						}

					}
				});

			}
		}

	}

	private void checkWinner() { // checks if someone got a win condition on the board
		// check horizontal win
		for (int row = 0; row < 3; row++) {
			if (board[row][0].getText().equals(""))
				continue;
			if (board[row][1].getText().equals(""))
				continue;
			if (board[row][2].getText().equals(""))
				continue;

			if (board[row][0].getText() == board[row][1].getText()
					&& board[row][1].getText() == board[row][2].getText()) {
					gameOver = true;
					return;
			}
		}
		
		

	}

}
