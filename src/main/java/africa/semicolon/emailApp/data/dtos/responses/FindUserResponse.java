package africa.semicolon.emailApp.data.dtos.responses;

import africa.semicolon.emailApp.data.models.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FindUserResponse {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<Email> emails;
    private List<Email> sentEmail;

}
