package testsystem;

import java.util.Arrays;


public class ModelTest {

	private int questionsNumber;
	private int currentQuestionNumber;
	private TestTask[] taskList;

	public ModelTest() {
		currentQuestionNumber = 0;
	}

	public void init(int questionsNumber) {
		this.questionsNumber = questionsNumber;
		currentQuestionNumber = 0;
		taskList = null;
	}

	public void setQuestionsNumber(int questionsNumber) {
		this.questionsNumber = questionsNumber;
	}

	public int getQuestionsNumber() {
		return questionsNumber;
	}

	public int nextQuestion() {
		if (currentQuestionNumber < (questionsNumber - 1))
			currentQuestionNumber++;
		return currentQuestionNumber;
	}


	public boolean isLastQuestion() {
		return currentQuestionNumber == (questionsNumber - 1);
	}

	public int currentQuestion() {
		return currentQuestionNumber;
	}

	public void setTaskList(TestTask[] taskList) throws NotEnoughQuestionsException {
		if ((taskList == null) || (taskList.length < questionsNumber))
			throw new NotEnoughQuestionsException();
		this.taskList = taskList;
	}

	public String getQuestion() {
		return taskList[currentQuestionNumber].getQuestion();
	}

	public String[] getAnswers() {
		return taskList[currentQuestionNumber].getAnswers();
	}

	public void putAnswer(int answer) {
		taskList[currentQuestion()].setUserAnswer(answer);
	}

	public boolean ifUserAnswerWrong() {
		return taskList[currentQuestion()].ifUserAnswerWrong();
	}

	public String[] getStringsWrongAnswer() {
		return Arrays.stream(taskList)
		.filter(x -> x.ifUserAnswerWrong())
		.map(x -> x.getQuestion())
		.toArray(size -> new String[size]);
	}

	public void loadTasks(Dao dao) throws NotEnoughQuestionsException {
		taskList = dao.getTasks(questionsNumber);
		//loadTasksFromDatabase();
	}

	public static void main(String[] args) throws NotEnoughQuestionsException {
		final int questionsNumber = 5;
		ModelTest modelTest = new ModelTest();
		modelTest.setQuestionsNumber(questionsNumber);
		modelTest.loadTasks(new DaoInMemory());

		for (int j = 0; j < questionsNumber; j++) {
			modelTest.putAnswer(0);
			System.out.format("%n>%s%n1.%s%n2.%s%n3.%s%n4.%s%n(%s)",modelTest.getQuestion(),
			modelTest.getAnswers()[0],modelTest.getAnswers()[1],modelTest.getAnswers()[2],
			modelTest.getAnswers()[3], ! modelTest.ifUserAnswerWrong());
			modelTest.nextQuestion();
		}
		System.out.println("wrong answer questions:");
		for (String wrongQuestion : modelTest.getStringsWrongAnswer()) {
			System.out.println(wrongQuestion);
		}
	}

}
