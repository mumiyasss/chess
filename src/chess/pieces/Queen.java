package chess.pieces;
import chess.*;

public class Queen extends Piece {
	public Queen(Color color) {
		this.icon = '♕';
		this.color = color;

		if (color == Color.WHITE) {
			this.icon = '♛';
		}
	}
	public boolean isLegalMove(Move move, boolean chopping) {
		int relativeRankFrom = move.FROM.rank;
		int relativeRankTo = move.TO.rank;
		int relativeFileFrom = move.FROM.file;     
		int relativeFileTo = move.TO.file;

		//
		// Свойства слона 
		//
		if (Math.abs(relativeFileTo - relativeFileFrom) ==
			Math.abs(relativeRankTo -  relativeRankFrom)) {
			return true;
		}

		//
		// Свойства ладьи
		//
		if (((Math.abs(relativeFileTo - relativeFileFrom) == 0) &&
			 (Math.abs(relativeRankTo - relativeRankFrom) != 0)) 
																||
																
			 ((Math.abs(relativeRankTo - relativeRankFrom) == 0) &&
			 (Math.abs(relativeFileTo - relativeFileFrom) != 0)))
				return true;
		
		return false;
	}

}