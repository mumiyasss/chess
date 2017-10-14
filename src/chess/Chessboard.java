222package chess;
import chess.pieces.*;

public class Chessboard {

	public static final int BOARD_SIZE = 8;
 	int moveCount = 1; // PACKAGE DEFAULT
	// String pieces = "♔♕♖♗♘♙ ♚♛♜♝♞♟"; just characters

	/*
	 * USE ONLY SQUARE CLASS TO ACCES TO CHESSBOARD
	 */
	Piece[][] board; // package default

	final static char[] files = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
	final static  int[] ranks = {8, 7, 6, 5, 4, 3, 2, 1};


	
	public Chessboard() {
		this.board = new Piece[BOARD_SIZE][BOARD_SIZE];
	}

	// default chessboard setup
	public void setup() {
		// white pieces
		for (char file : files) {
			this.set(new Pawn(Color.WHITE), new Square(file, 2));
		}
		this.set(new   King(Color.WHITE), new Square('E', 1));
		this.set(new  Queen(Color.WHITE), new Square('D', 1));
		this.set(new Bishop(Color.WHITE), new Square('C', 1));
		this.set(new Bishop(Color.WHITE), new Square('F', 1));
		this.set(new Knight(Color.WHITE), new Square('B', 1));
		this.set(new Knight(Color.WHITE), new Square('G', 1));
		this.set(new Rook(Color.WHITE), new Square('A', 1));
		this.set(new Rook(Color.WHITE), new Square('H', 1));
				
		// black pieces
		for (char file : files) {
			this.set(new Pawn(Color.BLACK), new Square(file, 7));
		}
		this.set(new   King(Color.BLACK), new Square('E', 8));
		this.set(new  Queen(Color.BLACK), new Square('D', 8));
		this.set(new Bishop(Color.BLACK), new Square('C', 8));
		this.set(new Bishop(Color.BLACK), new Square('F', 8));
		this.set(new Knight(Color.BLACK), new Square('B', 8));
		this.set(new Knight(Color.BLACK), new Square('G', 8));
		this.set(new Rook(Color.BLACK), new Square('A', 8));
		this.set(new Rook(Color.BLACK), new Square('H', 8));
	}

	// LOAD CHESSBOARD TO CONTINUE THE GAME
	public void setup(GameHistory history) {
		this.setup();
		for (Move m : history.logList) {
			try {
				this.move(m);
			} catch (IllegalMoveException e) {
				System.out.println(e + "History is corrupted.");
			}
		}
	}

	// add a piece to board
	public void set(Piece piece, Square position) {
		this.board[position.RANK][position.FILE] = piece;
	}

	// returns Piece Object from position
	public Piece get(Square position) {
		return this.board[position.RANK][position.FILE];
	}

	// removes Piece from the board on position
	// Вместо фигурки ставит null
	public void removeFrom(Square position) {
		this.board[position.RANK][position.FILE] = null;
	}

	public Piece get(int i, int j) {
		return this.board[i][j];
	}


	// perform a move
	public void move(Move move) throws IllegalMoveException {
		
		Square source		= move.SOURCE;
		Square destination	= move.DESTINATION;

		Piece movingPiece	= this.get(source);
		Piece target		= this.get(destination);

		if (movingPiece == null) {
			throw new IllegalMoveException(
				"there is no piece at " + source
			);
		}

		// Can the player move this piece
		if (this.moveCount % 2 != movingPiece.getColor().toInt()) {
			// if not throw exc
			throw new IllegalMoveException(
				"Piece " + movingPiece + " at " + source + " is not yours."
			);
		}

		// player tries to chop his own piece
		if (target != null && movingPiece.getColor() == target.getColor()) {
			// if true throw exc
			throw new IllegalMoveException(
				movingPiece + " cannot chop " + target
				+ " as they are the same color."
			);
		}

		// if could the piece even get there?
		if (!movingPiece.isLegal(move, this)) {
			// if not throw exc
			throw new IllegalMoveException(
				movingPiece + " at " + source
				+ " cannot get to " + destination
			);
		}

		// only if all conditions are satisfied
		this.set(movingPiece, destination);
		this.removeFrom(source);
		this.moveCount++;
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

				Square cell = new Square(file, rank);

				if (this.get(cell) != null) {
					builder.append(this.get(cell).toString());
				} else {
					builder.append(cell.draw());
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
}
