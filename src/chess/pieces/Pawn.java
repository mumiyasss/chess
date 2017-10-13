

package chess.pieces;

import chess.*;

public class Pawn extends Piece {
	
	public Pawn(Color color) {
		this.color = color;

		if (color == Color.WHITE) {
			this.icon = '♟';
		} else {
			this.icon = '♙';
		}
	}

	public boolean isLegal(Move move, Chessboard board) {

		int startRank = this.color.toBool() ? 1 : 6;
		int allowedStep = this.color.toBool() ? 1 : -1;

		int rankOffset = move.DESTINATION.RANK - move.SOURCE.RANK;
		int fileOffset = Math.abs(move.SOURCE.FILE - move.DESTINATION.FILE);

		// allowed to move front by 1 cell (from non-start position)
		if (move.SOURCE.RANK != startRank && rankOffset != allowedStep) {
			return false;
		}

		// allowed to jump 1 or 2 cells from a start position
		if (fileOffset == 0
				&& move.SOURCE.RANK == startRank 
				&& allowedStep != rankOffset
				&& 2 * allowedStep != rankOffset) {
			return false;
		}

		// file offset allowed only by 1 position // 1 if chopping else 0
		if (fileOffset > 1) {
			return false;
		}

		// if chopping, allowed to move front by 1 cell
		if (fileOffset == 1 && rankOffset != allowedStep) {
			return false;
		}

		// if going to offset by file so there must be a enemy's piece
		if (fileOffset == 1 && board.get(move.DESTINATION) == null) {
			return false;
		}

		// cannot chop piece the same color
		if (fileOffset == 1 && color == board.get(move.DESTINATION).getColor()) {
			return false;
		}

		// cannot chop moving forward
		if (fileOffset == 0 && board.get(move.DESTINATION) != null) {
			return false;
		}

		return true;

	}
}

