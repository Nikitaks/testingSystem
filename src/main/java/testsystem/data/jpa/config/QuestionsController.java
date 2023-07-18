package testsystem.data.jpa.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import testsystem.controllers.WebController;
import testsystem.data.jpa.questions.Questions;
import testsystem.data.jpa.questions.QuestionsService;
import testsystem.data.jpa.wordings.Wordings;
import testsystem.data.jpa.wordings.WordingsService;


@Controller
public class QuestionsController {

	private static final Logger logger = LogManager.getLogger(QuestionsController.class);

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private WordingsService wordingsService;

    @RequestMapping("/jpa")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("indexjpa");
        return mav;
    }

    @RequestMapping("/questions")
    public ModelAndView questions() {
        List<Questions> listQuestions = questionsService.listAll();
        ModelAndView mav = new ModelAndView("questions");
        mav.addObject("listQuestions", listQuestions);
        logger.info("idList: ");
        List<Long> list = questionsService.getQuestions_id();
		for (long i : list) {
			logger.info(String.valueOf(i));
		}
		Collections.shuffle(list);
		List<Long> shortList = list.stream().limit(5).collect(Collectors.toList());
		logger.info("5List: ");
		for (Questions i : questionsService.findAllById(shortList)) {
			logger.info(i.toString() + i.getWordings().toString());
		}
		return mav;
    }

    @RequestMapping("/wordings")
    @ResponseBody
    public ModelAndView wordings() {
    	List<Wordings> listWordings = wordingsService.listAll();
        ModelAndView mav = new ModelAndView("wordings");
        mav.addObject("listWordings", listWordings);
        return mav;
    }
    @RequestMapping("/randomWordings")
    @ResponseBody
    public ModelAndView randomWordings() {
    	Long count = wordingsService.count();
    	int randomIndex = (int)(Math.random() * count);
    	Page<Wordings> questionPage = wordingsService.findAll(PageRequest.of(randomIndex, 1));
    	Wordings wording = null;
        if (questionPage.hasContent()) {
        	wording = questionPage.getContent().get(0);
        	List<Wordings> randomWordings = new ArrayList<>();
        	randomWordings.add(wording);
            ModelAndView mav = new ModelAndView("randomWordings");
            mav.addObject("listWordings", randomWordings);
            return mav;
        }
        else {
        	return wordings();
        }
    }
}