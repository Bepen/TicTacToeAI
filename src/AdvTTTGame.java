//test2

import java.util.Scanner;
public class AdvTTTGame {

	private AdvTTTBoard advTTTBoard;
	private AdvComputerPlayer computerPlayer;
	private AdvRealPlayer humanPlayer;
	private String userChoice;

	public TTTBoard[][] insert(TTTBoard[][] bigBoard, int boardZone, int insert, String choice) {
		int bigRow = AdvRealPlayer.getRow(boardZone);
		int bigColumn = AdvRealPlayer.getColumn(boardZone);
		int littleRow = RealPlayer.getRow(insert);
		int littleColumn = RealPlayer.getColumn(insert);
		bigBoard[bigRow][bigColumn].getBoard()[littleRow][littleColumn] = choice;
		return bigBoard;
	}

	//Main driver to play the game
	public void playGame(AdvTTTBoard advTTTBoard, String userChoice) {
		boolean beginningMove = true;
		int zoneSelection = -999;
		this.advTTTBoard = advTTTBoard;
		//this.advTTTBoard.setAdvTTTBoard(AdvTTTBoard.getNullBoard());
		this.userChoice = userChoice;
		if (userChoice.equals("X")) {
			this.computerPlayer = new AdvComputerPlayer(2, "O", "X");
			this.humanPlayer = new AdvRealPlayer(1, "X");
		} else if (userChoice.equals("O")) {
			this.computerPlayer = new AdvComputerPlayer(1, "X", "O");
			this.humanPlayer = new AdvRealPlayer(2, "O");
		}
		
		if(userChoice.equals("X")) {
			Scanner scan = new Scanner(System.in);
			AdvTTTBoard.printAdvBoard(this.advTTTBoard);
			System.err.println("Zone: " + zoneSelection);
			while (!AdvTTTBoard.evaluateOWin(this.advTTTBoard) && !AdvTTTBoard.evaluateXWin(this.advTTTBoard)
					&& !AdvTTTBoard.isFull(this.advTTTBoard)) {
				int CPUChoice;
				
				if(beginningMove || AdvTTTBoard.zoneIsFull(advTTTBoard, zoneSelection)) {
					beginningMove = false;
					System.err.println("Choose a zone in the big tic tac toe board: ");
					zoneSelection = scan.nextInt();
				}
				int playerChoice = this.humanPlayer.userPlaceLetter(this.humanPlayer.getStringBoard(advTTTBoard, zoneSelection), "X");
				advTTTBoard.setAdvTTTBoard(insert(advTTTBoard.getAdvTTTBoard(), zoneSelection, playerChoice, "X"));
				//System.out.format("Zone: %d, Location: %d", zoneSelection, playerChoice);
				zoneSelection = playerChoice;
				AdvTTTBoard.printAdvBoard(advTTTBoard);
				System.err.println("Zone: " + zoneSelection);
				if(AdvTTTBoard.evaluateXWin(advTTTBoard)) {
					System.err.println("User Win.");
					break;
				} else if(AdvTTTBoard.isFull(advTTTBoard)) {
					System.err.println("Tie.");
					break;
				}
				if(AdvTTTBoard.zoneIsFull(advTTTBoard, zoneSelection)) {
					CPUChoice = computerPlayer.bestMove(advTTTBoard);
					zoneSelection = computerPlayer.getZone();
				} else{
					CPUChoice = computerPlayer.bestMove(advTTTBoard.getAdvTTTBoard(), zoneSelection);
				}
				this.advTTTBoard.setAdvTTTBoard(insert(advTTTBoard.getAdvTTTBoard(), zoneSelection, CPUChoice, "O"));
				//System.out.format("Zone: %d, Location: %d", zoneSelection, CPUChoice);
				zoneSelection = CPUChoice;
				System.err.println();
				System.err.println();
				System.err.println();
				System.err.println("AI Move");
				AdvTTTBoard.printAdvBoard(advTTTBoard);
				System.err.println("Zone: " + zoneSelection);
				if (AdvTTTBoard.evaluateOWin(advTTTBoard)) {
					System.err.println("Computer Win.");
					break;
				} else if (AdvTTTBoard.isFull(advTTTBoard)) {
					System.err.println("Tie.");
					break;
				}
				
			}
		} else if (userChoice.equals("O")) {
			Scanner scan = new Scanner(System.in);
			AdvTTTBoard.printAdvBoard(advTTTBoard);
			while (!AdvTTTBoard.evaluateOWin(this.advTTTBoard) && !AdvTTTBoard.evaluateXWin(this.advTTTBoard)
					&& !AdvTTTBoard.isFull(this.advTTTBoard)) {
				int CPUChoice;
				if(beginningMove || AdvTTTBoard.zoneIsFull(advTTTBoard, zoneSelection)) {
					beginningMove = false;
					CPUChoice = computerPlayer.bestMove(advTTTBoard);
					zoneSelection = computerPlayer.getZone();
				} else{
					CPUChoice = computerPlayer.bestMove(advTTTBoard.getAdvTTTBoard(), zoneSelection);
				}
				this.advTTTBoard.setAdvTTTBoard(insert(advTTTBoard.getAdvTTTBoard(), zoneSelection, CPUChoice, "X"));
				//System.out.format("Zone: %d, Location: %d", zoneSelection, CPUChoice);
				zoneSelection = CPUChoice;
				System.err.println();
				System.err.println();
				System.err.println();
				System.err.println("AI Move");
				AdvTTTBoard.printAdvBoard(advTTTBoard);
				if(AdvTTTBoard.evaluateXWin(advTTTBoard)) {
					System.err.println("Computer Win.");
					break;
				} else if(AdvTTTBoard.isFull(advTTTBoard)) {
					System.err.println("Tie.");
					break;
				}
				
				if(AdvTTTBoard.zoneIsFull(advTTTBoard, zoneSelection)) {
					System.err.println("Choose a zone in the big tic tac toe board: ");
					zoneSelection = scan.nextInt();
				}
				int playerChoice = this.humanPlayer.userPlaceLetter(this.humanPlayer.getStringBoard(advTTTBoard, zoneSelection), "O");
				//System.out.format("Zone: %d, Location: %d", zoneSelection, playerChoice);
				advTTTBoard.setAdvTTTBoard(insert(advTTTBoard.getAdvTTTBoard(), zoneSelection, playerChoice, "O"));
				zoneSelection = playerChoice;
				AdvTTTBoard.printAdvBoard(advTTTBoard);
				if(AdvTTTBoard.evaluateXWin(advTTTBoard)) {
					System.err.println("User Win.");
					break;
				} else if(AdvTTTBoard.isFull(advTTTBoard)) {
					System.err.println("Tie.");
					break;
				}
				
				
			}
		}
	}

}
