

package chess;


abstract public class Piece {
	protected char icon;

	abstract boolean isLegalMove(Position destination, Position source);

	public String toString() {
		return Character.toString(icon);
	}
}


