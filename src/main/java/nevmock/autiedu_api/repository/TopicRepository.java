package nevmock.autiedu_api.repository;

import jakarta.validation.constraints.NotNull;
import nevmock.autiedu_api.entity.Topic;
import org.springframework.aot.hint.annotation.RegisterReflection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {
    void existsById(UUID id);

    Optional<Topic> findById(@NotNull UUID topicId);
}
