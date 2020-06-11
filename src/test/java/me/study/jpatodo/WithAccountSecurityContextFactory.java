package me.study.jpatodo;

import lombok.RequiredArgsConstructor;
import me.study.jpatodo.auth.application.AccountService;
import me.study.jpatodo.auth.dto.AccountRequest;
import me.study.jpatodo.auth.dto.AccountRequest.AccountCreateRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.lang.annotation.Annotation;

@RequiredArgsConstructor
public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithAccount> {

    private final AccountService accountService;

    @Override
    public SecurityContext createSecurityContext(WithAccount withAccount) {

        String username = withAccount.value();
        String email = username + "@gmail.com";
        String password = "password";

        AccountCreateRequest accountCreateRequest = new AccountCreateRequest();
        accountCreateRequest.setEmail(email);
        accountCreateRequest.setPassword(password);
        accountCreateRequest.setNickname(username);

        accountService.saveAccount(accountCreateRequest);

        UserDetails userDetails = accountService.loadUserByUsername(email);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }
}
