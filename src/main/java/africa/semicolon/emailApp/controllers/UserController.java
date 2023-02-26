package africa.semicolon.emailApp.controllers;

import africa.semicolon.emailApp.data.dtos.requests.CreateUserRequest;
import africa.semicolon.emailApp.data.dtos.requests.UpdateUserRequest;
import africa.semicolon.emailApp.data.dtos.responses.CreateUserResponse;
import africa.semicolon.emailApp.data.dtos.responses.FindUserResponse;
import africa.semicolon.emailApp.data.dtos.responses.UpdateUserResponse;
import africa.semicolon.emailApp.data.models.Email;
import africa.semicolon.emailApp.services.EmailService;
import africa.semicolon.emailApp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    private UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @Operation(summary = "Get A Particular User by the user's email address",
            description = "Returns a Response entity containing the requested user and HTTP status code")
    @GetMapping("/emails/{email}")
    public ResponseEntity<?> getUserByEmail(
            @PathVariable
            @Parameter(name = "email", description = "The email address of the required user",
                    required = true, example = "user@example.com")
            String email) {
        FindUserResponse foundUser = userService.getUserByEmail(email);
//        return ResponseEntity.status(HttpStatus.OK).body(foundUser);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @Operation(summary = "Get A Particular User by the user's Id",
            description = "Returns a Response entity containing the requested user and HTTP status code")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(
            @PathVariable
            @Parameter(name = "id", description = "The id of the required user",
                    required = true, example = "1")
            Long userId) {
        FindUserResponse foundUser = userService.getUserById(userId);
//        return ResponseEntity.status(HttpStatus.OK).body(foundUser);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @Operation(summary = "Create a new user",
            description = "Returns a Response entity containing the new user's details and HTTP status code")
    @PostMapping("/create")
    public ResponseEntity<?> createUser(
            @RequestBody
            @Parameter(name = "CreateUserRequest", required = true,
                    description = "Contains the details required to create a new user which are e-mail, password, first name and lastname.")
            CreateUserRequest userRequest) {
        CreateUserResponse response = userService.createUser(userRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an existing user",
            description = "Returns a Response entity HTTP status code")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(
            @PathVariable
            @Parameter(name = "userId", required = true, example = "1",
            description = "The Id of the user to delete")
            Long userId) {
        userService.deleteUserById(userId);
//        return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update the details of an existing user",
            description = "Returns a Response entity containing the updated user's details and HTTP status code")
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(
            @RequestBody
            @Parameter(name = "updateUserRequest", required = true,
            description = "Contains the details of the user that requires the update")
            UpdateUserRequest updateUserRequest) {
        UpdateUserResponse updateUserResponse = userService.updateUser(updateUserRequest);
//        return ResponseEntity.status(HttpStatus.OK).body(updateUserResponse);
        return new ResponseEntity<>(updateUserResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get the inbox(emails) of an existing user",
            description = "Returns a Response entity containing the user's emails and HTTP status code")
    @GetMapping("/users/{userId}/inbox")
    public ResponseEntity<?> getInboxForUser(
            @PathVariable
            @Parameter(name = "userId", example = "1", required = true,
                    description = "The ID of the user whose inbox is being retrieved.")
            Long userId,
            @RequestParam(defaultValue = "0")
            @Parameter(name = "page", description = "The page number of the inbox to retrieve. Default value is 0.")
            int page,
            @RequestParam(defaultValue = "10")
            @Parameter(name = "pageSize", description = "The size of the page of inbox to retrieve. Default value is 10.")
            int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Email> emails = userService.getInboxForUser(userId, pageable);

//        return ResponseEntity.status(HttpStatus.OK).body(emails);
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }

}
