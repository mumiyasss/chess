package chess;

import chess.pieces.*;

public class Chessboard {
    // String pieces = "♔♕♖♗♘♙ ♚♛♜♝♞♟"; just characters
    /*
     * USE ONLY SQUARE CLASS TO ACCES THE CHESSBOARD
     */

    protected static final int RANK_SIZE = 8;
    protected static final int FILE_SIZE = 8;
    protected static Controller controller;
    protected static Controller check_system_controller;
    protected static int gameMoveNumber; // Номер хода

    private GameHistory history;


    
    protected Piece[][] board; // package default

    final static char[] files = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    final static  int[] ranks = {8, 7, 6, 5, 4, 3, 2, 1};


    
    public Chessboard() {
        this.board = new Piece[RANK_SIZE][FILE_SIZE];
        controller = new Controller(); // Инициализация контроллера 
        gameMoveNumber = 1;
        history = new GameHistory();
    }

    public void setup() {
        /// Передаются кординаты в метод set()

        // Сначала ставим все ячейки в null
        for (char f : files) {
            for (int r : ranks) {
                this.remove(new Square(f, r));
            }
        }
        // Теперь расставляем белые фигуры
        for (char f : files) {
            this.set(new Pawn(Color.WHITE), new Square(f, 2));
        }
        this.set(new   King(Color.WHITE), new Square('E', 1));
        this.set(new  Queen(Color.WHITE), new Square('D', 1));
        this.set(new Bishop(Color.WHITE), new Square('C', 1));
        this.set(new Bishop(Color.WHITE), new Square('F', 1));
        this.set(new Knight(Color.WHITE), new Square('B', 1));
        this.set(new Knight(Color.WHITE), new Square('G', 1));
        this.set(new   Rook(Color.WHITE), new Square('A', 1));
        this.set(new   Rook(Color.WHITE), new Square('H', 1));
                
        // Расставляем чёрные фигуры
        for (char f : files) {
            this.set(new Pawn(Color.BLACK), new Square(f, 7));
        }
        this.set(new   King(Color.BLACK), new Square('E', 8));
        this.set(new  Queen(Color.BLACK), new Square('D', 8));
        this.set(new Bishop(Color.BLACK), new Square('C', 8));
        this.set(new Bishop(Color.BLACK), new Square('F', 8));
        this.set(new Knight(Color.BLACK), new Square('B', 8));
        this.set(new Knight(Color.BLACK), new Square('G', 8));
        this.set(new   Rook(Color.BLACK), new Square('A', 8));
        this.set(new   Rook(Color.BLACK), new Square('H', 8));
    }


    // LOAD CHESSBOARD TO CONTINUE THE GAME
    public void setup(GameHistory history) {
        this.setup();
        this.history = history;
        for (Move m : this.history.logList) {
            try {
                this.move(m);
            } catch (IllegalMoveException e) {
                System.out.println(e + "History is corrupted.");
            }
        }
    }


    // add a piece to board
    public void set(Piece piece, Square position) {
        this.board[position.rank][position.file] = piece;
    }

    // removes Piece from the board on position
    // Вместо фигурки ставит null
    public void remove(Square position) {
        this.board[position.rank][position.file] = null;
    }

    // returns Piece Object from position
    public Piece get(Square position) {
        return this.board[position.rank][position.file];
    }

    // CANCEL LAST MOVE
    public void cancelLastMove() throws Exception {
        Move cancellingMove = this.history.pop(); // EXCEPTION HERE
        // TODO Idk where to decrease moveCount
        this.set(this.get(cancellingMove.FROM), cancellingMove.TO);
        this.remove(cancellingMove.FROM);
        this.gameMoveNumber -= 1;
    }


    /*
    public Piece[][] clone_board(Piece [][] original_board) {
        Piece [][]clonedBoard = ;
        for(int i = 0; i < RANK_SIZE; i++) {
            for(int j = 0; j < FILE_SIZE; j++) {
                if(original_board[i][j] == null)
                    clonedBoard[i][j] = null;
                else clonedBoard[i][j] = original_board[i][j];
            }
        } 
        return cloned_board;
    }
    */

