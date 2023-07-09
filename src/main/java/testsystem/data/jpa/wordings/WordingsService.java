package testsystem.data.jpa.wordings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WordingsService {
    @Autowired WordingsRepository repo;

    public void save(Wordings wording) {
        repo.save(wording);
    }

    public List<Wordings> listAll() {
        return (List<Wordings>) repo.findAll();
    }

    public Wordings get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    public long count() {
    	return repo.count();
    }
    public Page<Wordings> findAll(Pageable pageable) {
    	return repo.findAll(pageable);
    }
}