package chess.pieces;
import chess.*;

public class King extends Piece {
	public King(Color color) {
		this.icon = '♔';
		this.color = color;

		if (color == Color.WHITE) {
			this.icon = '♚';
		}
	}
	public boolean isLegalMove(Move move) {

		return true;
	}

}
