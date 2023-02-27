package africa.semicolon.emailApp.exceptions;

import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends EmailApplicationException {

    public EmailNotFoundException() {
        this("Email not found!");
    }

    public EmailNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
