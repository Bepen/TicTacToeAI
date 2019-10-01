
public class TTTBoard {
	// NOTE: Row->[][]<-Column
	private String[][] board;

	public TTTBoard() {
		board = new String[3][3];
	}

	public String[][] getBoard() {
		return board;
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}

	// Prints the Board's state to stderr
	public static void printBoardErr(String[][] board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == null) {
					System.err.print(" ");
				} else {
					System.err.print(board[i][j]);
				}

				if (j == 0 || j == 1) {
					System.err.print(" | ");
				}
			}
			if (i == 0 || i == 1) {
				System.err.println();
				System.err.println("---------");
			}

		}
		System.err.println();
		System.err.println();
		System.err.println();
	}
	

	//Copy an array into another
	public static String[][] copyArray(TTTBoard board) {
		String[][] copyBoard = new String[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				copyBoard[i][j] = board.getBoard()[i][j];
			}
		}
		return copyBoard;
	}

	// General method for determining whether a letter won
	public static boolean evaluateLetterWin(TTTBoard board, String player) {
		String[][] game = copyArray(board);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (game[i][j] == null) {
					game[i][j] = "pop";
				}
			}
		}

		// Horizontal Check
		for (int i = 0; i < 3; i++) {
			if (game[i][0].equals(player) && game[i][1].equals(player) && game[i][2].equals(player)) {

				return true;
			}
		}

		// Vertical Check
		for (int i = 0; i < 3; i++) {
			if (game[0][i].equals(player) && game[1][i].equals(player) && game[2][i].equals(player)) {
				return true;
			}
		}

		// Diagonal Check
		if (game[0][0].equals(player) && game[1][1].equals(player) && game[2][2].equals(player)) {
			return true;
		}
		if (game[2][0].equals(player) && game[1][1].equals(player) && game[0][2].equals(player)) {
			return true;
		}

		return false;
	}

	// Check to see if X wins
	public static boolean evaluateXWin(TTTBoard board) {
		return evaluateLetterWin(board, "X");
	}

	// Check to see if O wins
	public static boolean evaluateOWin(TTTBoard board) {
		return evaluateLetterWin(board, "O");
	}

	// Check to see if board is empty
	public static boolean isFull(TTTBoard tttBoard) {
		String[][] board = tttBoard.board;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == null) {
					// System.out.println("NULL REACHED");
					return false;
				}
			}
		}
		// System.out.println("Completely full");
		return true;
	}

	public static String[][] getNullBoard() {
		String[][] board = { { null, null, null }, { null, null, null }, { null, null, null } };
		return board;
	}

	public void printArray(String[][] array) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(array[i][j]);
			}
		}
	}
}
