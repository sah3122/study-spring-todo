package me.study.jpatodo.board.handler;

import lombok.extern.slf4j.Slf4j;
import me.study.jpatodo.board.domain.error.BoardNotFoundException;
import me.study.jpatodo.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice("me.study.jpatodo.board")
public class BoardExceptionHadler {
    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<ErrorResponse> boardNotFoundException(BoardNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(exception.getTitle(), exception.getMessage()));
    }
}
