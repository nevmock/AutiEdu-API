package nevmock.autiedu_api.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import nevmock.autiedu_api.entity.Answer;
import nevmock.autiedu_api.entity.Option;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQuestionResponse {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private List<Option> options;
    private List<Answer> answers;
    private boolean isUnlocked;
}
