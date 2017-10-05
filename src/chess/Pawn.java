

package chess;


public class Pawn extends Piece {
	
	public Pawn(Color color) {
		this.icon = '♙';

		if (color == Color.WHITE) {
			this.icon = '♟';
		}
	}

	// TODO or check Chessboard; think about it
	public boolean isLegalMove(Square from, Square to) {
		return true;
	}

}



