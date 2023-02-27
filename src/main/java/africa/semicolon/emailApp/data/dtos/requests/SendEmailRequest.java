package africa.semicolon.emailApp.data.dtos.requests;

import africa.semicolon.emailApp.data.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailRequest {

    private List<AppUser> recipients;
    private String subject;
    private String body;

}
