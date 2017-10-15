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

	public Square[] path(Move move) {
        int y_i = move.FROM.rank;
        int y = move.TO.rank;
        int x_i = move.FROM.file;     
        int x = move.TO.file;

        int arrayLength;
        if (y_i != y)
           arrayLength = Math.abs(y_i - y) - 1;
        else arrayLength = Math.abs(x_i - x) - 1;

        int arrayIterator = 0;
        Square[] pathArr = new Square[arrayLength];
        while(arrayIterator < arrayLength) {
            if (x_i < x)
                x_i++; else 
            if (x_i > x) 
                x_i--;
            
            if (y_i < y)
                y_i++; else 
            if (y_i > y)
                y_i--;

            pathArr[arrayIterator] = new Square(x_i, y_i);
            arrayIterator++;
        }
        return pathArr;
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