package me.study.jpatodo.auth.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserAccount extends User {
    private final Account account;

    public UserAccount(Account account) {
        super(account.getEmail(), account.getPassword(), generateAuthorities(account.getRoles()));
        this.account = account;
    }

    private static Collection<? extends GrantedAuthority> generateAuthorities(Set<AccountRole> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
}
