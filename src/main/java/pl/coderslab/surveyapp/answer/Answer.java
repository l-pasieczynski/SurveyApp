package pl.coderslab.surveyapp.answer;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.surveyapp.question.Question;
import pl.coderslab.surveyapp.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank
    private String answer;
    @ManyToOne
    private Question question;
    @ManyToOne
    private User user;

    public Answer() {
    }

    public Answer setAnswer(String answer) {
        this.answer = answer;
        return this;
    }
}
