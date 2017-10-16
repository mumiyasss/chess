/*
	This class is responsible for gameplay.
*/

package chess;
import chess.pieces.*;


public class Controller {
	
	/*
	protected static int gameMoveNumber; // Номер хода

	public Controller() {
		gameMoveNumber = 1; // Объявляем начало игры, первый ход
	}
	*/
	public Square find_this_color_king(Piece[][] board, int gameMoveNumber) {
        Color thisColor = gameMoveNumber % 2 == 1 ? Color.WHITE : Color.BLACK;
        Square king = null;
        for(int rank = 0; rank < 8; rank++) {
            for(int file = 0; file < 8; file++) {
                if (board[rank][file] != null) {
	                Piece p = board[rank][file];
	                if(p.get_color() == thisColor && p.KING)
	                    king = new Square(file, rank);
            	}
            }
        }
        return king;
	}

	public Square[] find_all_opposite_figures(Piece[][] board, int gameMoveNumber) {
        Color oppositeColor = gameMoveNumber % 2 == 1 ? Color.BLACK : Color.WHITE;
        int opFiguresQuantity = 0;
        int figIterator = 0;
        
        for(int rank = 0; rank < 8; rank++) {
            for(int file = 0; file < 8; file++) {
                if (board[rank][file] != null) {
                    Piece p = board[rank][file];
                    if(p.get_color() == oppositeColor)
                        opFiguresQuantity++;
            	}
            }
        }
        
        Square []oppositeFigures = new Square[opFiguresQuantity];

        for(int rank = 0; rank < 8; rank++) {
            for(int file = 0; file < 8; file++) {
            	if (board[rank][file] != null) {
                    Piece p = board[rank][file];
                    if(p.get_color() == oppositeColor) {
                        oppositeFigures[figIterator] = new Square(file, rank);
                        figIterator++;
                    }
                }
            }
        }
        
        return oppositeFigures;
    }
        

            
 

	public GameCode check_move(Move move, int gameMoveNumber, Piece[][] board,
                                        Piece this_piece, Piece aimSquare) {
		
		//Square this_piece_position = move.get_from_square(); // Дубляж объявления
		//Square aim_position = move.get_to_square();

		boolean chopping = false;
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
				chopping = true;
				
			}
		} 

		// Может ли так ходить фигура?
		if(this_piece.isLegalMove(move, chopping)) { 

			// Проверка на барьер
			for(Square step : this_piece.path(move)) {
				if (board[step.rank][step.file] != null)
					return GameCode.ILLEGAL_4;
			}

			gameMoveNumber++; // Следующий ход
			return GameCode.OK;
		} else {

			return GameCode.ILLEGAL_3;
		}


	}

}