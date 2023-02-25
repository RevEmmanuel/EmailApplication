package africa.semicolon.emailApp.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class EmailApplicationException extends RuntimeException {

    @Getter
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public EmailApplicationException() {
        this("An error occurred");
    }

    public EmailApplicationException(String message) {
        super(message);
    }

    public EmailApplicationException(String message, HttpStatus status){
        this(message);
        this.status = status;
    }
}
