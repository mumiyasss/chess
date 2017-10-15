

import java.util.Scanner;

import java.io.IOException;

import chess.Chessboard; // DO WE REALLY NEED SMTH BUT CHESSBOARD HERE?
import chess.GameHistory;
import chess.InputHandler;
import chess.Move;

public class ChessGame {
	public static void main(String[] args) throws IOException {
		if (args.length > 0) {
			run(new Chessboard(), new GameHistory(args[0]));
		} else {
			run(new Chessboard(), new GameHistory());
		}
	}

	// Runs the game
	static void run(Chessboard board, GameHistory history) throws IOException {
		Scanner scanner = new Scanner(System.in);

		if (!history.isEmpty()) {
			board.setup(history);
		} else {
			board.setup();
		}

		System.out.println("_______CHESS_______");
		System.out.println(board);

		while (true) {
			String query = scanner.nextLine().trim();

			// check if input is an empty line and skip if true
			if (query.isEmpty()) {
				continue;
			}

			// if input is a command
			if (query.charAt(0) == '/') {
				String[] argsLine = query.split(" ");
				switch (argsLine[0]) {
					case "/exit":
						return;

					case "/save":
						history.log();
						break;

					case "/cancel":
						history.cancelLastMove();
						break;

					default:
						System.out.println("Invalid command: " + query);
				}
			} else {
				// move input handler
				try {
					Move move = InputHandler.getNextMove(query);
					board.move(move);
	
					history.add(move);
				} catch (Exception e) {
					// System.out.println(e);
					e.printStackTrace();
					continue;
				}
			}

			InputHandler.clearScreen();
			System.out.println("_______CHESS_______");
			System.out.println(board);
		}
	} // END OF RUN()
}
