

import java.util.Scanner;
import chess.*;


public class ChessGame {
	public static void main(String[] args) {
		run(new Chessboard());
	}

	// Runs the game
	static void run(Chessboard board) {

		// just demo
		for (int i = 0; i < Chessboard.BOARD_SIZE; i++) {
			board.set(new Pawn(Color.WHITE), new Position(6, i));
		}

		System.out.println(board);
	}

	// Returns move in acceptable format
	static Move inputHandler(Scanner scanner) {
		// TODO
		String query = scanner.nextLine();

		// ...
	}
}


