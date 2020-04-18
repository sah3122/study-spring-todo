package me.study.jpatodo.card.dto;

import lombok.Getter;
import lombok.Setter;
import me.study.jpatodo.card.domain.Card;
import me.study.jpatodo.subject.domain.Subject;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
public class CardCreateRequest {

    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Subject subject;

    public Card toEntity() {
        return Card.builder()
                .title(title)
                .description(description)
                .dueDate(dueDate)
                .subject(subject)
                .build();

    }
}
