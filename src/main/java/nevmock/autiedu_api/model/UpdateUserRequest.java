package nevmock.autiedu_api.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {
    @Size(max = 100)
    private String email;

    @Size(max = 100)
    private String password;

    @Size(max = 100)
    private String name;

    @Size(max = 100)
    private String className;

    private String role = "user";

    @Size(max = 100)
    private String phoneNumber;

    @NumberFormat
    private Integer age;

    private boolean isEnabledMusic;
}
