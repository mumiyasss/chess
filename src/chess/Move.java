

package chess;

// Describes a move
public class Move {
	final public Square SOURCE;
	final public Square DESTINATION;

	public Move(Square from, Square to) {
		this.SOURCE			= from;
		this.DESTINATION	= to;
	}

	public String toString() {
		return this.SOURCE + " " + this.DESTINATION;
	}
}
