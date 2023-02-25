package africa.semicolon.emailApp.services;

import africa.semicolon.emailApp.data.dtos.requests.UpdateUserRequest;
import africa.semicolon.emailApp.data.dtos.responses.FindUserResponse;
import africa.semicolon.emailApp.data.dtos.responses.UpdateUserResponse;
import africa.semicolon.emailApp.data.models.Email;
import africa.semicolon.emailApp.data.models.User;

import java.util.List;

public interface UserService {

    FindUserResponse getUserByEmail(String email);

    void deleteUserById(Long userId);

    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest);

    List<User> getAllUsers();

    List<Email> getInboxForUser(Long userId);

    List<Email> getSentEmailsForUser(Long userId);
}
