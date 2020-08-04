package pl.coderslab.surveyapp.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.math.BigDecimal;
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
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<Question>();
    @ManyToMany
    private List<Participant> participant;

    @Builder
    public Survey() {
    }

}
