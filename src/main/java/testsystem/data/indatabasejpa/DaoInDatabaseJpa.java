package testsystem.data.indatabasejpa;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import testsystem.TestTask;
import testsystem.data.Dao;
import testsystem.data.jpa.questions.Questions;
import testsystem.data.jpa.questions.QuestionsService;
import testsystem.exceptions.NotEnoughQuestionsException;


public class DaoInDatabaseJpa implements Dao {

	public static final int MAX_ANSWERS = 6;

	@Autowired
    private QuestionsService questionsService;

	@Override
	public TestTask[] getTasks(int questionsNumber) throws NotEnoughQuestionsException {
		TestTask[] taskList = new TestTask[questionsNumber];

		List<Long> list = questionsService.getQuestions_id();
		Collections.shuffle(list);
		List<Long> shortList = list.stream()
			.limit(questionsNumber)
			.collect(Collectors.toList());
		Iterable<Questions> questions = questionsService.findAllById(shortList);
		int i = 0;
		for (Questions question : questions) {
			String questionText = question.getWordings().getQuestion_text();
			String[] answers = question.getAnswers()
				.stream()
				.limit(question.getAnswers_number())
				.toArray(String[]::new);
			int correctAnswer = question.getCorrect_answer();
			taskList[i++] = new TestTask(questionText, answers, correctAnswer);
		}
		return taskList;
	}

	public DaoInDatabaseJpa() {
	}

	public static void main(String[] args) throws NotEnoughQuestionsException {
	}
}
