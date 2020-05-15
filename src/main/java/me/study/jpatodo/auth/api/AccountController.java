package me.study.jpatodo.auth.api;

import lombok.RequiredArgsConstructor;
import me.study.jpatodo.auth.application.AccountService;
import me.study.jpatodo.auth.dto.AccountRequest.AccountCreateRequest;
import me.study.jpatodo.auth.dto.AccountResponse.AccountCreateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/sign-up")
    public ResponseEntity<AccountCreateResponse> signUp(@RequestBody AccountCreateRequest accountCreateRequest) {
        AccountCreateResponse accountCreateResponse = accountService.saveAccount(accountCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountCreateResponse);
    }
}
