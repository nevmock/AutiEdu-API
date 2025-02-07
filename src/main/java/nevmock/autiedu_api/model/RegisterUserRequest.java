package nevmock.autiedu_api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserRequest {
    @NotBlank
    @Size( max = 100)
    @Email
    private String email;

    @NotBlank
    @Size( max = 100)
    private String password;

    private String role = "user";

    private String name;

    private String className;

    private String phoneNumber;

    private boolean isEnabledMusic = true;
}
