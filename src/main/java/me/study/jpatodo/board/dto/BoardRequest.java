package me.study.jpatodo.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.study.jpatodo.board.domain.Board;
import me.study.jpatodo.common.BaseRequestDto;

import javax.validation.constraints.NotBlank;

public class BoardRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class CreateRequest implements BaseRequestDto<Board> {
        @NotBlank
        private String title;

        @Override
        public Board toEntity() {
            return new Board(title);
        }
    }
}
