package testsystem.data;

import testsystem.exceptions.NotEnoughQuestionsException;
import testsystem.TestTask;

public interface Dao {
	TestTask[] getTasks(int questionsNumber) throws NotEnoughQuestionsException;
}
