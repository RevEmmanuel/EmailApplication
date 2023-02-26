package africa.semicolon.emailApp.services;

import africa.semicolon.emailApp.data.dtos.requests.CreateUserRequest;
import africa.semicolon.emailApp.data.dtos.requests.UpdateUserRequest;
import africa.semicolon.emailApp.data.dtos.responses.CreateUserResponse;
import africa.semicolon.emailApp.data.dtos.responses.FindUserResponse;
import africa.semicolon.emailApp.data.dtos.responses.UpdateUserResponse;
import africa.semicolon.emailApp.data.models.Email;
import africa.semicolon.emailApp.data.models.AppUser;
import africa.semicolon.emailApp.data.repositories.UserRepository;
import africa.semicolon.emailApp.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.Page;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;


    @Override
    public FindUserResponse getUserById(Long id) {
        AppUser appUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        FindUserResponse userResponse = new FindUserResponse();
        setUserDetailsForResponse(appUser, userResponse);
        return userResponse;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        AppUser savedUser = userRepository.save(user);

        return CreateUserResponse.builder()
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .id(savedUser.getId())
                .build();
    }

    @Override
    public FindUserResponse getUserByEmail(String email) {
        AppUser appUser = userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
        FindUserResponse userResponse = new FindUserResponse();
        setUserDetailsForResponse(appUser, userResponse);
        return userResponse;
    }

    private void setUserDetailsForResponse(AppUser appUser, FindUserResponse userResponse) {
        userResponse.setFirstName(appUser.getFirstName());
        userResponse.setLastName(appUser.getLastName());
        userResponse.setId(appUser.getId());
        userResponse.setEmail(appUser.getEmail());
        userResponse.setEmails(appUser.getEmails());
        userResponse.setSentEmail(appUser.getSentEmails());
    }


    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        AppUser foundUser = userRepository.findById(updateUserRequest.getId()).orElseThrow(UserNotFoundException::new);
        setAppUserDetails(foundUser, updateUserRequest);
        userRepository.save(foundUser);

        foundUser = userRepository.findById(updateUserRequest.getId()).orElseThrow(UserNotFoundException::new);
        return UpdateUserResponse.builder()
                .email(foundUser.getEmail())
                .firstName(foundUser.getFirstName())
                .lastName(foundUser.getLastName())
                .build();
    }

    private void setAppUserDetails(AppUser foundUser, UpdateUserRequest updateUserRequest) {
        foundUser.setFirstName(updateUserRequest.getFirstName());
        foundUser.setLastName(updateUserRequest.getLastName());
        foundUser.setEmail(updateUserRequest.getEmail());
        foundUser.setPassword(updateUserRequest.getPassword());
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Email> getInboxForUser(Long userId) {
        AppUser appUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        FindUserResponse foundUser = FindUserResponse.builder().id(appUser.getId())
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .sentEmail(appUser.getSentEmails())
                .emails(appUser.getEmails())
                .build();
        return foundUser.getEmails();
    }

    @Override
    public Page<Email> getInboxForUser(Long userId, Pageable pageable) {
        AppUser appUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Email> foundEmails = emailService.getInboxForUser(appUser);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Email> pageEmails;

        if (foundEmails.size() < startItem) {
            pageEmails = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, foundEmails.size());
            pageEmails = foundEmails.subList(startItem, toIndex);
        }

        return new PageImpl<>(pageEmails, pageable, foundEmails.size());
    }

    @Override
    public List<Email> getSentEmailsForUser(Long userId) {
        AppUser appUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        FindUserResponse foundUser = FindUserResponse.builder().id(appUser.getId())
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .sentEmail(appUser.getSentEmails())
                .emails(appUser.getEmails())
                .build();
        return foundUser.getSentEmail();
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
