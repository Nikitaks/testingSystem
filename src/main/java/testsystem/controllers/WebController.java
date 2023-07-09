package testsystem.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import testsystem.data.Dao;
import testsystem.exceptions.NotEnoughQuestionsException;
import testsystem.ModelTest;


@Controller
public class WebController {

	private static final int QUESTIONS_NUMBER = 5;

	private static final Logger logger = LogManager.getLogger(WebController.class);

	@Autowired
	private ModelTest modelTest;
	@Autowired
	private Dao dao;

	@RequestMapping(value = "/")
	public String index(@Autowired Model model) {
		logger.info("Visited / path");
		model.addAttribute("testlink", "runtest");
		model.addAttribute("questionsNumber", QUESTIONS_NUMBER);
		return "index";
   }

	@RequestMapping(value = "/runtest")
	public String runtest(@Autowired Model model)
			throws NotEnoughQuestionsException {
		modelTest.init(QUESTIONS_NUMBER);
		modelTest.loadTasks(dao);
		logger.info("Visited /runtest");
		return runTestPost(model, -1);
   }

	@RequestMapping(value = "/runtest", method = RequestMethod.POST)
	public String runTestPost(@Autowired Model model, @RequestParam int answer) {
		if (modelTest.getQuestionsNumber() == 0)
			return index(model);
		if (answer != -1) {
			modelTest.putAnswer(answer);
			modelTest.nextQuestion();
		}
		model.addAttribute("questionNumber", modelTest.currentQuestion() + 1);
		model.addAttribute("question_text", modelTest.getQuestion());
		String answers[] = modelTest.getAnswers();
		model.addAttribute("answers", answers);
		model.addAttribute("mainPageLink", "/");
		model.addAttribute("testlink",
			modelTest.isLastQuestion() ? "results" : "runtest");

		logger.info("answered" + answer);
		logger.info("answered a question /runtestPost");
		return "test";
	}

	@RequestMapping(value = "/results", method = RequestMethod.POST)
	public String results(@Autowired Model model, @RequestParam int answer) {
		if (modelTest.getQuestionsNumber() == 0)
			return index(model);
		if (modelTest.currentQuestion() != 0) {
			modelTest.putAnswer(answer);
		}
		String wrongQuestions[] = modelTest.getStringsWrongAnswer();
		model.addAttribute("correct_answer_number", QUESTIONS_NUMBER - wrongQuestions.length);
		model.addAttribute("allCorrect", wrongQuestions.length == 0);
		if (wrongQuestions.length != 0)
			model.addAttribute("incorrect_questions", wrongQuestions);

		model.addAttribute("questionsNumber", QUESTIONS_NUMBER);
		model.addAttribute("mainPageLink", "/");
		logger.info("answered" + answer);
		logger.info("processed /results");
		return "results";
	}
}

