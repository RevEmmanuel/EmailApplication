package africa.semicolon.emailApp.controllers;

import africa.semicolon.emailApp.data.dtos.requests.CreateUserRequest;
import africa.semicolon.emailApp.data.dtos.responses.FindUserResponse;
import africa.semicolon.emailApp.exceptions.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getUserByEmail() {
        String email = "cynthiaosondo@myapp.com";
        FindUserResponse expectedUser = FindUserResponse.builder()
                .id(44L)
                .firstName("Cynthia")
                .lastName("Osondo")
                .email("cynthiaosondo@myapp.com")
                .emails(new ArrayList<>())
                .sentEmail(new ArrayList<>())
                .build();
        try {
            MvcResult result = mockMvc.perform(get("/api/users/emails/" + email))
                    .andExpect(status().is(HttpStatus.OK.value()))
                    .andReturn();

            String responseBody = result.getResponse().getContentAsString();
            FindUserResponse actualUser = objectMapper.readValue(responseBody, FindUserResponse.class);
            assertEquals(expectedUser, actualUser);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getUserById() {
        long userId = 44L;
        FindUserResponse expectedUser = FindUserResponse.builder()
                .id(44L)
                .firstName("Cynthia")
                .lastName("Osondo")
                .email("cynthiaosondo@myapp.com")
                .emails(new ArrayList<>())
                .sentEmail(new ArrayList<>())
                .build();
        try {
            MvcResult result = mockMvc.perform(get("/api/users/" + userId))
                    .andExpect(status().is(HttpStatus.OK.value()))
                    .andReturn();

            String responseBody = result.getResponse().getContentAsString();
            FindUserResponse actualUser = objectMapper.readValue(responseBody, FindUserResponse.class);
            assertEquals(expectedUser, actualUser);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createUser() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .firstName("User")
                .lastName("Example")
                .email("userexample@myapp.com")
                .password("password2")
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
    void deleteUser() throws Exception {
        long userId = 52L;
            mockMvc.perform(delete("/api/users/" + userId))
                    .andExpect(status().is(HttpStatus.OK.value()))
                    .andDo(print());
            try {
                mockMvc.perform(get("/api/users/" + userId))
                        .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
            }
            catch (Exception e) {
                System.out.println("Test passed.");
            }
    }

    @Test
    void updateUser() {

    }

}