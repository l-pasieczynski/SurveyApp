package pl.coderslab.surveyapp.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public
class EmailSenderImpl implements EmailSender {

    private final JavaMailSender javaMailSender;

    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(List<String> to, String subject, String message) {

        MimeMessage mail = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            for (int i =0; i <= to.size(); i++){
                helper.setTo(to.get(i));
                helper.setReplyTo("noreply@surveyapp.com");
                helper.setSubject(subject);
                helper.setText(message, true);
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mail);

    }

    @Override
    public void sendContactForm(String name, String email, String message) {
        MimeMessage mail = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail);
            //TODO zmienic adres email
//            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo("nasz@gmail.com");
            helper.setSubject(name + " Contact Form Question");
            helper.setFrom(email);
            helper.setText(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
    }

}
