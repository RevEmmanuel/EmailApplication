package africa.semicolon.emailApp.data.dtos.responses;

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
public class SendEmailResponse {

    private Long id;
    private AppUser sender;
    private List<AppUser> recipients;
    private LocalDateTime requestCreatedAt;
    private LocalDateTime sentAt;
    private String subject;
    private String body;

}
