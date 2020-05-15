package me.study.jpatodo.auth.dto;

import lombok.Getter;
import lombok.Setter;
import me.study.jpatodo.auth.domain.Account;

public class AccountResponse {
    @Getter
    @Setter
    public static class AccountCreateResponse {
        private String nickname;

        private String email;

        public AccountCreateResponse(Account account) {

            this.nickname = account.getNickname();
            this.email = account.getEmail();
        }
    }
}
