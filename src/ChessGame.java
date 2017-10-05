

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
		
		// while (true) {
			
		// }


		System.out.println(board);


	}

	// think about it
	// static Move parse(String line) {
	// 	// TODO
	// }
}


