

package chess;


public enum Color {
	BLACK(0), WHITE(1);

	int intColorImplementation;

	Color(int c) {
		this.intColorImplementation = c;
	}

	public int toInt() {
		return this.intColorImplementation;
	}

	public String toString() {
		return this.intColorImplementation == 1 ? "white" : "black";
	}
}

