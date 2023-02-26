package africa.semicolon.emailApp.data.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateUserResponse {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
}
