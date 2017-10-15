package chess.pieces;
import chess.*;

public class King extends Piece {
	final public boolean KING = true;

	public King(Color color) {
		this.icon = '♔';
		this.color = color;

		if (color == Color.WHITE) {
			this.icon = '♚';
		}
	}

	public Square[] path(Move move) {
		Square[] pathArr = new Square[0];
		

		return pathArr;
	}
    
    @Override // Почему не обязательно?
	public boolean isLegalMove(Move move, boolean chopping) {
		int relativeRankFrom = move.FROM.rank;
		int relativeRankTo = move.TO.rank;
		int relativeFileFrom = move.FROM.file;     
		int relativeFileTo = move.TO.file;

		if ((Math.abs(relativeFileTo - relativeFileFrom) > 1) ||
			(Math.abs(relativeRankTo - relativeRankFrom) > 1)) {
			return false;
		}


		return true;
	}

}
