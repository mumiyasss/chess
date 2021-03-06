

package chess.exceptions;

/**
 * IllegalMoveException
 */
public class IllegalMoveException extends Exception {
    String errorMessage;

    public IllegalMoveException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return "Не правильный ход: " + errorMessage;
    }
}