package me.study.jpatodo.board.domain.validator;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.study.jpatodo.board.application.BoardService;
import me.study.jpatodo.board.domain.error.BoardMissingParameterException;
import me.study.jpatodo.board.dto.BoardRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;

import static me.study.jpatodo.board.dto.BoardRequest.*;

@Component
@RequiredArgsConstructor
public class BoardUpdateValidator implements Validator {
    private final BoardService boardService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UpdateRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            ObjectError objectError = errors.getAllErrors().stream()
                    .findFirst()
                    .orElseThrow(IllegalAccessError::new);
            throw new BoardMissingParameterException(objectError.getDefaultMessage());
        }
    }
}
