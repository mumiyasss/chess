package chess;

import chess.pieces.*;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;


public class Chessboard {
    // String pieces = "♔♕♖♗♘♙ ♚♛♜♝♞♟"; just characters
    /*
     * USE ONLY SQUARE CLASS TO ACCES THE CHESSBOARD
     */

    protected static final int RANK_SIZE = 8;
    protected static final int FILE_SIZE = 8;
    protected static Controller controller;
    protected static CheckCheckSystem checkCheckSystem;
    protected static Controller check_system_controller;
    protected static int gameMoveNumber; // Номер хода

    private GameHistory history;


    
    protected Piece[][] board; // Наша доска

    final static char[] files = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    final static  int[] ranks = {8, 7, 6, 5, 4, 3, 2, 1};


    
    public Chessboard() {
        this.board = new Piece[RANK_SIZE][FILE_SIZE];
        controller = new Controller(); // Инициализация контроллера 
        checkCheckSystem = new CheckCheckSystem();
        gameMoveNumber = 1;
        history = new GameHistory();
    }

    public void setup() {

        // ctrl + C
        this.board = new Piece[RANK_SIZE][FILE_SIZE];
        controller = new Controller(); // Инициализация контроллера 
        gameMoveNumber = 1;
        history = new GameHistory();
        // ctrl + V


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
    public void setup(GameHistory history) throws IOException {
        this.setup();
        this.history = history;
        Move[] moves = this.history.getMoves();
        for (Move m : moves) {
            // try {
                this.set(this.get(m.FROM), m.TO);
                this.remove(m.FROM);
                // this.move(m);
            // } catch (IllegalMoveException e) {
            //     throw new IOException("History is corrupted.");
            // }
        }
        this.gameMoveNumber = history.moveCount() + 1;
    }


    // add a piece to board, also removes piece before adding 
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
    public Move cancelLastMove() throws EmptyHistoryException, IOException {
        Move lastMove = this.history.pop(); // EXCEPTION HERE

        this.setup(history);

        this.gameMoveNumber = this.history.moveCount() + 1;

        return lastMove;
    }

    public void save() throws IOException {
        this.history.log();
    }

    public void pawn_to_queen(Color color, Move move, 
                                    Square thisPiecePosition, Square aimPosition) {
        this.remove(thisPiecePosition);    // ISSUE не будет работать рокировка
        this.set(new  Queen(color), thisPiecePosition);
        this.set(this.get(thisPiecePosition), aimPosition);
        this.remove(thisPiecePosition);    // ISSUE не будет работать рокировка
        this.history.add(move);
    }
    
    // perform a move
    // THIS METHOD IS TOO BIG. IT SHOULD BE DIVIDED.
    public GameCode move(Move move) throws IllegalMoveException {

        Square thisPiecePosition = move.get_from_square();
        Square aimPosition = move.get_to_square();     

        Piece thisPiece = 
            board[thisPiecePosition.rank][thisPiecePosition.file];
        
        Piece aimSquare = 
            board[aimPosition.rank][aimPosition.file];

        

        GameCode moveStatus;
        moveStatus = controller.check_move(move, this.gameMoveNumber, 
                                                board, thisPiece, aimSquare);
        

        switch (moveStatus) {
            case OK:
                // Делаем ферзя
                if (thisPiecePosition.rank == 6 && thisPiece.PAWN && aimPosition.rank == 7) {
                    pawn_to_queen(Color.WHITE, move, thisPiecePosition, aimPosition);
                } else
                if (thisPiecePosition.rank == 1 && thisPiece.PAWN && aimPosition.rank == 0) {
                    pawn_to_queen(Color.BLACK, move, thisPiecePosition, aimPosition);
                } else {
                    // Возможно это надо вынести в отдельный метод
                    this.set(this.get(thisPiecePosition), aimPosition);
                    this.remove(thisPiecePosition);    // ISSUE не будет работать рокировка
                    this.history.add(move);
                }
                int gameMoveNumberSAVE = gameMoveNumber; // ПИЗДЕЦ НАХУЙ КАКОЙ КОСТЫЛЬ
                                                        // Я ТАКИХ КОСТЫЛЕЙ В ЖИЗНИ НЕ ДЕЛАЛ.
                                                        // 
                                                        // P.S всё из-за того что cancelLastMove()
                                                        // меняет gameMoveNumber
                                                        // P.P.S TODO: исправить 
                
                if (checkCheckSystem.check_check(board,
                        controller, gameMoveNumber) == GameCode.CHECK) {
                    try {
                        cancelLastMove(); 
                        gameMoveNumber = gameMoveNumberSAVE; // Восстанавливаем номер хода
                    } catch (EmptyHistoryException h) {
                        System.out.println(h);
                    } catch (IOException io) {
                        System.out.println(io);
                    }

                    throw new IllegalMoveException(thisPiece + " нельзя передвинуть на " 
                                + aimPosition + ", потому что вам поставлен шах!");
                }

                gameMoveNumber++; // делаем следующий ход

                 // Если есть шах, то предупредим и проверим на мат.
                if (checkCheckSystem.check_check(board,
                                    controller, gameMoveNumber) == GameCode.CHECK) {
                        System.out.println("Шах!"); // TODO: Заменить это на нормальный вывод
                }
             

                break;
            case ILLEGAL_1:
                throw new IllegalMoveException("Фигурка " + thisPiece + "  на " 
                        + thisPiecePosition + " не ваша.");
            case ILLEGAL_2:
                throw new IllegalMoveException(thisPiece + " не может рубить на клетке " + aimPosition);
            case ILLEGAL_3:
                throw new IllegalMoveException(thisPiece + " нельзя передвинуть на " + aimPosition);
            case ILLEGAL_4:
                throw new IllegalMoveException(thisPiece + " нельзя передвинуть на " + aimPosition +
                                                    ", так как существует препятствие на пути.");
        }

        return GameCode.NO_CHECK;
    }


    // returns pseudo graphical visualization
    public String toString() {
        StringBuilder builder = new StringBuilder(200); // size of output

        String sep = " ";
        String cor = "🙾"; // corner character

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
