package me.study.jpatodo.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.study.jpatodo.subject.domain.Subject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id @GeneratedValue
    private Long id;
    private String title;
    @OneToMany
    @JoinColumn(name = "subject_id")
    private List<Subject> subjects = new ArrayList<>();

    public Board(String title) {
        this.title = title;
    }
}
