package nevmock.autiedu_api.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String email;
    private String role;
    private String name;
    private String className;
    private String phoneNumber;
    private boolean isEnabledMusic;
}
