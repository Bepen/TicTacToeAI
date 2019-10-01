import java.util.Scanner;

public class TTTGameMain {

	public static void main(String[] args) {

		//This is the main method that runs both of the game
		Scanner sc = new Scanner(System.in);
		String playAgain = "Y";
		while(playAgain.equalsIgnoreCase("Y")) {
			System.err.print("Basic Tic-Tac-Toe: X plays first. Enter what you want to play as: ");
			String choiceTTT = sc.nextLine();
			choiceTTT = RealPlayer.removeCase(choiceTTT);
			TTTBoard tttBoard = new TTTBoard();
			TTTGame tttGame = new TTTGame();
			tttGame.playGame(tttBoard, choiceTTT);
			System.err.println("Do you want to play again? [Y/N]");
			playAgain = sc.nextLine();
		}
		
		String playAgainADV = "Y"; 
		while(playAgainADV.equalsIgnoreCase("Y")) {
			System.err.print("Advanced Tic-Tac-Toe: X plays first. Enter what you want to play as: ");
			String choice = sc.nextLine();
			choice = RealPlayer.removeCase(choice);
			TTTBoard[][] tttBoardArray = new TTTBoard[3][3];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					tttBoardArray[i][j] = new TTTBoard();
				}
			}
			AdvTTTBoard advtttBoard = new AdvTTTBoard(tttBoardArray);
			AdvTTTGame advtttGame = new AdvTTTGame();
			advtttGame.playGame(advtttBoard, choice);
			System.err.println("Do you want to play again? [Y/N]");
			playAgainADV = sc.nextLine();
		}

	}
}
