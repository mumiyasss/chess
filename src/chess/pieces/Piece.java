package chess.pieces;
import chess.*;

abstract public class Piece {
	protected char icon;
	protected Color color;
    public boolean KING;
    public boolean PAWN;
        
	abstract public boolean isLegalMove(Move move, boolean chopping);  
	abstract public Square[] path(Move move);

	public String toString() {
		return Character.toString(icon);
	}
	
	public Color get_color() {
		return color;
	}


}


