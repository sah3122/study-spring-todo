package me.study.jpatodo.board.domain;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.study.jpatodo.board.dto.BoardResponse;
import me.study.jpatodo.board.dto.BoardResponse.BoardDto;
import me.study.jpatodo.board.dto.QBoardResponse_BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static me.study.jpatodo.board.domain.QBoard.board;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<BoardDto> findPageAll(Pageable pageable) {
        QueryResults<BoardDto> results = jpaQueryFactory.select(new QBoardResponse_BoardDto(board))
                .from(board)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<BoardDto> contents = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }
}
