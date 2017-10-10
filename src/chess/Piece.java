

package chess;


abstract public class Piece {
	protected char icon;
	protected Color color;

	abstract boolean isLegalMove(Move move);

	public String toString() {
		return Character.toString(icon);
	}
}


