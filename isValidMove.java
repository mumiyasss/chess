

package chess;

// bla bla bla


public class Chessboard {


	// ...

	// perform a move
	public void move(Move move) throws IllegalMoveException {
		
		Square source      = move.get_from_square(); // SHOULD BE move.SOURCE
		Square destination = move.get_to_square();   // SHOULD BE move.DESTINATION

		Piece movingPiece = this.get(source);
		Piece target = this.get(destination);

		// Can the player move this piece
		if (gameMoveNumber % 2 != movingPiece.get_color().toInt()) {
			// if not throw exc
			throw new IllegalMoveException("Piece " + movingPiece + " at " + source + " is not yours.");
		}

		// player tries to chop his own piece
		if (movingPiece.get_color() == target.get_color()) {
			// if true throw exc
			throw new IllegalMoveException(movingPiece + " cannot chop " + target " at " + destination);
		}

		// if could the piece even get there?
		if (!movingPiece.isLegal(move, this.board)) {
			// if not throw exc
		// Should be implemented in each piece class
			throw new IllegalMoveException(movingPiece + " at " + source + " cannot get to " + destination);
		}

		// only if all conditions are satisfied
		this.set(movingPiece, destination);
		this.removea(source);

	}

	// ...

}


