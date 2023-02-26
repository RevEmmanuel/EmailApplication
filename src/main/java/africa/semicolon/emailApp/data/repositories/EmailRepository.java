package africa.semicolon.emailApp.data.repositories;

import africa.semicolon.emailApp.data.models.AppUser;
import africa.semicolon.emailApp.data.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {

    List<Email> findEmailsByRecipientsContaining(AppUser user);
}
