package nevmock.autiedu_api.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserTopicRequest {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID topicId;

    @NotNull
    private boolean isUnlocked;
}
