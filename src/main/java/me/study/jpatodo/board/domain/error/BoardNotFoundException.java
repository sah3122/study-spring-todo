package me.study.jpatodo.board.domain.error;

import lombok.Getter;

@Getter
public class BoardNotFoundException extends IllegalArgumentException {
    private final String code;
    private final String title;
    private final String message;

    public BoardNotFoundException(String code, String title, String message) {
        super(message);
        this.code = code;
        this.title = title;
        this.message = message;
    }
}
