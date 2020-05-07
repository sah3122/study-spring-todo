package me.study.jpatodo.subject.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.study.jpatodo.board.domain.Board;
import me.study.jpatodo.board.domain.BoardRepository;
import me.study.jpatodo.subject.dto.SubjectCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SubjectApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BoardRepository boardRepository;

    @DisplayName("Subject 생성")
    @Test
    void create() throws Exception {
        Board board = new Board("board1");
        Board savedBoard = boardRepository.save(board);
        SubjectCreateRequest subjectCreateRequest = new SubjectCreateRequest();
        subjectCreateRequest.setTitle("title");
        subjectCreateRequest.setBoard(savedBoard);

        mockMvc.perform(post("/subject")
                    .content(objectMapper.writeValueAsString(subjectCreateRequest))
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
