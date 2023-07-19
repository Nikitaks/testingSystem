package testsystem.data.jpa.questions;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import testsystem.data.jpa.wordings.Wordings;

@Entity
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long question_id;

    private Long section_id;
    private int answers_number;
    private int correct_answer;

    //relations
    @OneToOne
    @JoinColumn(name = "question_id", insertable = false,
    			updatable = false)
    private Wordings wordings;

    @Transient
    private List<String> answers = new ArrayList<>();

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


	public Wordings getWordings() {
		return wordings;
	}

	public void setWordings(Wordings wordings) {
		this.wordings = wordings;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Questions [question_id=" + question_id + ", section_id=" + section_id + ", answers_number="
				+ answers_number + ", correct_answer=" + correct_answer + "]";
	}

}