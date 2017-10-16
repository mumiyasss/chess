

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
	private ArrayList<Move> logList; // PACKAGE DEFAULT

	// creates empty history
	public GameHistory() {
		logList = new ArrayList<Move>();
	}

	// copies history from file
	// then Chessboard performs all these moves
	public GameHistory(File file)
			throws FileNotFoundException, IOException {
		this();

		Scanner reader = new Scanner(file);
		
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
		this.logList.add(move);
	}

	public int moveCount() {
		return this.logList.size();
	}

	// DELETE THE LAST MOVE FROM HISTORY
	public Move pop() throws EmptyHistoryException {
		if (this.logList.size() == 0) {
			throw new EmptyHistoryException("There is no move to cancel.");
		}
		Move canceledMove = this.logList.remove(this.logList.size() - 1);
		return canceledMove;
	}

	public Move[] getMoves() {
		Move[] moves = {};
		moves = this.logList.toArray(new Move[this.logList.size()]);
		return moves;
	}

	// true if no moves are logged
	public boolean isEmpty() {
		return this.logList.isEmpty();
	}

	// saves moves to file
	public void log() throws IOException {
		String fileName
			= String.join("_", new Date().toString().split(" ")) + ".save";
		FileWriter writer = new FileWriter(fileName);

		int moveC = 1;
		Move[] moves = this.getMoves(); 
		for (Move m : moves) {
			writer.write(moveC + ". " + m + "\n");
			moveC++;
		}
		writer.close();
	}
}


