package me.study.jpatodo.board.api;

import me.study.jpatodo.board.application.BoardService;
import me.study.jpatodo.board.dto.BoardRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardService boardService;

    @DisplayName("보드 생성")
    @Test
    void createBoard() throws Exception {
        mockMvc.perform(post(BoardController.BOARD_URI).param("title", "board1"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("보드 조회 - 전체")
    @Test
    void findBoardAll() throws Exception {
        mockMvc.perform(get(BoardController.BOARD_URI)
                .param("offset", "0")
                .param("pageSize", "10"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("보드 조회 - 단건")
    @Test
    void findBoard() throws Exception {
        BoardRequest.CreateRequest createRequest = new BoardRequest.CreateRequest();
        createRequest.setTitle("title");
        boardService.create(createRequest);

        mockMvc.perform(get(BoardController.BOARD_URI + "/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").exists());
    }

    @DisplayName("보드 조회 - 단건 - 정보가 존재하지 않을시 에러")
    @Test
    void findBoardFail() throws Exception {
        mockMvc.perform(get(BoardController.BOARD_URI + "/0"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
