

package chess.pieces;

import chess.*;

public class King extends Piece {

	public King(Color color) {
		this.color = color;

		if (color == Color.WHITE) {
			this.icon = '♚';
		} else {
			this.icon = '♔';
		}
	}

	public boolean isLegal(Move move, Chessboard board) {

		if (!King.isValid(move)) {
			return false;
		}

		// TODO
		// Check check occurs in Chessboard class


		return true;
	}

	static boolean isValid(Move move) {
		int dFile = move.DESTINATION.FILE - move.SOURCE.FILE;
		int dRank = move.DESTINATION.RANK - move.SOURCE.RANK;

		if (Math.abs(dFile) > 1 || Math.abs(dRank) > 1) {
			return false;
		}

		return true;
	}
}

