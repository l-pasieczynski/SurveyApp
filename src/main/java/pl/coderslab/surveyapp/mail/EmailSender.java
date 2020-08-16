package pl.coderslab.surveyapp.mail;

import java.util.List;

public interface EmailSender {

    void sendEmail(List<String> to, String subject, String message);

    void sendContactForm(String name, String email, String message);

}
