package pl.coderslab.surveyapp.survey;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.surveyapp.question.Question;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class FreeSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    private String name;
    private int questionCount;
    private int numberOfParticipant;
    private boolean active;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate created;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<Question>();

    @PrePersist
    public void create() {
        created = LocalDate.now();
    }

    @Builder
    public FreeSurvey() {
    }

}
