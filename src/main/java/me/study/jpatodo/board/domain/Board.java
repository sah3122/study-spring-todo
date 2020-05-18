package me.study.jpatodo.board.domain;

import lombok.*;
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
@Builder
@AllArgsConstructor
public class Board extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany
    @JoinColumn(name = "subject_id")
    private List<Subject> subjects = new ArrayList<>();
}
