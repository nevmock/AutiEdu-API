package nevmock.autiedu_api.repository;

import nevmock.autiedu_api.entity.UserTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTopicRepository extends JpaRepository<UserTopic, String> {
}


