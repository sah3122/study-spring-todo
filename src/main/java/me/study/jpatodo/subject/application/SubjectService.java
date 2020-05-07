package me.study.jpatodo.subject.application;

import lombok.RequiredArgsConstructor;
import me.study.jpatodo.subject.domain.Subject;
import me.study.jpatodo.subject.domain.SubjectRepository;
import me.study.jpatodo.subject.dto.SubjectCreateRequest;
import me.study.jpatodo.subject.dto.SubjectCreateResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Transactional
    public SubjectCreateResponse create(SubjectCreateRequest subjectCreateRequest) {
        Subject savedSubject = subjectRepository.save(subjectCreateRequest.toEntity());
        return new SubjectCreateResponse(savedSubject);
    }
}
