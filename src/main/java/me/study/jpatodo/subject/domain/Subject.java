package me.study.jpatodo.subject.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.study.jpatodo.card.domain.Card;
import me.study.jpatodo.common.entity.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "subject_id")
    private Long id;
    private String title;
    @OneToMany(mappedBy = "subject")
    private List<Card> cards = new ArrayList<>();

    public Subject(String title) {
        this.title = title;
    }
}
