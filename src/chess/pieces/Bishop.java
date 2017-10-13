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
	public boolean isLegalMove(Move move, boolean chopping) {
		int relativeRankFrom = move.FROM.rank;
		int relativeRankTo = move.TO.rank;
		int relativeFileFrom = move.FROM.file;     
		int relativeFileTo = move.TO.file;

		if (Math.abs(relativeFileTo - relativeFileFrom) !=
			Math.abs(relativeRankTo -  relativeRankFrom)) {
			return false;
		}
		
		return true;
	}

}