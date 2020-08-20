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

@Setter
@Getter
@RequiredArgsConstructor
public class UserSearch {


    private String username;
    private String email;
    private String gender;
    private Integer age;
    private boolean active;
    private String education;
    private String placeOfLiving;
    private Survey survey;

}


