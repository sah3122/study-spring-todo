package me.study.jpatodo.subject.api;

import lombok.RequiredArgsConstructor;
import me.study.jpatodo.subject.application.SubjectService;
import me.study.jpatodo.subject.domain.Subject;
import me.study.jpatodo.subject.dto.SubjectCreateRequest;
import me.study.jpatodo.subject.dto.SubjectCreateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class SubjectApiController {
    private final SubjectService subjectService;

    @PostMapping("/subject")
    public ResponseEntity<SubjectCreateResponse> create(@Valid SubjectCreateRequest subjectCreateRequest) {
        SubjectCreateResponse subjectCreateResponse = subjectService.create(subjectCreateRequest);
        return ResponseEntity.created(URI.create("/subject" + subjectCreateResponse.getId()))
                .body(subjectCreateResponse);
    }
}
