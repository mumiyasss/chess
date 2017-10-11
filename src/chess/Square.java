

package chess;

// Клеточка
public class Square {
	// TODO закоментировать этот класс
	final public int RANK;
	final public int FILE;

	public Square(char file, int rank) {
		this.RANK = rank - 1;

		if (file >= 'a') { // UpperCase
			file -= 32;
		}
		this.FILE = file - 'A'; // Получение числа
	}

	public String draw() {
		return (this.RANK + this.FILE) % 2 == 0 ? "■" : "□";
	}

	public String toString() { // 
		return "" + (char) (this.FILE + 'a') + (this.RANK + 1);
	}
}
