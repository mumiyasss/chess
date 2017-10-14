

package chess.pieces;

import chess.*;

public class Bishop extends Piece {

	public Bishop(Color color) {
		this.color = color;

		if (color == Color.WHITE) {
			this.icon = '♝';
		} else {
			this.icon = '♗';
		}
	}

	public boolean isLegal(Move move, Chessboard board) {

		if (!Bishop.isValid(move)) {
			return false;
		}

		int fileStep = sign(move.DESTINATION.FILE - move.SOURCE.FILE);
		int rankStep = sign(move.DESTINATION.RANK - move.SOURCE.RANK);

		int file = move.SOURCE.FILE + fileStep;
		int rank = move.SOURCE.RANK + rankStep;

		while (file != move.DESTINATION.FILE && rank != move.DESTINATION.RANK) {

			Square s = new Square(file, rank);
			Piece element = board.get(s);

			if (element != null) {
				return false;
			}
		}

		return true;
	}

	static boolean isValid(Move move) {
		int dFile = move.DESTINATION.FILE - move.SOURCE.FILE;
		int dRank = move.DESTINATION.RANK - move.SOURCE.RANK;

		if (Math.abs(dFile) != Math.abs(dRank)) {
			return false;
		}

		return true;
	}

}

