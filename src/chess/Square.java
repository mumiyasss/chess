

package chess;


public class Square {
	final public int rank, file;

	public Square(int rank, char file) {
		this.rank = rank - 1;
		this.file = file - 'A';
	}

	public int rank() {
		return this.rank;
	}

	public int file() {
		return this.file;
	}

	public String toString() {
		return (this.rank + this.file) % 2 == 0 ? "■" : "□";
	}
}
