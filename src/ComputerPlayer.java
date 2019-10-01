//Test Comment
public class ComputerPlayer {
	private int order;
	private String letter; // CPU Letter
	private int row;
	private int column;
	private String realPlayer;

	public ComputerPlayer(int order, String letter, String realPlayer) {
		this.letter = letter;
		this.order = order;
		this.realPlayer = realPlayer;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	// Return 1, -1 based on win. Otherwise, returns 10
	public int currentScore(TTTBoard tttBoard) {
		if (TTTBoard.evaluateXWin(tttBoard)) {
			if (letter.equals("X")) {
				return 1;
			}
			return -1;
		} else if (TTTBoard.evaluateOWin(tttBoard)) {
			if (letter.equals("O")) {
				return 1;
			}
			return -1;
		} else {
			return 0;
		}
	}

	public int minimax(TTTBoard tttBoard, int currentDepth, boolean minimumTurn) {
		// minimum turn is true if it's the human player's turn because the CPU
		// will want to minimize human score

		// Check the current score
		int score = currentScore(tttBoard);
		// If there is no winner
		if (gameOverNoWin(tttBoard)) {
			return 0;
		} else if (score == -1 || score == 1) {
			return score;
		}

		if (minimumTurn) {
			int bestCase = -100;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (tttBoard.getBoard()[i][j] == null) {
						String[][] changeBoard = tttBoard.getBoard();
						changeBoard[i][j] = letter;
						tttBoard.setBoard(changeBoard);

						bestCase = Math.max(bestCase, minimax(tttBoard, currentDepth + 1, !minimumTurn));

						String[][] resetBoard = tttBoard.getBoard();
						resetBoard[i][j] = null;
						tttBoard.setBoard(resetBoard);
					}
				}
			}
			return bestCase;
		} else {
			int bestCase = 100;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (tttBoard.getBoard()[i][j] == null) {
						String[][] changeBoard = tttBoard.getBoard();
						changeBoard[i][j] = realPlayer;
						tttBoard.setBoard(changeBoard);

						bestCase = Math.min(bestCase, minimax(tttBoard, currentDepth + 1, !minimumTurn));

						String[][] resetBoard = tttBoard.getBoard();
						resetBoard[i][j] = null;
						tttBoard.setBoard(resetBoard);
					}
				}
			}
			return bestCase;
		}
	}

	//Finds the best move
	public int bestMove(TTTBoard tttBoard) {
		int bestMoveScore = -10000;
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (tttBoard.getBoard()[r][c] == null) {
					tttBoard.getBoard()[r][c] = letter;
					int currentMove = minimax(tttBoard, 0, false);
					tttBoard.getBoard()[r][c] = null;
					// turning it back to null in order to evaluate if it was
					// the right move to make
					if (currentMove > bestMoveScore) {
						bestMoveScore = currentMove;
						row = r;
						column = c;
					}
				}
			}
		}
		//System.out.format("Row: %d. Column %d. Spot: %d.", row, column, rowAndColumnToNumber(row, column));
		return rowAndColumnToNumber(row, column);
	}

	public boolean gameOverNoWin(TTTBoard tttBoard) {
		if (TTTBoard.isFull(tttBoard) && !TTTBoard.evaluateXWin(tttBoard) && !TTTBoard.evaluateOWin(tttBoard)) {
			return true;
		}
		return false;
	}

	public int rowAndColumnToNumber(int row, int column) {
		if (row == 0 && column == 0) {
			return 1;
		}
		if (row == 0 && column == 1) {
			return 2;
		}
		if (row == 0 && column == 2) {
			return 3;
		}
		if (row == 1 && column == 0) {
			return 4;
		}
		if (row == 1 && column == 1) {
			return 5;
		}
		if (row == 1 && column == 2) {
			return 6;
		}
		if (row == 2 && column == 0) {
			return 7;
		}
		if (row == 2 && column == 1) {
			return 8;
		}
		if (row == 2 && column == 2) {
			return 9;
		}
		return 0;
	}

}
