

package chess;


abstract public class Piece {
	protected char icon;


	abstract boolean isLegalMove(Square from, Square to);

	public String toString() {
		return Character.toString(icon);
	}
}


