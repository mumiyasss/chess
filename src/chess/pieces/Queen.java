

package chess.pieces;

import chess.*;

public class Queen extends Piece {

	public Queen(Color color) {
		this.color = color;

		if (color == Color.WHITE) {
			this.icon = '♛';
		} else {
			this.icon = '♕';
		}
	}

	public boolean isLegal(Move move, Chessboard board) {
		int dFile = move.DESTINATION.FILE - move.SOURCE.FILE;
		int dRank = move.DESTINATION.RANK - move.SOURCE.RANK;

		// this means that Queen moves like a Rook
		if (dFile == 0 || dRank == 0) {
			if (!new Rook().isLegal(move)) {
				return false;
			}
		} else {
			// this means that Queen moves like a Bishop
			if (!new Bishop().isLegal(move)) {
				return false;
			}
		}

		return true;
	}
}

