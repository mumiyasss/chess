

package chess;


public class Pawn extends Piece {
	
	public Pawn(Color color) {
		this.icon = '♙';
		this.color = color;

		if (color == Color.WHITE) {
			this.icon = '♟';
		}
	}

	// TODO or check Chessboard; think about it
	public boolean isLegalMove(Move move) {
	
		return false;
	}

}

// TO KOLYA
/*
	is_legal_move возвращаю true или false
	is_legal_move проверяет все!

*/

