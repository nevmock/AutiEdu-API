package nevmock.autiedu_api.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID topicId;

    private Integer level;
    private String mediaType;
    private String src;
    @Nullable
    private Boolean isMultipleOption;
    private String text;
}
