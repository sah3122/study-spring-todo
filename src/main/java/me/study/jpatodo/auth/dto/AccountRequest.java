package me.study.jpatodo.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.study.jpatodo.auth.domain.Account;
import me.study.jpatodo.common.BaseRequestDto;

import javax.persistence.Column;

public class AccountRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class AccountCreateRequest implements BaseRequestDto<Account> {
        private String nickname;

        private String email;

        private String password;

        @Override
        public Account toEntity() {
            return new Account(nickname, email, password);
        }
    }
}
