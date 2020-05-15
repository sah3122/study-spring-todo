package me.study.jpatodo.auth.application;

import lombok.RequiredArgsConstructor;
import me.study.jpatodo.auth.domain.Account;
import me.study.jpatodo.auth.domain.AccountRepository;
import me.study.jpatodo.auth.domain.UserAccount;
import me.study.jpatodo.auth.dto.AccountRequest.AccountCreateRequest;
import me.study.jpatodo.auth.dto.AccountResponse;
import me.study.jpatodo.auth.dto.AccountResponse.AccountCreateResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AccountCreateResponse saveAccount(AccountCreateRequest accountCreateRequest) {
        Account account = accountCreateRequest.toEntity();
        account.encodePassword(passwordEncoder);
        account.makeRoleUser();

        Account newAccount = accountRepository.save(account);
        return new AccountCreateResponse(newAccount);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        return new UserAccount(account);
    }
}
