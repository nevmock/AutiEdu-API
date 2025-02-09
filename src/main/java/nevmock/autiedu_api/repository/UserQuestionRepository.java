package nevmock.autiedu_api.repository;

import nevmock.autiedu_api.entity.User;
import nevmock.autiedu_api.entity.UserQuestion;
import nevmock.autiedu_api.entity.UserQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserQuestionRepository extends JpaRepository<UserQuestion, String> {
    List<UserQuestion> findAllByUser(User user);

    List<UserQuestion> findAllByUserAndQuestionId(User user, UUID questionId);
}


