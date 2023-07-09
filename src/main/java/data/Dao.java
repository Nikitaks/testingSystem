package data;

import exceptions.NotEnoughQuestionsException;
import testsystem.TestTask;

public interface Dao {
	TestTask[] getTasks(int questionsNumber) throws NotEnoughQuestionsException;
}
