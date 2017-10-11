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
	public boolean isLegalMove(Move move, boolean chopping) {
		//
		// Описание ходов по правилам		
		//	
		// TODO можно ли перегружать как виртуальную функцию?
		// (идет повторение элементов)
		
		int relativeRankFrom = move.FROM.rank + 1; // TODO Позиция относительно игрока
		int relativeRankTO = move.TO.rank + 1;     // (какой цвет?)
		int relativeFileFrom = move.FROM.file;     // TODO исправить
		int relativeFileTo = move.TO.file;         // постоянное + 1
		
		if (relativeRankFrom == 2) { // Если пешка стоит вначале
			// Вначале можно ходить только на 1 или 2 клетки вперед
			if (relativeRankTO != 3 && relativeRankTO != 4) {  
                            System.out.println("NOW-1");
                            return false; 		
			}
		} else if (relativeRankFrom != (relativeRankTO - 1)) {
			// Можно ходить только на одну клетку вперед
                        System.out.println("NOW-2");
			return false;
		}
		 
		
		if (move.FROM.rank == 7) {
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

