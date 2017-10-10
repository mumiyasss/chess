
import java.util.Scanner;
import chess.*;

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


	private static void clearScreen() {
		// clears the screen
		// idk the meaning
		final String ANSI_CLS = "\u001b[2J";
		final String ANSI_HOME = "\u001b[H";
		System.out.print(ANSI_CLS + ANSI_HOME);
		System.out.flush();
	}


	static Move nextMove(String query) throws Exception {
		char[] parsedStr = parse(query);

		Square from = new Square(parsedStr[0], parsedStr[1] - '0');
		Square to = new Square(parsedStr[2], parsedStr[3] - '0');

		return new Move(from, to);
	}


	private static char[] parse(String string) throws Exception {
		char[] parsedStr = new char[4];
		int it = 0;

		int i = 0;
		for (int c = 0; c < 2; c++) {
			while (!isAplha(string.charAt(i))) {
				++i;
			}
			parsedStr[it++] = string.charAt(i);

			while (!isDigit(string.charAt(i))) {
				++i;
			}
			parsedStr[it++] = string.charAt(i);
		}

		if (it < 4) {
			throw new Exception("Invalid input.");
		}

		return parsedStr;
	}


	private static boolean isAplha(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}


	private static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	
	static void clearScreen() {
		// clears the screen
		// idk the meaning
		final String ANSI_CLS = "\u001b[2J";
		final String ANSI_HOME = "\u001b[H";
		System.out.print(ANSI_CLS + ANSI_HOME);
		System.out.flush();
	}
}


