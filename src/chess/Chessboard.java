package chess;
import chess.pieces.*;

public class Chessboard {

	protected static final int BOARD_SIZE = 8;
 	protected static int gameMoveNumber = 1; // Номер хода  
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

		// Сначала ставим все ячейки в null
		for (char f : files) {
			for (int r : ranks) {
				this.remove(new Square(f, r));
			}
		}
		// Теперь расставляем белые фигуры
		for (char f : files) {
			this.set(new Pawn(Color.WHITE), new Square(f, 2));
		}
		this.set(new King(Color.WHITE), new Square('E', 1));
		this.set(new Queen(Color.WHITE), new Square('D', 1));
		this.set(new Bishop(Color.WHITE), new Square('C', 1));
		this.set(new Bishop(Color.WHITE), new Square('F', 1));
		this.set(new Knight(Color.WHITE), new Square('B', 1));
		this.set(new Knight(Color.WHITE), new Square('G', 1));
		this.set(new Rook(Color.WHITE), new Square('A', 1));
		this.set(new Rook(Color.WHITE), new Square('H', 1));
				
		// Расставляем чёрные фигуры
		for (char f : files) {
			this.set(new Pawn(Color.BLACK), new Square(f, 7));
		}
		this.set(new King(Color.BLACK), new Square('E', 8));
		this.set(new Queen(Color.BLACK), new Square('D', 8));
		this.set(new Bishop(Color.BLACK), new Square('C', 8));
		this.set(new Bishop(Color.BLACK), new Square('F', 8));
		this.set(new Knight(Color.BLACK), new Square('B', 8));
		this.set(new Knight(Color.BLACK), new Square('G', 8));
		this.set(new Rook(Color.BLACK), new Square('A', 8));
		this.set(new Rook(Color.BLACK), new Square('H', 8));
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
	// Вместо фигурки ставит null
	public void remove(Square position) {
		this.board[position.rank][position.file] = null;
	}


	// perform a move
	public void move(Move move) throws Exception {
		Square this_piece_position = move.get_from_square();
		Square aim_position = move.get_to_square();

		Piece this_piece = 
			this.board[this_piece_position.rank][this_piece_position.file];
		
		Piece aimSquare = 
			this.board[aim_position.rank][aim_position.file];

		// TODO Создать функцию обработчик (вынести все это)	
		System.out.println(move.FROM.rank);
		 // Ходит ли нужный цвет?
		if ((gameMoveNumber % 2 == 1 && this_piece.get_color() == Color.BLACK) ||
			(gameMoveNumber % 2 == 0 && this_piece.get_color() == Color.WHITE)) {
				System.out.println("ILLEGAL-1");
				//System.out.println(gameMoveNumber);
				//System.out.println(gameMoveNumber % 2);
				//System.out.println(this_piece.get_color());
		} else 
		// Если что-то стоит на клетке
		if (aimSquare != null) {
			Piece aimPiece = aimSquare; // Значит это уже не просто клетка, а фигура
			// Проверяем, того же ли цвета фигура на новой клетке(своих рубить нельзя) 
			if ((gameMoveNumber % 2 == 1 && aimPiece.get_color() == Color.WHITE) ||
				(gameMoveNumber % 2 == 0 && aimPiece.get_color() == Color.BLACK)) {
				System.out.println("ILLEGAL-2");
			} else
			{
				// Будем рубить!
				// Заменить true/false
				if(this_piece.isLegalMove(move, true)) {}
			}
		} else
		// Может ли так ходить фигура?
		if(this_piece.isLegalMove(move, false)) { 
			this.set(this.get(move.get_from_square()), aim_position);
			this.remove(this_piece_position);	 // ISSUE не будет работать рокировка
			gameMoveNumber++; // Следующий ход

		} else {
			
			System.out.println(this_piece);
			System.out.println("ILLEGAL-3"); // TODO change to exception or smth
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
