package africa.semicolon.emailApp.services;

import africa.semicolon.emailApp.data.dtos.requests.SendEmailRequest;
import africa.semicolon.emailApp.data.dtos.responses.ForwardEmailResponse;
import africa.semicolon.emailApp.data.dtos.responses.SendEmailResponse;
import africa.semicolon.emailApp.data.models.AppUser;
import africa.semicolon.emailApp.data.models.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmailService {

    List<Email> getInboxForUser(AppUser appUser);

    SendEmailResponse sendEmail(AppUser appUser, SendEmailRequest emailRequest);

    void deleteEmail(Long emailId);

}
