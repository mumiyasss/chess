

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

		int dx = move.DESTINATION.FILE - move.SOURCE.FILE;
		int dy = move.DESTINATION.RANK - move.SOURCE.RANK;

		if (dx * dy != 0 || dx == dy) {
			return false;
		}

		// TODO

		return true;
	}

	// private Square[] vector(Move move) {

	// }
}

