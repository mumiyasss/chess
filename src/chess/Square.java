

package chess;

// Square / Position / Cell
// Pair of coordinates
public class Square {
	final public int FILE;
	final public int RANK;

	public Square(int file, int rank) {
		this.FILE = file;
		this.RANK = rank;
	}


	public Square(char file, int rank) {
		if (file >= 'a') {
			file -= 32; // to UpperCase
		}
		this.FILE = file - 'A'; // to int

		this.RANK = rank - 1;
	}

	public Color getColor() {
		return (this.RANK + this.FILE) % 2 == 0 ? Color.WHITE : Color.BLACK;
	}

	public String draw() {
		return (this.RANK + this.FILE) % 2 == 0 ? "■" : "□";
	}

	public String toString() { // 
		return "" + (char) (this.FILE + 'a') + (this.RANK + 1);
	}
}
