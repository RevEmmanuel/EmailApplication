package africa.semicolon.emailApp.services;

import africa.semicolon.emailApp.data.dtos.responses.FindUserResponse;
import africa.semicolon.emailApp.data.models.AppUser;
import africa.semicolon.emailApp.data.models.Email;
import africa.semicolon.emailApp.data.repositories.EmailRepository;
import africa.semicolon.emailApp.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final UserService userService;
    private final EmailRepository emailRepository;

    @Override
    public List<Email> getInboxForUser(AppUser appUser) {
        return emailRepository.findEmailsByRecipientsContaining(appUser);
    }
}
