import java.util.Random;

public class AdvComputerPlayer {
	private int order;
	private String letter; // CPU Letter
	private int row;
	private int column;
	private String realPlayer;
	private int currentZone = 0;

	public AdvComputerPlayer(int order, String letter, String realPlayer) {
		this.letter = letter;
		this.order = order;
		this.realPlayer = realPlayer;
	}

	public int getOrder() {
		return order;
	}

	public int getZone() {
		return currentZone;
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

	//Broken Method (Random Best Move)
	/*
	public int bestMove(AdvTTTBoard advTTTBoard, int zone) {
		String [][] board = getCompStringBoard(advTTTBoard, zone);
		
		Random random = new Random();
		int rowRand = random.nextInt(3);
		int columnRand = random.nextInt(3);
		
		if (board[rowRand][columnRand] != null) {
			row = rowRand;
			column = columnRand;
			
			currentZone = zone;
			return rowAndColumnToNumber(row, column);
		} else {
			while (board[rowRand][columnRand] == null) {
				rowRand = random.nextInt(3);
				columnRand = random.nextInt(3);
			}
		}
		
		
		row = rowRand;
		column = columnRand;
		
		currentZone = zone;
		return rowAndColumnToNumber(row, column);
	}
	*/
	
	//This is our random bestMove Method
	/*
	public int bestMove(AdvTTTBoard advTTTBoard, int zone) {
		String [][] board = getCompStringBoard(advTTTBoard, zone);
		
		Random random = new Random();
		int rowRand = random.nextInt(3);
		int columnRand = random.nextInt(3);
		
		if (board[rowRand][columnRand] != null) {
			while (true) {
				rowRand = random.nextInt(3);
				columnRand = random.nextInt(3);
				if (board[rowRand][columnRand] == null) {
					break;
				}
			}
		}
		
		row = rowRand;
		column = columnRand;
		
		currentZone = zone;
		return rowAndColumnToNumber(row, column);
		
	}
	*/
	
	
	//This is our random best move method when we are given no restricted zone
		/*
	public int bestMove(AdvTTTBoard advTTTBoard) {
			Random random = new Random();
			int randomChoice = random.nextInt(9) + 1;
			
			if (AdvTTTBoard.zoneIsFull(advTTTBoard, randomChoice)) {
				while (!AdvTTTBoard.zoneIsFull(advTTTBoard, randomChoice)) {
					randomChoice = random.nextInt(9) + 1;
				}
			}
			
			
			currentZone = randomChoice;
			return bestMove(advTTTBoard, randomChoice);
		}
		*/
	
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

	//Returns the String array for a zone
	public String[][] getCompStringBoard(AdvTTTBoard advtttBoard, int zone) {
		String[][] tictac = advtttBoard.getAdvTTTBoard()[AdvRealPlayer.getRow(zone)][AdvRealPlayer.getColumn(zone)]
				.getBoard();
		return tictac;
	}

	public int bestMove(TTTBoard[][] advTTTBoard, int zoneSelection) {
		int bigRow = AdvRealPlayer.getRow(zoneSelection);
		int bigColumn = AdvRealPlayer.getColumn(zoneSelection);
		int bestMoveScore = -10000;
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (advTTTBoard[bigRow][bigColumn].getBoard()[r][c] == null) {
					advTTTBoard[bigRow][bigColumn].getBoard()[r][c] = letter;
					int currentMove = hminimax(advTTTBoard, 0, false, zoneSelection);
					advTTTBoard[bigRow][bigColumn].getBoard()[r][c] = null;
					if (currentMove > bestMoveScore) {
						bestMoveScore = currentMove;
						row = r;
						column = c;
					}
				}
			}
		}
		currentZone = rowAndColumnToNumber(row, column);
		return rowAndColumnToNumber(row, column);
	}

	public int hminimax(TTTBoard[][] board, int depth, boolean b, int zone) {
		int bigRow = AdvRealPlayer.getRow(zone);
		int bigColumn = AdvRealPlayer.getColumn(zone);
		int maxSquareRow = -1;
		int maxSquareCol = -1;
		int maxScore = -10000;
		int score = 0;
		String[][] strBoard = board[bigRow][bigColumn].getBoard();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (strBoard[i][j] != null) {
					score = calcScore(board[i][j].getBoard(), letter);
				}
				if (score > maxScore) {
					maxScore = score;
					maxSquareRow = i;
					maxSquareCol = j;
				}
			}
		}
		
		return minimax(board[maxSquareCol][maxSquareRow], depth, b);
	}

	// Actual best move method with no restricted zone
	public int bestMove(AdvTTTBoard advTTTBoard) {
		TTTBoard[][] board = advTTTBoard.getAdvTTTBoard();
		int currRow = -1;
		int currCol = -1;
		int max = -99999;
		int score = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				TTTBoard indBoard = board[i][j];
				String[][] strBoard = indBoard.getBoard();
				score = calcScore(strBoard, letter);
				if (score > max) {
					max = score;
					currRow = i;
					currCol = j;
				}
			}
		}
		currentZone = rowAndColumnToNumber(currRow, currCol);
		TTTBoard indBoard = board[currRow][currCol];
		return bestMove(indBoard);

	}

	// Calc value of square (Corner: 3, Center: 5, Edge: 1, Each additional own
	// piece: 1, Each opponent piece: -1)
	// Serves as our heuristic 
	public int calcScore(String[][] board, String identity) {
		int score = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] != null) {
					if ((i == 0 && j == 0) || (i == 0 && j == 2) || (i == 2 && j == 0) || (i == 2 && j == 2)) {
						if (board[i][j].equals(identity)) {
							score += 5;
						}
					}
					if (i == 1 && j == 1) {
						if (board[i][j].equals(identity)) {
							score += 3;
						}
					}
					if ((i == 1 && j == 0) || (i == 0 && j == 1) || (i == 2 && j == 1) || (i == 1 && j == 2)) {
						if (board[i][j].equals(identity)) {
							score += 1;
						}
					}
					if (board[i][j].equals(identity)) {
						score += 1;
					} else if (board[i][j].equals(opposite(identity))) {
						score -= 5;
					}
				}
			}
		}
		return score;
	}

	//Returns x if o and vice versa
	public String opposite(String play) {
		if (play.equals("X")) {
			return "O";
		} else if (play.equals("O")) {
			return "X";
		} else {
			return "";
		}
	}

	//used for best move and no zone
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

	//Used if no zone
	public int bestMove(TTTBoard tttBoard) {
		int bestMoveScore = -10000;
		int row = -1;
		int column = -1;
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
		// System.out.format("Row: %d. Column %d. Spot: %d.", row, column,
		// rowAndColumnToNumber(row, column));
		return rowAndColumnToNumber(row, column);
	}

	//Both of these determine if game over
	public boolean gameOverNoWin(AdvTTTBoard advtttBoard) {
		if (AdvTTTBoard.isFull(advtttBoard) && !AdvTTTBoard.evaluateXWin(advtttBoard)
				&& !AdvTTTBoard.evaluateOWin(advtttBoard)) {
			return true;
		}
		return false;
	}

	public boolean gameOverNoWin(TTTBoard tttBoard) {
		if (TTTBoard.isFull(tttBoard) && !TTTBoard.evaluateXWin(tttBoard) && !TTTBoard.evaluateOWin(tttBoard)) {
			return true;
		}
		return false;
	}

	//Return int
	public int rowAndColumnToNumber(int row, int column) {
		return row * 3 + column + 1;
	}

}
