package nevmock.autiedu_api.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nevmock.autiedu_api.entity.Topic;
import nevmock.autiedu_api.entity.User;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTopicResponse {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private User user;
    private Topic topic;
}
