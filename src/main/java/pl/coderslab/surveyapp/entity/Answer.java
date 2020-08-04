package pl.coderslab.surveyapp.entity;

import lombok.Getter;
import lombok.Setter;

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
    private Participant participant;

    public Answer() {
    }

}
