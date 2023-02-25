package africa.semicolon.emailApp.controllers;

import africa.semicolon.emailApp.data.dtos.responses.FindUserResponse;
import africa.semicolon.emailApp.data.models.User;
import africa.semicolon.emailApp.services.UserService;
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

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        FindUserResponse foundUser = userService.getUserByEmail(email);
        return ResponseEntity.ok(foundUser);
    }

}
