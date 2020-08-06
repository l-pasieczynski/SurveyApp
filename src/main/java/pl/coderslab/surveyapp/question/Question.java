package pl.coderslab.surveyapp.question;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.surveyapp.answer.Answer;
import pl.coderslab.surveyapp.survey.Survey;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String question;
    private String additionalQuestion;
    @ManyToOne
    private Survey survey;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answer;

    public Question() {
    }
}