    // perform a move
    public void move(Move move) throws IllegalMoveException {
        Square this_piece_position = move.get_from_square();
        Square aim_position = move.get_to_square();     

        Piece this_piece = 
            board[this_piece_position.rank][this_piece_position.file];
        
        Piece aimSquare = 
            board[aim_position.rank][aim_position.file];

        Square thisColorKing = controller.find_this_color_king(this.board, 
                                                                this.gameMoveNumber);

        Square allOppositeFigures[] = controller.find_all_opposite_figures(this.board,
                                                                this.gameMoveNumber);

        GameCode moveStatus;
        moveStatus = controller.check_move(move, this.gameMoveNumber, 
                                                board, this_piece, aimSquare);
        
        
        /*
            clonedBoard = clone_board(board);
            
            for(Square opFig : allOppositeFigures) {
                Piece opPiece;
                Move moveVariant = new Move(apFig, thisColorKing)
                potentialCheckStatus = controller.check_move(moveVariant, this.gameMoveNumber, 
                                                            board, this_piece, aimSquare);
                    
            }
            */

        switch (moveStatus) {
            case OK:
                this.set(this.get(move.get_from_square()), aim_position);
                this.remove(this_piece_position);    // ISSUE не будет работать рокировка
                this.history.add(move);
                gameMoveNumber++; // делаем следующий ход
                break;
            case ILLEGAL_1:
                throw new IllegalMoveException("Piece " + this_piece + " at " 
                        + this_piece_position + " is not yours.");
            case ILLEGAL_2:
                throw new IllegalMoveException(this_piece + " cannot chop " + aim_position);
            case ILLEGAL_3:
                throw new IllegalMoveException(this_piece + " cannot move to " + aim_position);
            case ILLEGAL_4:
                throw new IllegalMoveException(this_piece + " cannot move to " + aim_position +
                                                    ", because there is a barrier.");
        }


        /*
        if (moveStatus == GameCode.OK) { 
            this.set(this.get(move.get_from_square()), aim_position);
            this.remove(this_piece_position);    // ISSUE не будет работать рокировка
            gameMoveNumber++; // делаем следующий ход
        } else
        if (moveStatus == GameCode.ILLEGAL_1) {

                    throw new IllegalMoveException("Piece " + this_piece + " at " 
                        + this_piece_position + " is not yours.");
        } else 
        if (moveStatus == GameCode.ILLEGAL_2) {
            throw new IllegalMoveException(this_piece + " cannot chop " + aim_position);
        } else 
        if (moveStatus == GameCode.ILLEGAL_3) {
            throw new IllegalMoveException(this_piece + " cannot move to " + aim_position);
        } else
        if (moveStatus == GameCode.ILLEGAL_4) {
            throw new IllegalMoveException(this_piece + " cannot move to " + aim_position +
                ", because there is a barrier.");
        }
        */
        // Вот тут проверим есть ли шах?
    }


    // returns pseudo graphical visualization
    public String toString() {
        StringBuilder builder = new StringBuilder(200); // size of output

        String sep = " ";
        String cor = "‧"; // corner character

        builder.append(cor);

        for (char c : files) {
            builder.append(sep + c);
        }

        builder.append(sep + cor + "\n");

        for (int rank : ranks) {
            builder.append(rank);

            for (char file : files) {
                builder.append(sep);

                Square cell = new Square(file, rank);

                if (this.get(cell) != null) {
                    builder.append(this.get(cell).toString());
                } else {
                    builder.append(cell.draw());
                }
            }

            builder.append(sep + rank + "\n");
        }

        builder.append(cor);

        for (char c : files) {
            builder.append(sep + c);
        }

        builder.append(sep + cor);

        return builder.toString();
    }
}
