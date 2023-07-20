package testsystem;

import java.util.Arrays;

public class TestTask {

	private String question;
	private String[] answers;
	private int correctAnswer;
	private int userAnswer;

	public TestTask(String question, String[] answers, int correctAnswer) {
		super();
		this.question = question;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
	}

	@Override
	public String toString() {
		return "TestTask [question=" + question + ", answers=" + Arrays.toString(answers) + ", correctAnswer="
				+ correctAnswer + "]";
	}

	public void setUserAnswer(int userAnswer) {
		this.userAnswer = userAnswer;
	}

	public boolean ifUserAnswerWrong() {
		return userAnswer != correctAnswer;
	}

	public String getQuestion() {
		return question;
	}

	public String[] getAnswers() {
		return answers;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
