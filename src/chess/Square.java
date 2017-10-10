

package chess;

// Клеточка
public class Square {
	// TODO закоментировать этот класс
	final public int rank, file;

	public Square(char file, int rank) {
		this.rank = rank - 1;

		if (file >= 'a') { // UpperCase
			file -= 32;
		}
		this.file = file - 'A'; // Получение числа
	}

	public String draw() {
		return (this.rank + this.file) % 2 == 0 ? "■" : "□";
	}

	public String toString() { // 
		return "" + (char) (file + 'a') + (rank + 1);
	}
}
