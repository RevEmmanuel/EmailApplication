package africa.semicolon.emailApp.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class EmailApplicationExceptionResponse {
    private String message;
    private HttpStatus status;
}

