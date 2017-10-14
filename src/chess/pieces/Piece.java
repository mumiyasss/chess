

package chess.pieces;

import chess.*;

abstract public class Piece {
	protected char icon;
	protected Color color;

	abstract public boolean isLegal(Move move, Chessboard board);  

	public String toString() {
		return Character.toString(icon);
	}
	
	public Color getColor() {
		return color;
	}

	protected int sign(int x) {
		return x == 0 ? 0 : (x < 0 ? -1 : 1);
	}
}

