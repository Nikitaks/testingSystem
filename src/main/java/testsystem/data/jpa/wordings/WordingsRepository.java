package testsystem.data.jpa.wordings;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface WordingsRepository extends CrudRepository<Wordings, Long> {
	Page<Wordings> findAll(Pageable pageable);
}