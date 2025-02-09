package nevmock.autiedu_api.repository;

import nevmock.autiedu_api.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, String> {
}
