package me.study.jpatodo.card.domain;

import lombok.*;
import me.study.jpatodo.common.entity.BaseTimeEntity;
import me.study.jpatodo.subject.domain.Subject;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Card extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @NotBlank
    private String description;
    private LocalDateTime dueDate;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
