package africa.semicolon.emailApp.services;

import africa.semicolon.emailApp.data.dtos.requests.CreateUserRequest;
import africa.semicolon.emailApp.data.dtos.requests.UpdateUserRequest;
import africa.semicolon.emailApp.data.dtos.responses.CreateUserResponse;
import africa.semicolon.emailApp.data.dtos.responses.FindUserResponse;
import africa.semicolon.emailApp.data.dtos.responses.UpdateUserResponse;
import africa.semicolon.emailApp.data.models.Email;
import africa.semicolon.emailApp.data.models.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    FindUserResponse getUserById(Long id);

    CreateUserResponse createUser(CreateUserRequest request);

    FindUserResponse getUserByEmail(String email);

    void deleteUserById(Long userId);

    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest);

    List<AppUser> getAllUsers();

    List<Email> getInboxForUser(Long userId);
    Page<Email> getInboxForUser(Long userId, Pageable pageable);

    List<Email> getSentEmailsForUser(Long userId);

    void deleteAll();
}
