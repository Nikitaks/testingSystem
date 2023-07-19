package testsystem.data.jpa.questions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionsService {
    @Autowired QuestionsRepository repo;

    public void save(Questions customer) {
        repo.save(customer);
    }

    public List<Questions> listAll() {
        return (List<Questions>) repo.findAll();
    }

    public Questions get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Long> getQuestions_id() {
    	return repo.getQuestions_id();
    }
    public Iterable<Questions> findAllById(Iterable<Long> ids) {
    	Iterable<Questions> questions = repo.findAllById(ids);
    	for (Questions question : questions) {
    		question.getAnswers().add(question.getWordings().getAnswer0());
    		question.getAnswers().add(question.getWordings().getAnswer1());
    		question.getAnswers().add(question.getWordings().getAnswer2());
    		question.getAnswers().add(question.getWordings().getAnswer3());
    		question.getAnswers().add(question.getWordings().getAnswer4());
    		question.getAnswers().add(question.getWordings().getAnswer5());
    	}
    	return questions;
    }
}