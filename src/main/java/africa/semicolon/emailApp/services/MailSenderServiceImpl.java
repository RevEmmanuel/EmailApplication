package africa.semicolon.emailApp.services;

import africa.semicolon.emailApp.data.dtos.responses.SendEmailResponse;
import africa.semicolon.emailApp.data.models.AppUser;
import africa.semicolon.emailApp.data.models.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender mailSender;

    @Autowired
    private MailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public SendEmailResponse sendEmail(Email email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(email.getSender().getEmail());
        helper.setSubject(email.getSubject());
        helper.setText(email.getBody(),true);
        List<AppUser> recipients = email.getRecipients();
        List<String> recipientEmailAddress = new ArrayList<>();

        // get the recipients email addresses
        for (AppUser user : recipients) {
            recipientEmailAddress.add(user.getEmail());
        }

        // set the recipients
        for (String recipient : recipientEmailAddress) {
            helper.setTo(recipient);
            mailSender.send(message);
        }

        return SendEmailResponse.builder()
                .id(email.getId())
                .sender(email.getSender())
                .sentAt(LocalDateTime.now())
                .recipients(email.getRecipients())
                .subject(email.getSubject())
                .body(email.getBody())
                .build();
    }

}

