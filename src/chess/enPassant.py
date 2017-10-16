


# attempt to check if en_passant is possible
def en_passant(move, chessboard):

	# Rank where en passant happens 
	toRank = 2 if move.get(move.TO).getColor().toBool() else 5;

	# where is target Pawn relative to toRank 
	offset = 1 if move.get(move.TO).getColor().toBool() else -1;

	# Square where en passant happens (assuming)
	possibleDestination = new Square(move.TO.file, toRank); # in the middle of move

	# checking if attacking piece get to Square where en passant happens
	if (possibleDestination != move.TO):
		return False;

	# checking if the last move is Pawn's and it jumped over a square, like a2 a4
	moves = history.getMoves(); # the method really exist
	lastMove = moves(len(moves) - 1);

	if lastMove != new Move(new Square(move.TO.file, toRank + 1), new Square(move.TO.file, toRank - 1)):
		return False;


	# perform a move
	# and after do:
	chessboard.remove(new Square(move.TO.file, toRank + offset));
	return True;


