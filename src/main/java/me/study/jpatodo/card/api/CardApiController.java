package me.study.jpatodo.card.api;

import lombok.RequiredArgsConstructor;
import me.study.jpatodo.card.application.CardService;
import me.study.jpatodo.card.dto.CardCreateRequest;
import me.study.jpatodo.card.dto.CardCreateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CardApiController {

    private final CardService cardService;

    @PostMapping("/card")
    public ResponseEntity<CardCreateResponse> create(CardCreateRequest cardCreateRequest) {
        CardCreateResponse cardCreateResponse = cardService.createCard(cardCreateRequest);
        return ResponseEntity.created(URI.create("/card/" + cardCreateResponse.getId()))
                .body(cardCreateResponse);
    }
}
