package me.study.jpatodo.auth.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.study.jpatodo.auth.application.AccountService;
import me.study.jpatodo.auth.config.AppProperties;
import me.study.jpatodo.auth.dto.AccountRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static me.study.jpatodo.auth.dto.AccountRequest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private AccountService accountService;

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

    @Test
    @DisplayName("인증 토큰을 발급 받는 테스트")
    @Transactional
    public void getAuthToken() throws Exception {
        String email = "dongchul2@gmail.com";
        String password = "password";

        AccountCreateRequest accountCreateRequest = new AccountCreateRequest();
        accountCreateRequest.setEmail(email);
        accountCreateRequest.setNickname("dongchul2");
        accountCreateRequest.setPassword(password);

        accountService.saveAccount(accountCreateRequest);

        mockMvc.perform(post("/oauth/token")
                .with(httpBasic(appProperties.getClientId(), appProperties.getClientSecret()))
                .param("username", email)
                .param("password", password)
                .param("grant_type", "password"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists());
    }

    @Test
    @DisplayName("등록되지 않은 계정으로 토큰 발급시 에러")
    @Transactional
    public void getAuthTokenFail() throws Exception {
        String email = "dongchul@gmail.com";
        String password = "password";

        mockMvc.perform(post("/oauth/token")
                .with(httpBasic(appProperties.getClientId(), appProperties.getClientSecret()))
                .param("username", email)
                .param("password", password)
                .param("grant_type", "password"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("error").value("invalid_grant"));
    }
}
