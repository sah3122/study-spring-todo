package me.study.jpatodo.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.study.jpatodo.board.domain.Board;

import javax.validation.constraints.NotBlank;

public class BoardRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class CreateRequest {
        @NotBlank
        private String title;

        public Board toEntity() {
            return new Board(title);
        }
    }
}
