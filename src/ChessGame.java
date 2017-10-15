
import java.util.Scanner;

// import chess.pieces.*;.

import java.io.IOException;

import chess.Chessboard; // DO WE REALLY NEED SMTH BUT CHESSBOARD HERE?
import chess.GameHistory;
import chess.InputHandler;
import chess.Move;
import chess.IllegalMoveException;

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
		
		mainloop : while (true) {
			// Считывание пользовательского ввода 
			String query = scanner.nextLine().trim();

			// Проверка на пустую строку при вводе
			if (query.isEmpty()) {
				continue;
			}
			try {

			// Выход из приложения при нажатии /exit
				if (query.charAt(0) == '/') {
					String[] argsLine = query.split(" ");
					switch (argsLine[0]) {
						case "/exit":
							return;

						case "/save":
							history.log();
							continue mainloop;

						case "/cancel":
							board.cancelLastMove();
							continue mainloop;

						default:
							System.out.println("Invalid command: " + query);
					}
				}

				Move move = InputHandler.getNextMove(query);
				board.move(move);
			} catch (Exception e) {
				System.out.println("Error: " + e);
				continue;
			}

			InputHandler.clearScreen();
			System.out.println("_______CHESS_______");
			System.out.println(board);

		} // EOF WHILE TRUE 
	} // EOF RUN
}


