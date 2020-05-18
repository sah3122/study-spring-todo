package me.study.jpatodo.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.study.jpatodo.board.domain.Board;
import me.study.jpatodo.common.BaseRequestDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BoardRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class CreateRequest implements BaseRequestDto<Board> {
        @NotBlank
        private String title;

        @Override
        public Board toEntity() {
            return Board.builder()
                    .title(title)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UpdateRequest implements BaseRequestDto<Board> {
        @NotNull(message = "필수값입니다.")
        private Long id;
        @NotBlank
        private String title;

        @Override
        public Board toEntity() {
            return Board.builder()
                    .title(title)
                    .id(id)
                    .build();
        }
    }
}
