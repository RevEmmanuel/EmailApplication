package africa.semicolon.emailApp.controllers;

import africa.semicolon.emailApp.data.dtos.requests.CreateUserRequest;
import africa.semicolon.emailApp.data.dtos.responses.CreateUserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

    }

    @Test
    void getUserByEmail() {

    }

    @Test
    void getUserById() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .firstName("Adeola")
                .lastName("Babanla")
                .email("adebaba@myapp.com")
                .password("adebaba")
                .build();
        try {
            mockMvc.perform(post("/api/users/emails/{}")
                            .content(objectMapper.writeValueAsString(createUserRequest))
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is(HttpStatus.CREATED.value()))
                    .andDo(print());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createUser() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .firstName("Cynthia")
                .lastName("Osondo")
                .email("cynthiaosondo@myapp.com")
                .password("iloveadeola")
                .build();
        try {
            mockMvc.perform(post("/api/users/create")
                            .content(objectMapper.writeValueAsString(createUserRequest))
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is(HttpStatus.CREATED.value()))
                    .andDo(print());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}