package nevmock.autiedu_api.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserQuestionRequest {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID questionId;

    @NotNull
    private boolean isUnlocked;
}
