package me.study.jpatodo.card.application;

import lombok.RequiredArgsConstructor;
import me.study.jpatodo.card.domain.Card;
import me.study.jpatodo.card.domain.CardRepository;
import me.study.jpatodo.card.dto.CardCreateRequest;
import me.study.jpatodo.card.dto.CardCreateResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public CardCreateResponse createCard(CardCreateRequest cardCreateRequest) {
        Card savedCard = cardRepository.save(cardCreateRequest.toEntity());
        return new CardCreateResponse(savedCard.getId());
    }
}
