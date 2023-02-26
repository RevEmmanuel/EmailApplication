package africa.semicolon.emailApp.services;

import africa.semicolon.emailApp.data.dtos.requests.CreateUserRequest;
import africa.semicolon.emailApp.data.dtos.requests.UpdateUserRequest;
import africa.semicolon.emailApp.data.dtos.responses.CreateUserResponse;
import africa.semicolon.emailApp.data.dtos.responses.FindUserResponse;
import africa.semicolon.emailApp.data.dtos.responses.UpdateUserResponse;
import africa.semicolon.emailApp.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    private CreateUserResponse response;

    @BeforeEach
    void setUp() {
        userService.deleteAll();
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .firstName("Cynthia")
                .lastName("Osondo")
                .email("cynthiaosondo@myapp.com")
                .password("iloveadeola")
                .build();
        response = userService.createUser(createUserRequest);
    }

    @Test
    void getUserByEmail() {
        FindUserResponse foundUser = userService.getUserByEmail("cynthiaosondo@myapp.com");

        assertEquals(foundUser.getId(), response.getId());
        assertEquals(foundUser.getFirstName(), response.getFirstName());
        assertEquals(foundUser.getLastName(), response.getLastName());
        assertEquals(foundUser.getEmail(), response.getEmail());
    }

    @Test
    void getUserById() {
        FindUserResponse foundUser = userService.getUserById(response.getId());

        assertEquals(foundUser.getId(), response.getId());
        assertEquals(foundUser.getFirstName(), response.getFirstName());
        assertEquals(foundUser.getLastName(), response.getLastName());
        assertEquals(foundUser.getEmail(), response.getEmail());
    }

    @Test
    void deleteUserById() {
        assertNotNull(userService.getUserById(response.getId()));
        userService.deleteUserById(response.getId());
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(response.getId()));
        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail(response.getEmail()));
    }

    @Test
    void updateUser() {
        UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
                .id(response.getId())
                .firstName("Cynthia")
                .lastName("Osondo")
                .password("iloveme")
                .email("osondocynthia@myapp.com")
                .build();
        UpdateUserResponse updateUserResponse = userService.updateUser(updateUserRequest);

        FindUserResponse foundUser = userService.getUserById(response.getId());

        assertEquals(foundUser.getFirstName(), updateUserRequest.getFirstName());
        assertEquals(foundUser.getLastName(), updateUserRequest.getLastName());
        assertEquals(foundUser.getEmail(), updateUserRequest.getEmail());
    }

    @Test
    void createUser() {
        CreateUserRequest createNewUserRequest = CreateUserRequest.builder()
                .firstName("Adeola")
                .lastName("Adekunle")
                .email("adeolaadekunle@myapp.com")
                .password("iloveyou")
                .build();

        CreateUserResponse createNewUserResponse = userService.createUser(createNewUserRequest);

        assertNotNull(createNewUserResponse.getId());
        assertEquals(createNewUserResponse.getFirstName(), createNewUserRequest.getFirstName());
        assertEquals(createNewUserResponse.getLastName(), createNewUserRequest.getLastName());
        assertEquals(createNewUserResponse.getEmail(), createNewUserRequest.getEmail());
    }
}
