

package chess;

// Describes a move
// Две пары координат
public class Move {
	final public Square from;
	final public Square to;

	public Move(Square from, Square to) {
		this.from = from;
		this.to = to;
	}

	public String toString() {
		return from + " " + to;
	}
}
