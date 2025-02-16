package nevmock.autiedu_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LearningModuleResponse {
    private UUID id;
    private String name;
    private String description;
    private String method;
    private List<TopicResponse> topics;
}
