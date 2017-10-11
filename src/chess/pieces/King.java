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
        @Override // Почему не обязательно?
	public boolean isLegalMove(Move move, boolean chopping) {

		return true;
	}

}
