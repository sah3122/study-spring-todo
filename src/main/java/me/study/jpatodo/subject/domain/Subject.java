package me.study.jpatodo.subject.domain;

import me.study.jpatodo.card.domain.Card;
import me.study.jpatodo.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @OneToMany(mappedBy = "subject")
    private List<Card> cards = new ArrayList<>();
}
