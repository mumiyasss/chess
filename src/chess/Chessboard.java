package chess;
import chess.pieces.*;

public class Chessboard {

	protected static final int BOARD_SIZE = 8;
 	protected static int moveCount = 1; // Номер хода  
	// String pieces = "♔♕♖♗♘♙ ♚♛♜♝♞♟"; just characters
	/*
	 * USE ONLY SQUARE CLASS TO ACCES TO CHESSBOARD
	 */
	protected Piece[][] board; // package default

	final static char[] files = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
	final static  int[] ranks = {8, 7, 6, 5, 4, 3, 2, 1};


	
	public Chessboard() {
		this.board = new Piece[BOARD_SIZE][BOARD_SIZE];
	}

	public void setup() {
		/// Передаются кординаты в метод set()
		
		// Расставляем белые фигуры
		for (char f : files) {
			this.set(new Pawn(Color.WHITE), new Square(f, 2));
		}
		this.set(new   King(Color.WHITE), new Square('E', 1));
		this.set(new  Queen(Color.WHITE), new Square('D', 1));
		this.set(new Bishop(Color.WHITE), new Square('C', 1));
		this.set(new Bishop(Color.WHITE), new Square('F', 1));
		this.set(new Knight(Color.WHITE), new Square('B', 1));
		this.set(new Knight(Color.WHITE), new Square('G', 1));
		this.set(new   Rook(Color.WHITE), new Square('A', 1));
		this.set(new   Rook(Color.WHITE), new Square('H', 1));
				
		// Расставляем чёрные фигуры
		for (char f : files) {
			this.set(new Pawn(Color.BLACK), new Square(f, 7));
		}
		this.set(new   King(Color.BLACK), new Square('E', 8));
		this.set(new  Queen(Color.BLACK), new Square('D', 8));
		this.set(new Bishop(Color.BLACK), new Square('C', 8));
		this.set(new Bishop(Color.BLACK), new Square('F', 8));
		this.set(new Knight(Color.BLACK), new Square('B', 8));
		this.set(new Knight(Color.BLACK), new Square('G', 8));
		this.set(new   Rook(Color.BLACK), new Square('A', 8));
		this.set(new   Rook(Color.BLACK), new Square('H', 8));
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
	public void remove(Square position) {
		this.board[position.RANK][position.FILE] = null;
	}


	// perform a move
	public void move(Move move) throws Exception {

		// TODO check if move is legal
		Square currentPosition = move.SOURCE;
		
		Piece movingPiece = 
			this.board[currentPosition.RANK][currentPosition.FILE];
		// TODO Создать функцию обработчик (вынести все это)	
		if((moveCount % 2 == 1 && movingPiece.get_color() == Color.BLACK) ||
			(moveCount % 2 == 0 && movingPiece.get_color() == Color.WHITE)) {
				System.out.println("ILLEGAL11111");
				// !!! throw new IllegalMoveException(movingPiece + "cannot move like this");
		} else if(!movingPiece.isLegalMove(move)) {
			System.out.println(movingPiece);
			System.out.println("ILLEGAL"); // TODO change to exception or smth
			// !!! throw new IllegalMoveException(movingPiece + "cannot move like this");
		} else {
			this.set(this.get(move.SOURCE), move.DESTINATION);
			this.remove(move.SOURCE);	
			this.moveCount++; // Следующий ход
		}
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


	// TODO isLegalMove(); or think about it
}
