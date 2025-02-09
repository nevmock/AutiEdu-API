package nevmock.autiedu_api.repository;

import nevmock.autiedu_api.entity.Option;
import nevmock.autiedu_api.entity.Topic;
import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.entity.UserTopic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserTopicRepository extends JpaRepository<UserTopic, String> {
    List<UserTopic> findAllByUser(User user);

    Optional<UserTopic> findByUserIdAndTopicId(UUID userId, UUID topicId);

    boolean existsByUserIdAndTopicId(UUID userId, UUID topicId);

    List<UserTopic> findAllByUserAndTopicId(User user, UUID topicId);

    List<UserTopic> findAllByUserAndTopicWithinAndTopic_LearningModule_Id(User user, Topic topic, UUID topicLearningModuleId, Pageable pageable);

    List<UserTopic> findAllByUserAndTopic_LearningModule_Id(User user, UUID learningModuleId);
}


