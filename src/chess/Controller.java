/*
	This class is responsible for gameplay.
*/

package chess;
import chess.pieces.*;


public class Controller {
	protected static int gameMoveNumber; // Номер хода

	public Controller() {
		gameMoveNumber = 1; // Объявляем начало игры, первый ход
	}

	public GameCode check_move(Move move, Piece[][] board,
                                        Piece this_piece, Piece aimSquare) {
		
		Square this_piece_position = move.get_from_square(); // Дубляж объявления
		Square aim_position = move.get_to_square();


		// Ходит ли нужный цвет?
		if (gameMoveNumber % 2 != this_piece.get_color().toInt()) {
                    
			return GameCode.ILLEGAL_1;
			
		} else 
		// Если что-то стоит на клетке
		if (aimSquare != null) {
			Piece aimPiece = aimSquare; // Значит это уже не просто клетка, а фигура
			// Проверяем, того же ли цвета фигура на новой клетке(своих рубить нельзя) 
			if ((gameMoveNumber % 2 == 1 && aimPiece.get_color() == Color.WHITE) ||
				(gameMoveNumber % 2 == 0 && aimPiece.get_color() == Color.BLACK)) {
			
				return GameCode.ILLEGAL_2;

			} else {
				// Будем рубить!
				if (this_piece.isLegalMove(move, true)) {}
			}
		} else
		// Может ли так ходить фигура?
		if(this_piece.isLegalMove(move, false)) { 
			gameMoveNumber++; // Следующий ход
			return GameCode.OK;
		} else {
			System.out.println(this_piece);
			return GameCode.ILLEGAL_3;
		}

            return GameCode.TEMP_CODE;
	}

}