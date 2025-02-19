package nevmock.autiedu_api.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateQuestionRequest {
    @GeneratedValue(strategy = GenerationType.UUID)
    @NotNull
    private UUID id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID topicId;

    private Integer level;

    private String mediaType;

    private String src;

    private boolean isMultipleOption;

    private String text;
}
