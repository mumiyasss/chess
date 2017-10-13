

package chess.pieces;

import chess.*;

public class Knight extends Piece {

	public Knight(Color color) {
		this.color = color;

		if (color == Color.WHITE) {
			this.icon = '♞';
		} else {
			this.icon = '♘';
		}
	}

	public boolean isLegal(Move move, Chessboard board) {

		int rankOffset = Math.abs(move.DESTINATION.RANK - move.SOURCE.RANK);
		int fileOffset = Math.abs(move.SOURCE.FILE - move.DESTINATION.FILE);

		if (rankOffset + fileOffset != 3) {
			return false;
		}

		if (rankOffset == 3 || fileOffset == 3) {
			return false;
		}

		return true;
	}
}

