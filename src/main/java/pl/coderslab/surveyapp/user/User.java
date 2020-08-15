package pl.coderslab.surveyapp.user;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.surveyapp.role.Role;
import pl.coderslab.surveyapp.survey.Survey;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Column(nullable = false)
//    @Size(min = 6)
    private String password;
//    @NotNull
    private String gender;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;
    private BigDecimal accountBalance;
    private boolean active;
    private String education;
    private String placeOfLiving;
    @OneToMany
    private List<Survey> survey;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", accountBalance=" + accountBalance +
                ", active=" + active +
                ", education='" + education + '\'' +
                ", placeOfLiving='" + placeOfLiving + '\'' +
                ", survey=" + survey +
                ", roles=" + roles +
                '}';
    }
}
