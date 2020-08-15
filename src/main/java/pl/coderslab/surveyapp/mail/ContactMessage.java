package pl.coderslab.surveyapp.mail;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@RequiredArgsConstructor
public class ContactMessage {

    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String message;

}
