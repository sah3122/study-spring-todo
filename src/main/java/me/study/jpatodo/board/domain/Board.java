package me.study.jpatodo.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.study.jpatodo.subject.domain.Subject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id @GeneratedValue
    private Long id;
    private String title;
    @OneToMany(mappedBy = "board")
    private List<Subject> subjects = new ArrayList<>();

    public Board(String title) {
        this.title = title;
    }
}
