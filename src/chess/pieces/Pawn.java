package chess.pieces;
import chess.*;

public class Pawn extends Piece {
	
	public Pawn(Color color) {
		this.icon = '♙';
		this.color = color;

		if (color == Color.WHITE) {
			this.icon = '♟';
		}
	}

	// TODO or check Chessboard; think about it
	public boolean isLegalMove(Move move) {
		//
		// Описание ходов по правилам		
		//	
		// TODO можно ли перегружать как виртуальную функцию?
		// (идет повторение элементов)
		
		int relativeRankFrom = move.SOURCE.RANK; // TODO Позиция относительно игрока
		int relativeRankTO = move.DESTINATION.RANK;     // (какой цвет?)
		int relativeFileFrom = move.SOURCE.FILE;
		int relativeFileTo = move.DESTINATION.FILE;
		
		if (relativeRankFrom == 2) { // Если пешка стоит вначале
			// Вначале можно ходить только на 1 или 2 клетки вперед
			if (relativeRankTO != 3 && relativeRankTO != 4) {  
				
				return false; 		
			}
		} else if (relativeRankFrom != (relativeRankTO - 1)) {
			// Можно ходить только на одну клетку вперед
			
			return false;
		}
		 
		
		if (move.DESTINATION.RANK == 7) {
			// TODO описать создание новой фигуры
		}
		
		return true; // И только тогда возывращаем true
	}

}

// TO KOLYA
/*
	is_legal_move возвращаю true или false
	is_legal_move проверяет все!

*/

