

package chess;

import chess.*;
import java.io.IOException;


/**
 * InputHandler
 */
public class InputHandler {

	// clears the screen
	public static void clearScreen() {
		final String ANSI_CLS = "\u001b[2J";
		final String ANSI_HOME = "\u001b[H";
		System.out.print(ANSI_CLS + ANSI_HOME);
		System.out.flush();
	}

	// extracts Move from a string
	public static Move getNextMove(String query) throws IOException {
		char[] parsedStr = parse(query); // throws Exception

		Square from = new Square(parsedStr[0], parsedStr[1] - '0');
		Square to = new Square(parsedStr[2], parsedStr[3] - '0');

		return new Move(from, to);
	}

	// returns "([a-h][1-8]){2}"
	private static char[] parse(String line) throws IOException {
		char[] parsedStr = new char[4];
		int it = 0;

		int i = 0;
		try {
			for (int positionCount = 0; positionCount < 2; positionCount++) {
				for (; !isFile(line.charAt(i)); i++);
				parsedStr[it++] = line.charAt(i);
	
				for (; !isRank(line.charAt(i)); i++);
				parsedStr[it++] = line.charAt(i);
			}
		} catch (java.lang.StringIndexOutOfBoundsException e) {
			throw new IOException("Incomplete input.");
		}

		if (it < 4) {
			throw new IOException("Cannot parse.");
		}

		return parsedStr;
	}

	// true if c is a valid file
	private static boolean isFile(char c) {
		return (c >= 'a' && c <= 'h') || (c >= 'A' && c <= 'H');
	}

	// true if c is a valid rank
	private static boolean isRank(char c) {
		return c >= '1' && c <= '8';
	}

}
