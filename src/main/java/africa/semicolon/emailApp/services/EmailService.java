package africa.semicolon.emailApp.services;

import africa.semicolon.emailApp.data.models.AppUser;
import africa.semicolon.emailApp.data.models.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmailService {

    List<Email> getInboxForUser(AppUser appUser);
}
