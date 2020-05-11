package me.study.jpatodo.board.domain;

import me.study.jpatodo.board.dto.BoardResponse;
import me.study.jpatodo.board.dto.BoardResponse.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {
    Page<BoardDto> findPageAll(Pageable pageable);
}
