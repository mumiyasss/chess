

package chess;

import chess.InputHandler;

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
	ArrayList<String> log; // PACKAGE DEFAULT
	private int moveCount;

	// creates empty history
	public GameHistory() {
		moveCount = 0;
		log = new ArrayList<String>();
	}

	// copies history from file
	public GameHistory(String fileName) throws FileNotFoundException {
		this();

		Scanner reader = new Scanner(new File(fileName));
		while (reader.hasNext()) {
			this.add(InputHandler.getNextMove(reader.nextLine()));
		}


	}

	public void add(Move move) {
		++this.moveCount;
		this.log.add(this.moveCount + ". " + move.toString());
	}

	public boolean isEmpty() {
		return this.log.isEmpty();
	}

	public Move getMove(int index) throws IndexOutOfBoundsException {
		return InputHandler.getNextMove(this.log.get(index));
	}

	public void log() {
		String fileName = new Date() + ".log";
		FileWriter writer = new FileWriter(fileName);
		for (String s : this.log) {
			writer.write(s);
		}
		writer.close();
	}
}


