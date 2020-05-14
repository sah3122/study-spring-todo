package me.study.jpatodo.board.api;

import lombok.RequiredArgsConstructor;
import me.study.jpatodo.board.application.BoardService;
import me.study.jpatodo.board.dto.BoardRequest.CreateRequest;
import me.study.jpatodo.board.dto.BoardResponse.BoardDto;
import me.study.jpatodo.board.dto.BoardResponse.CreateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
public class BoardApiController {
    static final String BOARD_URI = "/board";

    private final BoardService boardService;

    @PostMapping(BOARD_URI)
    public ResponseEntity<CreateResponse> create(@RequestBody CreateRequest createRequest) {
        CreateResponse createResponse = boardService.create(createRequest);
        Link link = linkTo(BoardApiController.class).slash(BOARD_URI + "/" + createResponse.getId()).withSelfRel();
        URI createdUri = link.toUri();
        createResponse.add(link.withRel("update"));
        createResponse.add(new Link("/docs/index.html#resources-board-create"));
        return ResponseEntity.created(createdUri)
                .body(createResponse);
    }

    @GetMapping(BOARD_URI)
    public ResponseEntity<Page<BoardDto>> findAll(Pageable pageable) {
        Page<BoardDto> boardDtos = boardService.findAll(pageable);
        return ResponseEntity.ok(boardDtos);
    }

    @GetMapping(BOARD_URI + "/{id}")
    public ResponseEntity<BoardDto> findOne(@PathVariable Long id) {
        BoardDto boardDto = boardService.findOne(id);
        return ResponseEntity.ok(boardDto);
    }

}
