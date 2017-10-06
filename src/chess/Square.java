

package chess;


public class Square {
	final public int rank, file;

	public Square(char file, int rank) {
		this.rank = rank - 1;

		if (file >= 'a') {
			file -= 32;
		}
		this.file = file - 'A';
	}

	public String draw() {
		return (this.rank + this.file) % 2 == 0 ? "■" : "□";
	}

	public String toString() {
		return "" + (char) (file + 'a') + (rank + 1);
	}
}
