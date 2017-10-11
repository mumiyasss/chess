
import java.util.Scanner;

// import chess.pieces.*;.

import chess.Chessboard; // DO WE REALLY NEED SMTH BUT CHESSBOARD HERE?
import chess.GameHistory;
import input.InputHandler;

public class ChessGame {
	public static void main(String[] args) {

		if (args.length > 0) {
			run(new Chessboard(), new GameHistory(args[0]));
		} else {
			run(new Chessboard(), new GameHistory());
		}

	}

	// Runs the game
	static void run(Chessboard board, GameHistory history) {
		// just demo

		Scanner scanner = new Scanner(System.in);

		if (!history.isEmpty()) {
			board.setup(history);
		} else {
			board.setup();
		}

		while (true) {
			InputHandler.clearScreen();

			System.out.println("_______CHESS_______");
			System.out.println(board);
			
			// Считывание пользовательского ввода 
			String query = scanner.nextLine().trim();

			// Проверка на пустую строку при вводе
			if (query.isEmpty()) {
				continue;
			}

			// Выход из приложения при нажатии на '/' << or /exit
			// TODO
			if (query.charAt(0) == '/') {
				String[] argsLine = query.split(" ");
				switch (argsLine[0]) {
					case "/exit":
						return;

					case "/save":
						history.log(...);
						break;

					default:
						throw new IOException("Invalid command.");
				}
			}

			try {
				Move move = InputHandler.getNextMove(query);
				board.move(move);

				history.add(move);
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}

		} // EOF WHILE TRUE 
	} // EOF RUN
}


