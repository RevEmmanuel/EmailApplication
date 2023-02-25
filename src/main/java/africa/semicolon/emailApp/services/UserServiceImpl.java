package africa.semicolon.emailApp.services;

import africa.semicolon.emailApp.data.dtos.requests.UpdateUserRequest;
import africa.semicolon.emailApp.data.dtos.responses.FindUserResponse;
import africa.semicolon.emailApp.data.dtos.responses.UpdateUserResponse;
import africa.semicolon.emailApp.data.models.Email;
import africa.semicolon.emailApp.data.models.User;
import africa.semicolon.emailApp.data.repositories.UserRepository;
import africa.semicolon.emailApp.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public FindUserResponse getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
        return FindUserResponse.builder().id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .sentEmail(user.getSentEmails())
                .emails(user.getEmails())
                .build();
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Email> getInboxForUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        FindUserResponse foundUser = FindUserResponse.builder().id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .sentEmail(user.getSentEmails())
                .emails(user.getEmails())
                .build();
        return foundUser.getEmails();
    }

    @Override
    public List<Email> getSentEmailsForUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        FindUserResponse foundUser = FindUserResponse.builder().id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .sentEmail(user.getSentEmails())
                .emails(user.getEmails())
                .build();
        return foundUser.getSentEmail();
    }
}
