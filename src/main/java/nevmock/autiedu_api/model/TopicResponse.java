package nevmock.autiedu_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicResponse {
    private UUID id;
    private String name;
    private String description;
    private String method;
    private Integer level;
    private UUID learningModuleId;

}
