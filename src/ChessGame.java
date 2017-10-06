

import java.util.Scanner;
import chess.*;


public class ChessGame {
	public static void main(String[] args) {
		run(new Chessboard());
	}

	// Runs the game
	static void run(Chessboard board) {
		// just demo
		board.setup();

		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.println(board);


			String query = scanner.nextLine().trim();

			if (query.isEmpty()) {
				continue;
			}

			if (query.charAt(0) == '/') {
				return;
			}

			Move move;
			try {
				move = nextMove(query);
				board.move(move);
			} catch (Exception e) {
				System.out.println("Error: " + e);
				continue;
			}

		} // EOF WHILE TRUE 


	} // EOF MAIN

	static Move nextMove(String query) throws Exception {
		Move move;

		char[] parsedStr = parse(query);

		Square from = new Square(parsedStr[0], parsedStr[1] - '0');
		Square to	= new Square(parsedStr[2], parsedStr[3] - '0');

		return new Move(from, to);
	}

	static char[] parse(String string) throws Exception {
		char[] parsedStr = new char[4];
		int it = 0;

		int i = 0;
		for (int c = 0; c < 2; c++) {
			for (; !isAplha(string.charAt(i)); i++);
			parsedStr[it++] = string.charAt(i);

			for (; !isDigit(string.charAt(i)); i++);
			parsedStr[it++] = string.charAt(i);
		}

		if (it < 4) {
			throw new Exception("Invalid input.");
		}

		return parsedStr;
	}

	static boolean isAplha(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

}


