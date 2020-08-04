package pl.coderslab.surveyapp.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<Question>();
    @ManyToMany
    private List<Participant> participant;

    @PrePersist
    public void create() {
        created = LocalDate.now();
    }

    @Builder
    public Survey() {
    }

}
