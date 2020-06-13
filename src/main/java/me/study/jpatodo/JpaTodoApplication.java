package me.study.jpatodo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.study.jpatodo.auth.application.AccountService;
import me.study.jpatodo.auth.domain.AccountRepository;
import me.study.jpatodo.auth.dto.AccountRequest;
import me.study.jpatodo.auth.dto.AccountRequest.AccountCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;

@EnableJpaAuditing
@SpringBootApplication
public class JpaTodoApplication {

    @Autowired
    private AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(JpaTodoApplication.class, args);
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        AccountCreateRequest accountCreateRequest = new AccountCreateRequest();
        accountCreateRequest.setNickname("dong1");
        accountCreateRequest.setEmail("dong1@gmail.com");
        accountCreateRequest.setPassword("password");
        accountService.saveAccount(accountCreateRequest);
    }
}
