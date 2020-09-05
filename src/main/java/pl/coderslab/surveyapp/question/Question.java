package pl.coderslab.surveyapp.question;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.coderslab.surveyapp.answer.Answer;
import pl.coderslab.surveyapp.survey.Survey;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String question;
    private String query;
    private String imageQuestion;
    @ManyToOne
    @JsonBackReference
    private Survey survey;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    @JsonManagedReference
    private List<Answer> answer;
    private String questionType;

//    public Question() {
//    }
//
//    public Question setAnswer(List<Answer> answer) {
//        this.answer = answer;
//        return this;
//    }
}
