package nevmock.autiedu_api.repository;

import nevmock.autiedu_api.entity.LearningModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LearningModuleRepository extends JpaRepository<LearningModule, String> {
    void existsById(UUID id);
}
