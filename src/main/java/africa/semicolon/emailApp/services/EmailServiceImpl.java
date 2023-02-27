package africa.semicolon.emailApp.services;

import africa.semicolon.emailApp.data.dtos.requests.SendEmailRequest;
import africa.semicolon.emailApp.data.dtos.responses.SendEmailResponse;
import africa.semicolon.emailApp.data.models.AppUser;
import africa.semicolon.emailApp.data.models.Email;
import africa.semicolon.emailApp.data.repositories.EmailRepository;
import africa.semicolon.emailApp.exceptions.EmailNotFoundException;
import africa.semicolon.emailApp.exceptions.UnableToSendMessageException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final MailSenderService mailSenderService;

    @Override
    public List<Email> getInboxForUser(AppUser appUser) {
        return emailRepository.findEmailsByRecipientsContaining(appUser);
    }

    @Override
    public SendEmailResponse sendEmail(AppUser appUser, SendEmailRequest emailRequest) {
        Email email = Email.builder()
                .sender(appUser)
                .createdAt(LocalDateTime.now())
                .subject(emailRequest.getSubject())
                .body(emailRequest.getBody())
                .recipients(emailRequest.getRecipients())
                .build();

        Email savedEmail = emailRepository.save(email);
        SendEmailResponse emailResponse;
        try {
            emailResponse = mailSenderService.sendEmail(savedEmail);
        } catch (MessagingException e) {
            throw new UnableToSendMessageException();
        }
        emailResponse.setRequestCreatedAt(email.getCreatedAt());
        email = emailRepository.findById(savedEmail.getId()).orElseThrow(
                () -> new EmailNotFoundException("Email with id " + savedEmail.getId() + " not found."));
        email.setSentAt(emailResponse.getSentAt());
        emailRepository.save(email);
        return emailResponse;
    }

    @Override
    public void deleteEmail(Long emailId) {
        emailRepository.deleteById(emailId);
    }
}
