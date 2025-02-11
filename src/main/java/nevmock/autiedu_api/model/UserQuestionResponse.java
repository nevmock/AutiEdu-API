package nevmock.autiedu_api.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import nevmock.autiedu_api.entity.Answer;
import nevmock.autiedu_api.entity.Option;
import nevmock.autiedu_api.entity.Topic;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQuestionResponse {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String mediaType;
    private String src;
    private Boolean isMultipleOption;
    private String text;
    private Integer level;
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID topicId;
    private List<OptionResponse> options;
    private List<AnswerResponse> answers;
    private boolean isUnlocked;
}
