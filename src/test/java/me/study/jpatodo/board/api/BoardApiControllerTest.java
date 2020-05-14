package me.study.jpatodo.board.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.study.jpatodo.board.application.BoardService;
import me.study.jpatodo.board.dto.BoardRequest;
import me.study.jpatodo.board.dto.BoardRequest.CreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
class BoardApiControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardService boardService;

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("보드 생성")
    @Test
    void createBoard() throws Exception {
        String title = "board1";
        CreateRequest createRequest = new CreateRequest();
        createRequest.setTitle(title);

        mockMvc.perform(post(BoardApiController.BOARD_URI)
                    .content(objectMapper.writeValueAsString(createRequest))
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-board",
                        requestFields(
                                fieldWithPath("title").description("제목")
                        )));
    }

    @DisplayName("보드 조회 - 전체")
    @Test
    void findBoardAll() throws Exception {
        mockMvc.perform(get(BoardApiController.BOARD_URI)
                .param("offset", "0")
                .param("pageSize", "10"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("보드 조회 - 단건")
    @Test
    void findBoard() throws Exception {
        CreateRequest createRequest = new CreateRequest();
        createRequest.setTitle("title");
        boardService.create(createRequest);

        mockMvc.perform(get(BoardApiController.BOARD_URI + "/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").exists());
    }

    @DisplayName("보드 조회 - 단건 - 정보가 존재하지 않을시 에러")
    @Test
    void findBoardFail() throws Exception {
        mockMvc.perform(get(BoardApiController.BOARD_URI + "/0"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
