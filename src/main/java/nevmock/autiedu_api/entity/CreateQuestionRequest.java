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
public class CreateQuestionRequest {
    @NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID topicId;

    @NotNull
    private Integer level;

    @NotBlank
    @Size(max = 100)
    private String mediaType;

    @NotBlank
    @Size(max = 100)
    private String src;

    @NotNull
    private boolean isMultipleOption;

    @NotBlank
    @Size(max = 255)
    private String text;
}
