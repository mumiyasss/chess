package chess.pieces;
import chess.*;

public class Knight extends Piece {
	public Knight(Color color) {
		this.icon = '♘';
		this.color = color;

		if (color == Color.WHITE) {
			this.icon = '♞';
		}
	}
	public boolean isLegalMove(Move move) {

		return true;
	}

}