package testsystem;

public interface Dao {
	TestTask[] getTasks(int questionsNumber) throws NotEnoughQuestionsException;
}
