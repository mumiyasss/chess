

package chess;

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

	// gets a string and returns a move if its possible
	public static Move getNextMove(String query) throws IOException {
		char[] parsedStr = parse(query); // THROwS IO EXCEPTION

		Square from = new Square(parsedStr[0], parsedStr[1] - '0');
		Square to   = new Square(parsedStr[2], parsedStr[3] - '0');

		return new Move(from, to);
	}

	// gets a line and takes only ([a-hA-H][1-8]){2}
	private static char[] parse(String line) throws IOException {
		char[] parsedStr = new char[4];
		int it = 0;

		int i = 0;
		try {
			for (int c = 0; c < 2; c++) {
				while (!isFile(line.charAt(i))) {
					++i;
				}
				parsedStr[it++] = line.charAt(i);
	
				while (!isRank(line.charAt(i))) {
					++i;
				}
				parsedStr[it++] = line.charAt(i);
			}
		} catch (java.lang.StringIndexOutOfBoundsException e) {
			throw new IOException("Invalid input.");
		}

		if (it < 4) {
			throw new IOException("Invalid input. Cannot parse " + line);
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
