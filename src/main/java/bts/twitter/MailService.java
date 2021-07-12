package bts.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendConfirmCode(String to, String code) {
        String message = "Hello, to confirm your email go on the link\n" +
                "localhost:8080/registration/confirm/" + code;
        String subject = "Confirm your email on Twitter app";

        sendMail(to, subject, message);
    }

    public void sendMail(
            String to,
            String subject,
            String text
    ) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setFrom("noreply@gmail.com");
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }
}
