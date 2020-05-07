package me.study.jpatodo.subject.dto;

import lombok.Getter;
import lombok.Setter;
import me.study.jpatodo.subject.domain.Subject;

@Getter
@Setter
public class SubjectCreateResponse {
    private Long id;
    private String title;

    public SubjectCreateResponse(Subject savedSubject) {
        this.id = savedSubject.getId();
        this.title = savedSubject.getTitle();
    }
}
