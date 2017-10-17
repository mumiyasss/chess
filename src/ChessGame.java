

import chess.Chessboard;
import chess.EmptyHistoryException;
import chess.GameHistory;
import chess.IllegalMoveException;
import chess.InputHandler;
import chess.Move;
import chess.GameCode;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ChessGame {
	public static void main(String[] args) throws IOException {
		InputHandler.clearScreen();
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
			
			// Проверка на шах
			if (board.check_status() == GameCode.CHECK) {
				System.out.println("Шах!");
			}

			// Скажем чей щас ход
			if (board.gameMoveNumber % 2 == 0) {
				System.out.print("Ход чёрных: ");
			} else System.out.print("Ход белых: ");
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
							System.out.println(mv + " отменён");
							break errorHandler;

						case "/save":
							board.save();
							System.out.println("Успех!");
							continue mainLoop;

						default:
							System.out.println("Неверная команда: " + query);
							continue mainLoop;
					}
				}

				Move move = InputHandler.getNextMove(query);
				board.move(move);

			} catch (IllegalMoveException e) {
				System.out.println(e);
				continue mainLoop;
			} catch (EmptyHistoryException e) {
				System.out.println("Итстория пуста: " + e);
				continue mainLoop;
			} catch (IOException e) {
				System.out.println("Неверный ввод: " + e);
				continue mainLoop;
			}

			InputHandler.clearScreen();
			System.out.println("_______CHESS_______");
			System.out.println(board);

		} // EOF WHILE TRUE 
	} // EOF RUN
}


