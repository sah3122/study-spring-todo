package me.study.jpatodo.auth.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.study.jpatodo.auth.dto.AccountRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static me.study.jpatodo.auth.dto.AccountRequest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("유저 생성")
    @Test
    void createAccount() throws Exception {
        AccountCreateRequest accountCreateRequest = new AccountCreateRequest();
        accountCreateRequest.setEmail("dongchul@gmail.com");
        accountCreateRequest.setNickname("dongchul");
        accountCreateRequest.setPassword("password");

        mockMvc.perform(post("/sign-up")
                .content(objectMapper.writeValueAsString(accountCreateRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("nickname").exists())
                .andExpect(jsonPath("email").exists());
    }
}
