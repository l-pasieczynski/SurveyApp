package pl.coderslab.surveyapp.mail;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class ContactMessage {

    private String name;
    private String email;
    private String message;

}
