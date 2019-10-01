import java.util.Scanner;

public class RealPlayer {

	private int order;
	private String letter;

	public RealPlayer(int order, String letter) {
		this.letter = letter;
		this.order = order;
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

	public boolean canPlace(String[][] board, int row, int column) {
		if (board[row][column] == null) {
			return true;
		} else {
			return false;
		}
	}

	public static int getColumn(int input) {
		if (input == 1) {
			return 0;
		} else if (input == 2) {
			return 1;
		} else if (input == 3) {
			return 2;
		} else if (input == 4) {
			return 0;
		} else if (input == 5) {
			return 1;
		} else if (input == 6) {
			return 2;
		} else if (input == 7) {
			return 0;
		} else if (input == 8) {
			return 1;
		} else if (input == 9) {
			return 2;
		} else {
			return -1;
		}
	}

	public static int getRow(int input) {
		if (input == 1) {
			return 0;
		} else if (input == 2) {
			return 0;
		} else if (input == 3) {
			return 0;
		} else if (input == 4) {
			return 1;
		} else if (input == 5) {
			return 1;
		} else if (input == 6) {
			return 1;
		} else if (input == 7) {
			return 2;
		} else if (input == 8) {
			return 2;
		} else if (input == 9) {
			return 2;
		} else {
			return -1;
		}
	}

	public static String removeCase(String choice) {
		if (choice.equals("x") || choice.equals("X")) {
			choice = "X";
		} else if (choice.equals("o") || choice.equals("O")) {
			choice = "O";
		} else {
			choice = null;
		}
		return choice;
	}

	//Place the letter in the board
	public int userPlaceLetter(String[][] board, String choice) {
		Scanner scanner = new Scanner(System.in);
		System.err.print("Enter Choice: ");
		int input = scanner.nextInt();
		int rowCalc = RealPlayer.getRow(input);
		int columnCalc = RealPlayer.getColumn(input);
		if (board[rowCalc][columnCalc] == null && rowCalc != -1 && columnCalc != -1) {
			return input;
		} else {
			while (true) {
				if (board[rowCalc][columnCalc] == null) {
					return input;
				} else {
					if (rowCalc == -1 && columnCalc == -1) {
						System.err.print("Invalid. Enter again: ");
					} else {
						System.err.print("Already taken. Enter again: ");
					}
					input = scanner.nextInt();
					rowCalc = RealPlayer.getRow(input);
					columnCalc = RealPlayer.getColumn(input);
				}
			}
		}
	}
}
