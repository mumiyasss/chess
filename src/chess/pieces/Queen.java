package chess.pieces;
import chess.*;

public class Queen extends Piece {
	public Queen(Color color) {
		this.icon = '♕';
		this.color = color;

		if (color == Color.WHITE) {
			this.icon = '♛';
		}
	}
	public boolean isLegalMove(Move move, boolean chopping) {

		return true;
	}

}