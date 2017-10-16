
import java.util.Scanner;

// import chess.pieces.*;

import java.io.IOException;

import chess.Chessboard;
import chess.EmptyHistoryException;
import chess.GameHistory;
import chess.IllegalMoveException;
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
		
		mainLoop : while (true) {
			// Считывание пользовательского ввода 
			String query = scanner.nextLine().trim();

			// Проверка на пустую строку при вводе
			if (query.isEmpty()) {
				continue mainLoop;
			}

			try {
				if (query.charAt(0) == '/') {
					String[] argsLine = query.split(" ");
					switch (argsLine[0]) {
						case "/exit":
							return;

						case "/cancel":
							board.cancelLastMove();
							continue mainLoop;

						case "/save":
							history.log();
							continue mainLoop;

						default:
							System.out.println("Invalid command: " + query);
							continue mainLoop;
					}
				}

				Move move = InputHandler.getNextMove(query);
				board.move(move);
			} catch (IllegalMoveException e) {
				System.out.println("You are mistaking: " + e);
				continue mainLoop;
			} catch (EmptyHistoryException e) {
				System.out.println("Your history is empty: " + e);
				continue mainLoop;
			}

			InputHandler.clearScreen();
			System.out.println("_______CHESS_______");
			System.out.println(board);

		} // EOF WHILE TRUE 
	} // EOF RUN
}


