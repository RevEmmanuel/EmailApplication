package africa.semicolon.emailApp.services;

import africa.semicolon.emailApp.data.dtos.responses.SendEmailResponse;
import africa.semicolon.emailApp.data.models.Email;
import jakarta.mail.MessagingException;

public interface MailSenderService {

    SendEmailResponse sendEmail(Email email) throws MessagingException;
}
