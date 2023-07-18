package testsystem.data.jpa.questions;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface QuestionsRepository extends CrudRepository<Questions, Long> {

	@Query("select question_id from Questions")
    List<Long> getQuestions_id();
}