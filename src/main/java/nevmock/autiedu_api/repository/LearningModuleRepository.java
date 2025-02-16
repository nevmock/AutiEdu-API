package nevmock.autiedu_api.repository;

import nevmock.autiedu_api.entity.LearningModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LearningModuleRepository extends JpaRepository<LearningModule, String> {
    void existsById(UUID id);

    Optional<LearningModule> findByName(String learningModuleName);

    List<LearningModule> findByNameIn(List<String> defaultLearningModules);
}
