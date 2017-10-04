package chess;

public class Chess { 
    public static void main(String[] args) {
        Bishop bish1 = new Bishop();
        King king1 = new King();
        
        Figure[][] board = new Figure[8][8];
        board[0][0] = bish1;
        board[1][1] = king1;
        board[2][2] = null;
        System.out.println(board[0][0]);
        System.out.println(board[2][2]);
        System.out.println(board[1][1]);
    }
}

class Figure {
    int x;
}

class Bishop extends Figure {
    int y;
    
    public String toString() {
        return "Bishop.toStr";
    }
}

class King extends Figure {
    int z;
}
