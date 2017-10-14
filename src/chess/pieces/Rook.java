

package chess.pieces;

import chess.*;

public class Rook extends Piece {

	public Rook(Color color) {
		this.color = color;

		if (color == Color.WHITE) {
			this.icon = '♜';
		} else {
			this.icon = '♖';
		}
	}

	public boolean isLegal(Move move, Chessboard board) {

		if (!Rook.isValid(move)) {
			return false;
		}

		int fileStep = sign(move.DESTINATION.FILE - move.SOURCE.FILE);
		int rankStep = sign(move.DESTINATION.RANK - move.SOURCE.RANK);

		if (fileStep != 0) {
			for (int file = move.SOURCE.FILE + fileStep; file != move.DESTINATION.FILE; file += fileStep) {

				Square s = new Square(file, move.DESTINATION.RANK);
				Piece element = board.get(s);

				if (element != null) {
					return false;
				}
			}
		}

		if (rankStep != 0) {
			for (int rank = move.SOURCE.RANK + rankStep; rank != move.DESTINATION.RANK; rank += rankStep) {

				Square s = new Square(move.DESTINATION.FILE, rank);
				Piece element = board.get(s);

				if (element != null) {
					return false;
				}
			}
		}

		return true;
	}

	static boolean isValid(Move move) {
		int dFile = move.DESTINATION.FILE - move.SOURCE.FILE;
		int dRank = move.DESTINATION.RANK - move.SOURCE.RANK;

		if (dFile * dRank != 0 || dFile == dRank) {
			return false;
		}

		return true;
	}

}

