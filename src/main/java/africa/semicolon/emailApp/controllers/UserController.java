package africa.semicolon.emailApp.controllers;

import africa.semicolon.emailApp.data.dtos.requests.CreateUserRequest;
import africa.semicolon.emailApp.data.dtos.responses.CreateUserResponse;
import africa.semicolon.emailApp.data.dtos.responses.FindUserResponse;
import africa.semicolon.emailApp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
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
//        return ResponseEntity.ok(foundUser);
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
//        return ResponseEntity.ok(foundUser);
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
//        return ResponseEntity.ok(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(
            @PathVariable
            @Parameter(name = "userId", required = true, example = "1",
            description = "The Id of the user to delete")
            Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }

}
