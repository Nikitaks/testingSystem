package testsystem.data.jpa.wordings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import testsystem.data.jpa.questions.Questions;


@Entity
public class Wordings {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long question_id;
    private String question_text;
    private String answer0;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer5;

    //relations
    @OneToOne
    @JoinColumn(name = "question_id", insertable = false,
    			updatable = false)
    private Questions questions;

    protected Wordings() {
    }

	protected Wordings(Long id, Long question_id, String question_text, String answer0, String answer1, String answer2,
			String answer3, String answer4, String answer5) {
		super();
		this.id = id;
		this.question_id = question_id;
		this.question_text = question_text;
		this.answer0 = answer0;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.answer5 = answer5;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Long question_id) {
		this.question_id = question_id;
	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}

	public String getAnswer0() {
		return answer0;
	}

	public void setAnswer0(String answer0) {
		this.answer0 = answer0;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public String getAnswer5() {
		return answer5;
	}

	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

}