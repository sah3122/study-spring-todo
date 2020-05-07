package me.study.jpatodo.subject.dto;

import lombok.Getter;
import lombok.Setter;
import me.study.jpatodo.board.domain.Board;
import me.study.jpatodo.common.BaseRequestDto;
import me.study.jpatodo.subject.domain.Subject;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class SubjectCreateRequest implements BaseRequestDto<Subject> {
    @NotBlank
    private String title;
    private Board board;

    @Override
    public Subject toEntity() {
        return new Subject(title, board);
    }
}
