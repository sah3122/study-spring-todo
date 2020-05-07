package me.study.jpatodo.board.dto;

import lombok.Getter;
import lombok.Setter;
import me.study.jpatodo.board.domain.Board;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BoardCreateRequest {
    @NotBlank
    private String title;

    public Board toEntity() {
        return new Board(title);
    }
}
