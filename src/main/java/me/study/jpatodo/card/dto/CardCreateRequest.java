package me.study.jpatodo.card.dto;

import lombok.Getter;
import lombok.Setter;
import me.study.jpatodo.card.domain.Card;
import me.study.jpatodo.common.BaseRequestDto;
import me.study.jpatodo.subject.domain.Subject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class CardCreateRequest implements BaseRequestDto<Card> {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private LocalDateTime dueDate;
    @NotNull
    private Subject subject;

    @Override
    public Card toEntity() {
        return Card.builder()
                .title(title)
                .description(description)
                .dueDate(dueDate)
                .subject(subject)
                .build();

    }
}
