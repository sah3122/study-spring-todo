package me.study.jpatodo.card.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.study.jpatodo.common.entity.BaseEntity;
import me.study.jpatodo.subject.domain.Subject;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
