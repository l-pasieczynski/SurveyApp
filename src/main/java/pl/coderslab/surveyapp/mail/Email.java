package pl.coderslab.surveyapp.mail;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class Email {

    private String header;
    private String title;
    private String description;
    private List<String> usersEmailAddress;
    private String subject;

}
