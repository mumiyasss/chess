

package chess;


public class Chessboard {

	public static final int BOARD_SIZE = 8;

	// String pieces = "♔♕♖♗♘♙♚♛♜♝♞♟"; just characters
	/*
	 * USE ONLY SQUARE CLASS TO ACCES TO CHESSBOARD
	 */
	Piece[][] board; // package default

	final static char[]	files = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
	final static int[] 	ranks = {8, 7, 6, 5, 4, 3, 2, 1};


	
	public Chessboard() {
		this.board = new Piece[BOARD_SIZE][BOARD_SIZE];
	}


	public void clear() {
		this.board = new Piece[BOARD_SIZE][BOARD_SIZE];
	}


	public void setup() {


		for (char f : files) {
			this.set(new Pawn(Color.WHITE), new Square(2, f));
		}


		for (char f : files) {
			this.set(new Pawn(Color.BLACK), new Square(7, f));
		}

	}


	// add a piece to board
	public void set(Piece piece, Square position) {
		this.board[position.rank][position.file] = piece;
	}


	// returns Piece Object from position
	public Piece get(Square position) {
		return this.board[position.rank][position.file];
	}


	// removes Piece from the board on position
	public void remove(Square position) {
		this.board[position.rank][position.file] = null;
	}


	// perform a move
	public void move(Square from, Square to) {
		this.set(this.get(from), to);
		this.remove(from);
	}


	// returns pseudo graphical visualization
	public String toString() {
		StringBuilder builder = new StringBuilder(200); // size of output

		String sep = " ";
		String cor = "‧"; // corner character

		builder.append(cor);

		for (char c : files) {
			builder.append(sep + c);
		}

		builder.append(sep + cor + "\n");

		for (int rank : ranks) {
			builder.append(rank);

			for (char file : files) {
				builder.append(sep);

				Square cell = new Square(rank, file);

				if (this.get(cell) != null) {
					builder.append(this.get(cell).toString());
				} else {
					builder.append(cell.toString());
				}
			}

			builder.append(sep + rank + "\n");
		}

		builder.append(cor);

		for (char c : files) {
			builder.append(sep + c);
		}

		builder.append(sep + cor);

		return builder.toString();
	}


	// TODO isLegalMove(); or think about it
}