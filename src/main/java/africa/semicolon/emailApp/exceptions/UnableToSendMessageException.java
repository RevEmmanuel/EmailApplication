package africa.semicolon.emailApp.exceptions;

import org.springframework.http.HttpStatus;

public class UnableToSendMessageException extends EmailApplicationException {

    public UnableToSendMessageException() {
        this("An error occurred while sending the message.");
    }

    public UnableToSendMessageException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
