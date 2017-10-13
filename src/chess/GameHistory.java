

package chess;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Date;


/**
 * GameHistory
 */
public class GameHistory {
	ArrayList<Move> logList; // PACKAGE DEFAULT
	private int moveCount;

	// creates empty history
	public GameHistory() {
		moveCount = 0;
		logList = new ArrayList<Move>();
	}

	// copies history from file
	// then Chessboard performs all these moves
	public GameHistory(String fileName)
			throws FileNotFoundException, IOException {
		this();

		Scanner reader = new Scanner(new File(fileName));
		
		while (reader.hasNext()) {
			String line = reader.nextLine().trim();
			
			if (line.indexOf("[") < line.lastIndexOf("]")) {
				continue;
			}
			
			this.add(InputHandler.getNextMove(line));
		}
	}

	// after performing a move, add it to the history
	public void add(Move move) {
		++this.moveCount;
		this.logList.add(move);
	}

	// true if no moves are logged
	public boolean isEmpty() {
		return this.logList.isEmpty();
	}

	// saves moves to file
	public void log() throws IOException {
		String fileName
			= String.join("_", new Date().toString().split(" ")) + ".log";
		FileWriter writer = new FileWriter(fileName);

		int moveC = 1;
		for (Move m : this.logList) {
			writer.write(moveC + ". " + m + "\n");
			moveC++;
		}
		writer.close();
	}
}

