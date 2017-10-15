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

	public Square[] path(Move move) {
		Square[] pathArr = new Square[0];
	
		return pathArr;
	}

	@Override
	public boolean isLegalMove(Move move, boolean chopping) {
		int relativeRankFrom = move.FROM.rank;
		int relativeRankTo = move.TO.rank;
		int relativeFileFrom = move.FROM.file;     
		int relativeFileTo = move.TO.file;

		if (Math.abs(relativeFileTo - relativeFileFrom) == 2) {
			if (Math.abs(relativeRankFrom - relativeRankTo) == 1) {
				return true;
			} 
		} else 
		if (Math.abs(relativeRankTo - relativeRankFrom) == 2) {
			if (Math.abs(relativeFileFrom - relativeFileTo) == 1) {
				return true;
			} 
		}

		return false;
	}

}