package nevmock.autiedu_api.repository;

import jakarta.validation.constraints.NotNull;
import nevmock.autiedu_api.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {
    void existsById(UUID id);

    Optional<Question> findById(@NotNull UUID id);
}
