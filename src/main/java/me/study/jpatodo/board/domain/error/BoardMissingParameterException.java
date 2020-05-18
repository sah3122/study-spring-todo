package me.study.jpatodo.board.domain.error;

public class BoardMissingParameterException extends IllegalArgumentException {
    private final String message;

    public BoardMissingParameterException(String message) {
        super(message);
        this.message = message;
    }
}
