package africa.semicolon.emailApp.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class EmailApplicationExceptionHandler {

    @ExceptionHandler(EmailApplicationException.class)
    public ResponseEntity<EmailApplicationExceptionResponse> handleEmailApplicationException(EmailApplicationException e){
        var res = EmailApplicationExceptionResponse
                .builder()
                .message(e.getMessage())
                .status(e.getStatus())
                .build();

        return new ResponseEntity<>(res, e.getStatus());
    }
}
