package me.study.jpatodo.subject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.study.jpatodo.board.domain.Board;
import me.study.jpatodo.card.domain.Card;
import me.study.jpatodo.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Subject extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "subject_id")
    private Long id;
    private String title;
    @OneToMany(mappedBy = "subject")
    private List<Card> cards = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public Subject(String title, Board board) {
        this.title = title;
        this.board = board;
    }
}
