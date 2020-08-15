package pl.coderslab.surveyapp.mail;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.springframework.test.util.AssertionErrors.assertEquals;

class EmailSenderImplTest {


    private EmailSenderImpl emailSender;

    public EmailSenderImplTest(JavaMailSender mailSender, EmailSenderImpl emailSender) {
        this.emailSender = emailSender;
    }

    @Test
    void shouldSendContactMessageToREST() throws MessagingException {




        JavaMailSender mailSender = Mockito.mock(JavaMailSender.class);
//        Mockito.when(mailSender.createMimeMessage()).thenReturn(new MimeMessage());
        String from = "from@email.com";
        String to = "to@first-email.com";
        String subject = "subject";
        String msg = "<html>msg</html>";
        //        String[] to = {"to@first-email.com", "to@second-email.com"};


        MimeMessage mail = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mail);
        helper.setTo(to);
        helper.setText(msg);
        helper.setSubject(subject);

        mailSender.send(mail);

        emailSender.sendContactForm(from, subject, msg);


        try {
            assertEquals(from, mail.getFrom()[0].toString(), "from@email.com");
            assertEquals(subject, mail.getSubject(), subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}

//    MockJavaMailSender sender = new MockJavaMailSender();
//        sender.setHost("host");
//                sender.setPort(30);
//                sender.setUsername("username");
//                sender.setPassword("password");