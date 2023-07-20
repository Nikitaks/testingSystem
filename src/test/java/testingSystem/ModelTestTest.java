package testingSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import testsystem.ModelTest;

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
}
