package me.study.jpatodo.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import me.study.jpatodo.board.domain.Board;
import me.study.jpatodo.subject.domain.Subject;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.List;
import java.util.stream.Collectors;

public class BoardResponse {

    @Getter
    @Setter
    public static class CreateResponse extends EntityModel<Board> {
        private Long id;
        private String title;

        public CreateResponse(Board board, Link... links) {
            id = board.getId();
            title = board.getTitle();
        }
    }

    @Getter
    @Setter
    public static class BoardDto {
        private String title;
        private List<Long> subjectIds;

        @QueryProjection
        public BoardDto(Board board) {
            title = board.getTitle();
            subjectIds = board.getSubjects().stream()
                    .map(Subject::getId)
                    .collect(Collectors.toList());
        }
    }
}
