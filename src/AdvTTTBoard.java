public class AdvTTTBoard {

	private TTTBoard[][] AdvTTTBoard;

	public AdvTTTBoard(TTTBoard[][] AdvTTTBoard) {
		this.AdvTTTBoard = AdvTTTBoard;
	}

	public TTTBoard[][] getAdvTTTBoard() {
		return AdvTTTBoard;
	}

	public void setAdvTTTBoard(TTTBoard[][] AdvTTTBoard) {
		this.AdvTTTBoard = AdvTTTBoard;
	}

	//Prints a row in the table
	public static void printRowBig(AdvTTTBoard advtttBoard, int bigRow) {
		TTTBoard[][] tttBoard = advtttBoard.getAdvTTTBoard();
		int row = bigRow % 3;
		int box = bigRow / 3;
		for (int i = 0; i < 3; i++) {
			TTTBoard board = tttBoard[box][i];
			String [][] stringBoard = board.getBoard();
			for (int j = 0; j < 3; j++) {
				if (stringBoard[row][j] == null) {
					System.err.print(" ");
				} else {
					System.err.print(stringBoard[row][j]);
				}

				if (j == 0 || j == 1) {
					System.err.print(" | ");
				}
			}
			if (i == 0 || i == 1) {
				System.err.print("   ||   ");
			}
		}
		
		System.err.println();
	}
	
	//Prints the big table in tictactoe
	public static void printAdvBoard(AdvTTTBoard advtttBoard) {
		for (int i = 0; i < 9; i++) {
			printRowBig(advtttBoard, i);
			
			if (i >= 0 && i < 8) {
				if (i == 2 || i == 5) {
					System.err.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.err.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				} else {
					System.err.println("--------------------------------------------");
				}
			}
			
		}
		
	}
	
	//Copy's an array to avoid a local copy
	public static TTTBoard[][] copyAdvArray(AdvTTTBoard tttboard) {
		TTTBoard[][] copyAdvTTTBoard = new TTTBoard[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				String[][] array = TTTBoard.copyArray(tttboard.getAdvTTTBoard()[i][j]);
				TTTBoard board = new TTTBoard();
				board.setBoard(array);
				copyAdvTTTBoard[i][j] = board;
			}
		}
		return copyAdvTTTBoard;
	}

	// General method for determining whether a letter won
	public static boolean evaluateLetterWin(AdvTTTBoard board, String player) {
		TTTBoard[][] game = copyAdvArray(board);
		
		for(int i = 0; i < 3; i++) { //turning all the nulls to pop in order to allow a string comparison later
			for(int j = 0; j < 3; j++) {
				for(int k = 0; k < 3; k++) {
					for(int l = 0; l < 3; l++) {
						if (game[i][j].getBoard()[k][l] == null) {
							game[i][j].getBoard()[k][l] = "pop";
						}
					}
				}
			}
		}
		
		for(int i = 0; i < 3; i++) { //horizontal check
			for(int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					if (game[i][j].getBoard()[k][0].equals(player) && game[i][j].getBoard()[k][1].equals(player) && game[i][j].getBoard()[k][2].equals(player)) {
						return true;
					}
				}
			}
		}
		
		for(int i = 0; i < 3; i++) { //vertical check
			for(int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					if (game[i][j].getBoard()[0][k].equals(player) && game[i][j].getBoard()[1][k].equals(player) && game[i][j].getBoard()[2][k].equals(player)) {
						return true;
					}
				}
			}
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if (game[i][j].getBoard()[0][0].equals(player) && game[i][j].getBoard()[1][1].equals(player) && game[i][j].getBoard()[2][2].equals(player)) {
					return true;
				}
				if (game[i][j].getBoard()[2][0].equals(player) && game[i][j].getBoard()[1][1].equals(player) && game[i][j].getBoard()[0][2].equals(player)) {
					return true;
				}
			}
		}
		
		return false;
	}

	// Check to see if X wins
	public static boolean evaluateXWin(AdvTTTBoard board) {
		return evaluateLetterWin(board, "X");
	}

	// Check to see if O wins
	public static boolean evaluateOWin(AdvTTTBoard board) {
		return evaluateLetterWin(board, "O");

	}

	// Check to see if board is empty
	public static boolean isFull(AdvTTTBoard AdvTTTBoard) {
		TTTBoard[][] board = copyAdvArray(AdvTTTBoard);
		for(int i = 0; i < 3; i++) { //turning all the nulls to pop in order to allow a string comparison later
			for(int j = 0; j < 3; j++) {
				for(int k = 0; k < 3; k++) {
					for(int l = 0; l < 3; l++) {
						if (board[i][j].getBoard()[k][l] == null) {
							return false;
						}
					}
				}
			}
		}
		return true;

	}
	
	//Sees a certain zone is full
	public static boolean zoneIsFull(AdvTTTBoard AdvTTTBoard, int zone) { //returns true if the zone is full
		if(zone == -999) {
			return false;
		}
		TTTBoard[][] board = copyAdvArray(AdvTTTBoard);
		int bigRow = AdvRealPlayer.getRow(zone);
		int bigColumn = AdvRealPlayer.getColumn(zone);
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				if(board[bigRow][bigColumn].getBoard()[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}

	//Get a board of nulls
	public static TTTBoard[][] getNullBoard() {
		TTTBoard[][] AdvTTTBoard = new TTTBoard[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				for(int k = 0; k < 3; k++) {
					for(int l = 0; l < 3; l++) {
						AdvTTTBoard[i][j].getBoard()[k][l] = null;
					}
				}
			}
		}
		
		return AdvTTTBoard;
	}
}
