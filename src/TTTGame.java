//test2
public class TTTGame {

	private TTTBoard tttBoard;
	private ComputerPlayer computerPlayer;
	private RealPlayer humanPlayer;
	private String userChoice;

	//Modify the string array and return it
	public String[][] insert(String[][] board, int insert, String choice) {
		int row = RealPlayer.getRow(insert);
		int column = RealPlayer.getColumn(insert);
		board[row][column] = choice;
		return board;
	}

	//Play the normal game
	public void playGame(TTTBoard tttBoard, String userChoice) {
		// System.out.println("Enter Play Game method");
		this.tttBoard = tttBoard;
		this.tttBoard.setBoard(TTTBoard.getNullBoard());
		this.userChoice = userChoice;
		if (userChoice.equals("X")) {
			// System.out.println("Enter Choice is X");
			this.computerPlayer = new ComputerPlayer(2, "O", "X");
			this.humanPlayer = new RealPlayer(1, "X");
		} else if (userChoice.equals("O")) {
			// System.out.println("Enter Choice is O");
			this.computerPlayer = new ComputerPlayer(1, "X", "O");
			this.humanPlayer = new RealPlayer(2, "O");
		}

		if (userChoice.equals("X")) {
			TTTBoard.printBoardErr(this.tttBoard.getBoard());
			while (!TTTBoard.evaluateOWin(this.tttBoard) && !TTTBoard.evaluateXWin(this.tttBoard)
					&& !TTTBoard.isFull(this.tttBoard)) {

				int CPUChoice;

				int playerChoice = this.humanPlayer.userPlaceLetter(this.tttBoard.getBoard(), "X");
				this.tttBoard.setBoard(insert(this.tttBoard.getBoard(), playerChoice, "X"));

				TTTBoard.printBoardErr(this.tttBoard.getBoard());
				if (TTTBoard.evaluateXWin(this.tttBoard)) {
					System.err.println("User Win.");
					break;
				} else if (TTTBoard.isFull(tttBoard)) {
					System.err.println("Tie.");
					break;
				}

				CPUChoice = computerPlayer.bestMove(tttBoard);
				this.tttBoard.setBoard(insert(this.tttBoard.getBoard(), CPUChoice, "O"));
				System.err.println();
				System.err.println("AI Move");
				TTTBoard.printBoardErr(this.tttBoard.getBoard());
				if (TTTBoard.evaluateOWin(this.tttBoard)) {
					System.err.println("Computer Win.");
					break;
				} else if (TTTBoard.isFull(tttBoard)) {
					System.err.println("Tie.");
					break;
				}
			}
		} else if (userChoice.equals("O")) {
			TTTBoard.printBoardErr(this.tttBoard.getBoard());
			while (!TTTBoard.evaluateOWin(this.tttBoard) && !TTTBoard.evaluateXWin(this.tttBoard)
					&& !TTTBoard.isFull(this.tttBoard)) {
				int CPUChoice;

				CPUChoice = computerPlayer.bestMove(tttBoard);
				this.tttBoard.setBoard(insert(this.tttBoard.getBoard(), CPUChoice, "X"));

				TTTBoard.printBoardErr(this.tttBoard.getBoard());
				if (TTTBoard.evaluateXWin(this.tttBoard)) {
					System.err.println("Computer Win.");
					break;
				} else if (TTTBoard.isFull(tttBoard)) {
					System.out.println("Tie.");
					break;
				}

				int playerChoice = this.humanPlayer.userPlaceLetter(this.tttBoard.getBoard(), "O");
				this.tttBoard.setBoard(insert(this.tttBoard.getBoard(), playerChoice, "O"));
				System.err.println("AI Move");
				TTTBoard.printBoardErr(this.tttBoard.getBoard());
				if (TTTBoard.evaluateOWin(this.tttBoard)) {
					System.err.println("User Win.");
					break;
				} else if (TTTBoard.isFull(tttBoard)) {
					System.out.println("Tie.");
					break;
				}

			}
		}
	}

}
