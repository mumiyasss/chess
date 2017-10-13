

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

		return true;
	}
}

