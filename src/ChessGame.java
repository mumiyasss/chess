
import chess.*;
import java.util.Scanner;
import java.lang.Exception;

public class ChessGame {
	public static void main(String[] args) {
		run(new Chessboard());
	}


	
	// Runs the game
	private static void run(Chessboard board) {
		// just demo
		board.setup();

		Scanner scanner = new Scanner(System.in);

		while (true) {
			clearScreen();

			System.out.println("_______CHESS_______");
			System.out.println(board);
			
			// Считывание пользовательского ввода 
			String query = scanner.nextLine().trim();

			// Проверка на пустую строку при вводе
			if (query.isEmpty()) {
				continue;
			}

			// Выход из приложения при нажатии на '/' << or /exit
			if (query.charAt(0) == '/') {
				return;
			}
			
			Move move; // Много раз выделяется памяти? << only 1 time
			try {
				move = nextMove(query);
				board.move(move);
			} catch (Exception e) {
				System.out.println("Error: " + e);
//				continue; // for a while
			}
		} // EOF WHILE TRUE 
	} // EOF RUN










}


