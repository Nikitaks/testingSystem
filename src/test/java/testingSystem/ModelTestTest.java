package testingSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import testsystem.ModelTest;
import testsystem.TestTask;
import testsystem.exceptions.NotEnoughQuestionsException;

public class ModelTestTest {
	//arrange
	ModelTest modelTest = new ModelTest();
	private final int questionsNumber = 12;

	@Before
	public void beforeEach() {
		modelTest.init(questionsNumber);
	}

	@Test
	public void init() {
		//act in @BeforeEach
		//assert
		Assert.assertEquals(questionsNumber, modelTest.getQuestionsNumber());
		Assert.assertEquals(0, modelTest.currentQuestion());
	}

	@Test
	public void nextQuestion() {
		for (int i = 0; i < questionsNumber; i++) {
			Assert.assertEquals(i, modelTest.currentQuestion());
			modelTest.nextQuestion();
		}
		Assert.assertEquals(questionsNumber - 1, modelTest.currentQuestion());
	}

	@Test
	public void isLastQuestion() {
		for (int i = 0; i < questionsNumber - 1; i++) {
			Assert.assertFalse(String.valueOf(i), modelTest.isLastQuestion());
			modelTest.nextQuestion();
		}
		Assert.assertTrue(modelTest.isLastQuestion());
	}

	@Test
	public void setTaskList() {
		try {
			modelTest.setTaskList(null);
			Assert.fail("Exception by null not thrown");
	    } catch (Throwable e) {
	    	Assert.assertEquals(NotEnoughQuestionsException.class, e.getClass());
	    }
		try {
			modelTest.setTaskList(new TestTask[questionsNumber - 1]);
			Assert.fail("Exception by questionsNumber - 1 not thrown");
	    } catch (Throwable e) {
	    	Assert.assertEquals(NotEnoughQuestionsException.class, e.getClass());
	    }
		try {
			modelTest.setTaskList(new TestTask[questionsNumber]);
	    } catch (Throwable e) {
	    	Assert.fail("Exception by questionsNumber thrown");
	    }
		try {
			modelTest.init(1);
			modelTest.setTaskList(new TestTask[]
				{new TestTask("question", new String[] {"a0","a1","a2","a3"}, 2)});

	    } catch (Throwable e) {
	    	Assert.fail("Exception by real question setting thrown");
	    }
	}

	@Test
	public void getQuestion() {
		try {
			modelTest.init(1);
			modelTest.setTaskList(new TestTask[]
				{new TestTask("question", new String[] {"a0","a1","a2","a3"}, 2)});
			Assert.assertEquals("question", modelTest.getQuestion());
	    } catch (Throwable e) {
	    	Assert.fail("Exception by real question setting thrown");
	    }
	}

	@Test
	public void getAnswers() {
		try {
			modelTest.init(1);
			modelTest.setTaskList(new TestTask[]
				{new TestTask("question", new String[] {"a0","a1","a2","a3"}, 2)});
			Assert.assertArrayEquals(new String[] {"a0","a1","a2","a3"}, modelTest.getAnswers());
	    } catch (Throwable e) {
	    	Assert.fail("Exception by real question setting thrown");
	    }
	}
}
