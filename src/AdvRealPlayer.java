import java.util.Scanner;

public class AdvRealPlayer {

	private int order;
	private String letter;

	public AdvRealPlayer(int order, String letter) {
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

	public boolean canPlace(AdvTTTBoard advTTTBoard, int bigRow, int bigColumn, int row, int column) {
		TTTBoard tttBoard = advTTTBoard.getAdvTTTBoard()[bigRow][bigColumn];
		if (tttBoard.getBoard()[row][column] == null) {
			return true;
		}
		return false;
	}

	public static int getColumn(int input) {
		if (input > 0 && input < 10) {
			input -= 1;
			return input % 3;
		} else {
			return -1;
		}
		
	}

	public static int getRow(int input) {
		if (input > 0 && input < 10) {
			return (input - 1) / 3;
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
	
	public String[][] getStringBoard(AdvTTTBoard advtttBoard, int zone) {
		String[][] tictac = advtttBoard.getAdvTTTBoard()[AdvRealPlayer.getRow(zone)][AdvRealPlayer.getColumn(zone)].getBoard();
		return tictac;
	}

	public int userPlaceLetter(String[][] board, String choice) {
		Scanner scanner = new Scanner(System.in);
		System.err.println();
		System.err.println();
		System.err.println();
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
