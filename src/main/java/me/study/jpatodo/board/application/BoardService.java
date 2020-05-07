package me.study.jpatodo.board.application;

import lombok.RequiredArgsConstructor;
import me.study.jpatodo.board.domain.Board;
import me.study.jpatodo.board.domain.BoardRepository;
import me.study.jpatodo.board.dto.BoardCreateRequest;
import me.study.jpatodo.board.dto.BoardCreateResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardCreateResponse create(BoardCreateRequest boardCreateRequest) {
        Board board = boardCreateRequest.toEntity();
        Board savedBoard = boardRepository.save(board);
        return new BoardCreateResponse(savedBoard);
    }
}
