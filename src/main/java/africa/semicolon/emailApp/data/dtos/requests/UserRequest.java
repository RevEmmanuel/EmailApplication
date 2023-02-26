package africa.semicolon.emailApp.data.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
