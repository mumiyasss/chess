

package chess;


public class Pawn extends Piece {
	
	public Pawn(Color color) {
		this.icon = '♙';

		if (color == Color.WHITE) {
			this.icon = '♟';
		}
	}

	// TODO
	public boolean isLegalMove(Position destination, Position source) {
		return true;
	}

}



