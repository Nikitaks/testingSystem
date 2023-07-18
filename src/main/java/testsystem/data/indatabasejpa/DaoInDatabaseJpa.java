package testsystem.data.indatabasejpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.servlet.ModelAndView;

import testsystem.TestTask;
import testsystem.data.Dao;
import testsystem.data.jpa.questions.Questions;
import testsystem.data.jpa.questions.QuestionsService;
import testsystem.data.jpa.wordings.Wordings;
import testsystem.data.jpa.wordings.WordingsService;
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
			String[] allAnswers = new String[MAX_ANSWERS];
			allAnswers[0] = question.getWordings().getAnswer0();
			allAnswers[1] = question.getWordings().getAnswer1();
			allAnswers[2] = question.getWordings().getAnswer2();
			allAnswers[3] = question.getWordings().getAnswer3();
			allAnswers[4] = question.getWordings().getAnswer4();
			allAnswers[5] = question.getWordings().getAnswer5();
			String[] answers = new String[question.getAnswers_number()];
			for (int j = 0; j < answers.length; j++) {
				answers[j] = allAnswers[j];
			}
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
