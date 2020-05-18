package me.study.jpatodo.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.study.jpatodo.common.entity.BaseEntity;
import me.study.jpatodo.common.entity.BaseTimeEntity;
import me.study.jpatodo.subject.domain.Subject;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany
    @JoinColumn(name = "subject_id")
    private List<Subject> subjects = new ArrayList<>();

    private Long createdBy;
    private LocalDateTime createdDate;

    public Board(String title) {
        this.title = title;
    }
}
