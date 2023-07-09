package testsystem.data.jpa.questions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long question_id;

    private Long section_id;
    private int answers_number;
    private int correct_answer;

    protected Questions() {
    }

	protected Questions(Long section_id, int answers_number, int correct_answer) {
		super();
		this.section_id = section_id;
		this.answers_number = answers_number;
		this.correct_answer = correct_answer;
	}

	public Long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Long question_id) {
		this.question_id = question_id;
	}

	public Long getSection_id() {
		return section_id;
	}

	public void setSection_id(Long section_id) {
		this.section_id = section_id;
	}

	public int getAnswers_number() {
		return answers_number;
	}

	public void setAnswers_number(int answers_number) {
		this.answers_number = answers_number;
	}

	public int getCorrect_answer() {
		return correct_answer;
	}

	public void setCorrect_answer(int correct_answer) {
		this.correct_answer = correct_answer;
	}
}