package chess.pieces;
import chess.*;

public class Bishop extends Piece {
	public Bishop(Color color) {
		this.icon = '♗';
		this.color = color;

		if (color == Color.WHITE) {
			this.icon = '♝';
		}
	}
	public boolean isLegalMove(Move move) {

		return true;
	}

}