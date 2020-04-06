package me.study.jpatodo.card.domain;

import me.study.jpatodo.common.entity.BaseEntity;
import me.study.jpatodo.subject.domain.Subject;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Card extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;
}
