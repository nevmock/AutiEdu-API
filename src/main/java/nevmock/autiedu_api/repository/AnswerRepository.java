package nevmock.autiedu_api.repository;

import nevmock.autiedu_api.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, String> {
}
