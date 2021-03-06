package me.study.jpatodo.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private final String title;
    private final String message;

    public ErrorResponse(String message) {
        this("에러가 발생하였습니다." ,message);
    }

    public ErrorResponse(String title, String message) {
        this.title = title;
        this.message = message;
    }
}
