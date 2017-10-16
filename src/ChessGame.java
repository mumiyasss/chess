

import chess.Chessboard;
import chess.EmptyHistoryException;
import chess.GameHistory;
import chess.IllegalMoveException;
import chess.InputHandler;
import chess.Move;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChessGame {
	public static void main(String[] args) throws IOException {
		run(args.length > 0 ? args[0] : "");
	}

	// Runs the game
	static void run(String savedGameFileName) throws IOException {
		Scanner scanner = new Scanner(System.in);

		Chessboard board;
		board = new Chessboard();

		if (!savedGameFileName.isEmpty()) {
			// board = new Chessboard(new GameHistory(new File(savedGameFileName)));
			board.setup(new GameHistory(new File(savedGameFileName)));
		} else {
			// board = new Chessboard();
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

			errorHandler : try {
				if (query.charAt(0) == '/') {
					String[] argsLine = query.split(" ");
					switch (argsLine[0]) {
						case "/exit":
							return;

						case "/cancel":
							Move mv = board.cancelLastMove();
							System.out.println(mv + " had been сancelled");
							break errorHandler;

						case "/save":
							board.save();
							System.out.println("Succes!");
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


