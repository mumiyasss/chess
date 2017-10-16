

package chess;

public class EmptyHistoryException extends Exception {
	String errorMessage;

    public EmptyHistoryException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return "EmptyHistoryException: " + errorMessage;
    }
}