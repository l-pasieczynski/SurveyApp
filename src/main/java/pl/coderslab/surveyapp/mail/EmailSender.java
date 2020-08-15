package pl.coderslab.surveyapp.mail;

public interface EmailSender {

    void sendEmail(String[] to, String subject, String message);

    void sendContactForm(String name, String email, String message);

}
