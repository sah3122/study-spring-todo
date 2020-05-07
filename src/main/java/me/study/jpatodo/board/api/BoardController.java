package me.study.jpatodo.board.api;

import lombok.RequiredArgsConstructor;
import me.study.jpatodo.board.application.BoardService;
import me.study.jpatodo.board.dto.BoardCreateRequest;
import me.study.jpatodo.board.dto.BoardCreateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseEntity<BoardCreateResponse> create(BoardCreateRequest boardCreateRequest) {
        BoardCreateResponse boardCreateResponse = boardService.create(boardCreateRequest);
        return ResponseEntity.created(URI.create("/card/" + boardCreateResponse.getId()))
                .body(boardCreateResponse);
    }
}
