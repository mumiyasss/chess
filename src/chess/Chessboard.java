

package chess;


public class Chessboard {

	public static final int BOARD_SIZE = 8;

	// String pieces = "♔♕♖♗♘♙♚♛♜♝♞♟"; just characters
	Piece[][] board; // package default

	final static char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

	
	public Chessboard() {
		board = new Piece[8][8];
	}

	// add a piece to board
	public void set(Piece piece, Position position) {
		board[position.x()][position.y()] = piece;
	}

	// returns pseudo graphical visualization
	public String toString() {
		StringBuilder builder = new StringBuilder(200); // size of output

		String sep = " ";
		String cor = "‧"; // corner character

		builder.append(cor);
		for (char c : alpha) {
			builder.append(sep + c);
		}
		builder.append(sep + cor + "\n");

		for (int i = 0; i < BOARD_SIZE; i++) {
			builder.append(BOARD_SIZE - i);
			for (int j = 0; j < BOARD_SIZE; j++) {
				builder.append(sep);
				if (this.board[i][j] != null) {
					builder.append(this.board[i][j].toString());
				} else {
					builder.append(
						(i + j) % 2 == 1 ? '⬜' : '⬛'
					);
				}
			}
			builder.append(sep + (BOARD_SIZE - i) + "\n");
		}
		builder.append(cor);
		for (char c : alpha) {
			builder.append(sep + c);
		}
		builder.append(sep + cor);

		return builder.toString();
	}
}