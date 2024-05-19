package game_src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
	int boardWidth = 600;
	int boardHeight = 600 + 50 + 50; // 50px for the text panel on top + 50px for the restart button

	JFrame frame = new JFrame("Tic-Tac-Toe");
	JLabel textLabel = new JLabel();
	JPanel textPanel = new JPanel(); // a panel for the text
	JPanel boardPanel = new JPanel(); // a panel for the play board
	JPanel restartGame = new JPanel(); // a panel for the restart button

	JButton[][] board = new JButton[3][3]; // make a 3x3 grid button
	JButton restartBtn = new JButton();

	String playerX = "X";
	String playerO = "O";
	String currentPlayer = playerX; // the first player is always the player that starts with "X"

	boolean gameOver = false;
	int turns = 0;

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

		frame.add(boardPanel, BorderLayout.CENTER);

		restartBtn.setText("Restart");
		restartBtn.setFont(new Font("Arial", Font.BOLD, 15));
		restartBtn.setBackground(Color.white);
		restartBtn.setForeground(Color.darkGray);
		restartBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameOver = false;
				turns = 0;
				currentPlayer = playerX;
				textLabel.setText("Tic-Tac-Toe");
				textLabel.setBackground(Color.darkGray);
				textLabel.setForeground(Color.white);

				for (int row = 0; row < 3; row++) {
					for (int col = 0; col < 3; col++) {
						board[row][col].setText("");
						board[row][col].setBackground(Color.darkGray);
						board[row][col].setForeground(Color.white);
					}
				}
			}

		});
		restartGame.add(restartBtn);
		restartGame.setBackground(Color.darkGray);

		frame.add(restartGame, BorderLayout.SOUTH);

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
						JButton tilePressed = (JButton) event.getSource();
						if (tilePressed.getText().equals("")) { // update the tile only if not pressed
							tilePressed.setText(currentPlayer); // set the text to who selected the tile
							turns++;
							checkWinner();
							// nobody won, end the game as a tie
							if (turns == 9 && !gameOver) {
								setTie(board);
							}
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
//			if (board[row][1].getText().equals(""))
//				continue;
//			if (board[row][2].getText().equals(""))
//				continue;

			if (board[row][0].getText() == board[row][1].getText()
					&& board[row][1].getText() == board[row][2].getText()) {
				for (int i = 0; i < 3; i++) {
					setWinner(board[row][i]);
				}
				gameOver = true;
				return;
			}
		}

		// check vertical win
		for (int col = 0; col < 3; col++) {
			if (board[0][col].getText().equals(""))
				continue;
			if (board[0][col].getText() == board[1][col].getText()
					&& board[1][col].getText() == board[2][col].getText()) {
				for (int i = 0; i < 3; i++) {
					setWinner(board[i][col]);
				}
				gameOver = true;
				return;
			}
		}

		// check diagonal win
		for (int k = 0; k < 3; k++) {
			if (board[k][k].getText().equals("")) {
				continue;
			}
			if (board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText()) {
				for (int i = 0; i < 3; i++) {
					setWinner(board[i][i]);
				}
				gameOver = true;
				return;
			}

		}

		// check second diagonal win
		for (int y = 0; y < 3; y++) {
			if (board[y][2 - y].getText().equals("")) {
				continue;
			}
			if (board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText()) {
				for (int i = 0; i < 3; i++) {
					setWinner(board[i][2 - i]);
				}
				gameOver = true;
				return;
			}
		}

	}

	private void setWinner(JButton tile) {
		// changes the tiles color corresponding to the winning tiles
		// changes the text label to the winner too
		tile.setForeground(Color.GREEN);
		tile.setBackground(Color.GRAY);

		textLabel.setText(currentPlayer + " Won!");
	}

	private void setTie(JButton board[][]) {
		// changes the tiles color tie
		// changes the text label to tie

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col].setForeground(Color.ORANGE);
				board[row][col].setBackground(Color.gray);

			}
		}
		textLabel.setText("It's a tie!");
		textLabel.setBackground(Color.gray);
		textLabel.setForeground(Color.ORANGE);

		gameOver = true;
		return;
	}
}
