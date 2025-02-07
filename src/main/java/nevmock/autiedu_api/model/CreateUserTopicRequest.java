package nevmock.autiedu_api.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nevmock.autiedu_api.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserTopicRequest {
    @NotNull
    private CreateTopicRequest topic;

    @NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

}
