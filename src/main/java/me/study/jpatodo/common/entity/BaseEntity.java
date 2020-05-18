package me.study.jpatodo.common.entity;

import lombok.Getter;
import me.study.jpatodo.auth.domain.UserAccount;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;

@MappedSuperclass
@Getter
public class BaseEntity extends BaseTimeEntity {
    @Column(updatable = false)
    private Long createdBy;
    private Long lastUpdatedBy;

    @PrePersist
    public void prePersist() {
        SecurityContext context = SecurityContextHolder.getContext();
        UserAccount userAccount = (UserAccount) context.getAuthentication().getPrincipal();
        createdBy = userAccount.getAccount().getId();
        lastUpdatedBy = userAccount.getAccount().getId();
    }

    @PreUpdate
    public void preUpdate() {
        SecurityContext context = SecurityContextHolder.getContext();
        UserAccount userAccount = (UserAccount) context.getAuthentication().getPrincipal();
        lastUpdatedBy = userAccount.getAccount().getId();
    }
}
