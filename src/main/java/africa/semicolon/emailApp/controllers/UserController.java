package africa.semicolon.emailApp.controllers;

import africa.semicolon.emailApp.data.dtos.responses.FindUserResponse;
import africa.semicolon.emailApp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apr/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get A Particular User", description = "Returns a Response entity containing the requested user")
    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(
            @PathVariable
            @Parameter(name = "email", description = "The email address of the required user") String email) {
        FindUserResponse foundUser = userService.getUserByEmail(email);
        return ResponseEntity.ok(foundUser);
    }

}
