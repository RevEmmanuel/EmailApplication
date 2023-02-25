package africa.semicolon.emailApp.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends EmailApplicationException {

    public UserNotFoundException() {
        this("User not found!");
    }

    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
