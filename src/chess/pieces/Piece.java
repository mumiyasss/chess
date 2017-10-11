package chess.pieces;
import chess.*;

abstract public class Piece {
	protected char icon;
	protected Color color;

	abstract public boolean isLegalMove(Move move);  

	public String toString() {
		return Character.toString(icon);
	}
	
	public Color get_color() {
		return color;
	}
}


