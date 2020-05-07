package me.study.jpatodo.board.dto;

import lombok.Getter;
import lombok.Setter;
import me.study.jpatodo.board.domain.Board;

@Getter
@Setter
public class BoardCreateResponse {
    private Long id;
    private String title;

    public BoardCreateResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
    }
}
