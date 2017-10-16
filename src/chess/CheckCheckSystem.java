package chess;
import chess.pieces.*;

public class CheckCheckSystem {
    int RANK_SIZE = 8;
    int FILE_SIZE = 8;

    

    public Piece[][] clone_board(Piece [][] original_board) {
        Piece [][]clonedBoard = new Piece[RANK_SIZE][FILE_SIZE];
        for(int i = 0; i < RANK_SIZE; i++) {
            for(int j = 0; j < FILE_SIZE; j++) {
                if(original_board[i][j] == null)
                    clonedBoard[i][j] = null;
                else clonedBoard[i][j] = original_board[i][j];
            }
        } 
        return clonedBoard;
    }

    public GameCode check_check(Piece[][] board, Controller controller, int gameMoveNumber) {
        Piece [][]clonedBoard = clone_board(board);

        Square thisColorKingPosition = controller.find_this_color_king(board, 
                                                                gameMoveNumber);

        Square allOppositeFigures[] = controller.find_all_opposite_figures(board,
                                                                gameMoveNumber);
        
        for(Square opFigPosition : allOppositeFigures) {
            

            Piece opPiece = clonedBoard[opFigPosition.rank][opFigPosition.file];
            Piece ourKing = clonedBoard[thisColorKingPosition.rank][thisColorKingPosition.file];
            //System.out.println(opPiece + " " + opFigPosition);   

            Move moveVariant = new Move(opFigPosition, thisColorKingPosition);
            GameCode potentialCheckStatus;
            potentialCheckStatus = controller.check_move(moveVariant, gameMoveNumber + 1, 
                                                        clonedBoard, opPiece, ourKing);
            
            
            
            if(potentialCheckStatus == GameCode.OK)
                return GameCode.CHECK;
            
                
        }
        return GameCode.NO_CHECK;
    }

    private GameCode check_mate(Piece [][]board, Controller controller, int gameMoveNumber) {
        // Кто то передвинул фигуру и стал шах...
        // сейчас очередь того, на кого напали
        Piece [][]clonedBoard = clone_board(board);

        Square thisColorKingPosition = controller.find_this_color_king(board, 
                                                                gameMoveNumber);
                                                                    // + 1 тк ищем opposite

        Square allThisFigures[] = controller.find_all_opposite_figures(board,
                                                                gameMoveNumber + 1);
        /*
        for(Square thisFig : allThisFigures) {
            for(int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    //aimSquare = 
                    GameCode moveStatus;
                    moveStatus = controller.check_move(move, this.gameMoveNumber, 
                                                clonedBoard, thisFig, aimSquare);
        
                }
            }
            Square allOppositeFigures[] = controller.find_all_opposite_figures(board,
                                                                gameMoveNumber);    
        }
        // реализовать метод все возможных ходов
        */
    	
        return GameCode.OK;
    }
}