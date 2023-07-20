package testingSystem;

import org.junit.Assert;
import org.junit.Test;

import testsystem.TestTask;

public class TestTaskTest {
	@Test
	public void ifUserAnswerWrong() {
		//arrange
		TestTask task = new TestTask(
			"question", new String[] {"a0","a1","a2","a3"},
			2);
		//act
		task.setUserAnswer(0);
		boolean result = task.ifUserAnswerWrong();
		//assert
		Assert.assertTrue(result);

		task.setUserAnswer(1);
		result = task.ifUserAnswerWrong();
		Assert.assertTrue(result);

		task.setUserAnswer(2);
		result = task.ifUserAnswerWrong();
		Assert.assertFalse(result);

		task.setUserAnswer(3);
		result = task.ifUserAnswerWrong();
		Assert.assertTrue(result);
	}
}
