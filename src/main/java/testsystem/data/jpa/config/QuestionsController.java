package testsystem.data.jpa.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import testsystem.data.jpa.questions.Questions;
import testsystem.data.jpa.questions.QuestionsService;
import testsystem.data.jpa.wordings.Wordings;
import testsystem.data.jpa.wordings.WordingsService;


@Controller
public class QuestionsController {

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