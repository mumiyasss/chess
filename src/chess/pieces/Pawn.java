package chess.pieces;
import chess.*;


public class Pawn extends Piece {
	
	public Pawn(Color color) {
		this.icon = '♙';
		this.color = color;

		this.PAWN = true;

		if (color == Color.WHITE) {
			this.icon = '♟';
		}
	}

	public Square[] path(Move move) {
		int y_i = move.FROM.rank;
		int y = move.TO.rank;
		int x_i = move.FROM.file;     
		int x = move.TO.file;

		int arrayLength = Math.abs(y_i - y) - 1;
		int arrayIterator = 0;
		Square[] pathArr = new Square[arrayLength];
		while(arrayIterator < arrayLength) {
			if (y_i < y)
				y_i++; else y_i--;
			pathArr[arrayIterator] = new Square(x_i, y_i);
			arrayIterator++;
		}
		return pathArr;
	}

 
	// TODO or check Chessboard; think about it
	@Override
        public boolean isLegalMove(Move move, boolean chopping) {
		//
		// Описание ходов по правилам		
		//	
		// TODO можно ли перегружать как виртуальную функцию?
		// (идет повторение элементов)
		int relativeRankFrom = move.FROM.rank + 1; // TODO Позиция относительно игрока
		int relativeRankTo = move.TO.rank + 1;     // (какой цвет?)
		int relativeFileFrom = move.FROM.file;     // TODO исправить
		int relativeFileTo = move.TO.file;         // постоянное + 1
		
		if (this.color == Color.BLACK) { 
			// Инвертируем строки для черных как для белых 
			relativeRankFrom = 8 - relativeRankFrom + 1;
			relativeRankTo = 8 - relativeRankTo + 1;
		}

		//
		// Рассматриваем движение вперед
		//
		if (relativeRankFrom == 2) { // Если пешка стоит вначале
			// Вначале можно ходить только на 1 или 2 клетки вперед
			if (relativeRankTo != 3 && relativeRankTo != 4) {  
                return false; 		
			}
			
		} else if (relativeRankFrom != (relativeRankTo - 1)) {
			// Можно ходить только на одну клетку вперед
			return false;
		}

		//
		// Рассматриваем движение в сторону
		// 
		if(chopping == false) {
			if (relativeFileFrom != relativeFileTo)
				return false;
		} else
		// Смотрим чтобы сдвиг в сторону был только на одну клетку
		if (Math.abs(relativeFileFrom - relativeFileTo) != 1) 
			return false;
		

		return true; // И только тогда возывращаем true
	}

}



