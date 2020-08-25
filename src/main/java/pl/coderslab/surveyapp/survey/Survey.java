package pl.coderslab.surveyapp.survey;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.surveyapp.question.Question;
import pl.coderslab.surveyapp.user.User;

import javax.persistence.*;
import javax.validation.constraints.Future;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter

public class Survey {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private int questionCount;
    private String owner;
    private int numberOfParticipant;
    private BigDecimal price;
    private boolean active;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate created;
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate expirationDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<Question>();
    @ManyToMany
    private List<User> user;

    @PrePersist
    public void create() {
        created = LocalDate.now();
    }

    @Builder
    public Survey() {
    }

    public Survey setQuestions(List<Question> questions) {
        this.questions = questions;
        return this;
    }
}
