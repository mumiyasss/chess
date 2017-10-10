

package chess;

// Describes a move
// Две пары координат
public class Move {
	final public Square FROM;
	final public Square TO;

	public Move(Square from, Square to) {
		this.FROM = from;
		this.TO = to;
	}
	
	public Square get_from_square() {
		return FROM;
	}
	
	public Square get_to_square() {
		return TO;
	}

	public String toString() {
		return FROM + " " + TO;
	}
}
