package me.study.jpatodo.board.application;

import lombok.RequiredArgsConstructor;
import me.study.jpatodo.board.domain.Board;
import me.study.jpatodo.board.domain.BoardRepository;
import me.study.jpatodo.board.dto.BoardRequest;
import me.study.jpatodo.board.dto.BoardRequest.CreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static me.study.jpatodo.board.dto.BoardResponse.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public CreateResponse create(CreateRequest createRequest) {
        Board board = createRequest.toEntity();
        Board savedBoard = boardRepository.save(board);
        return new CreateResponse(savedBoard);
    }

    public Page<BoardDto> findAll(Pageable pageable) {
        return boardRepository.findPageAll(pageable);
    }
}
