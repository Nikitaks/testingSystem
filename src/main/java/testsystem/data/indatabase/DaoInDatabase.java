package testsystem.data.indatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PreDestroy;

import testsystem.TestTask;
import testsystem.data.Dao;
import testsystem.exceptions.NotEnoughQuestionsException;

public class DaoInDatabase implements Dao {

	private String dbName;
	private Connection con;
	private JDBCTutorialUtilities myJDBCTutorialUtilities;

	private static final String[] answerNumberVariants = {"", "",
			"ANSWER0, ANSWER1",
			"ANSWER0, ANSWER1, ANSWER2",
			"ANSWER0, ANSWER1, ANSWER2, ANSWER3",
			"ANSWER0, ANSWER1, ANSWER2, ANSWER3, ANSWER4",
			"ANSWER0, ANSWER1, ANSWER2, ANSWER3, ANSWER4, ANSWER5"
			};

	public DaoInDatabase(Connection connArg, String dbNameArg) {
		super();
		this.con = connArg;
		this.dbName = dbNameArg;
	}

	public DaoInDatabase(Connection connArg) {
		super();
		this.con = connArg;
	}

	public DaoInDatabase() {
		super();
	}

	public void makeConnection() {
		try {
			this.con = myJDBCTutorialUtilities.getConnection();
		} catch (SQLException e) {
			JDBCTutorialUtilities.printSQLException(e);
		}
	}
	@PreDestroy
	public void closeConnection() {
		JDBCTutorialUtilities.closeConnection(this.con);
	}

	public void loadConfigFile(String databaseConfigPath) {
		try {
			myJDBCTutorialUtilities = new JDBCTutorialUtilities(databaseConfigPath);
		} catch (Exception e) {
			System.err.println("Problem reading properties file ");
			e.printStackTrace();
			return;
		}
	}

	public void showFormedQuestion(TestTask task) throws SQLException {
		System.out.println(task.toString());
	}

	public TestTask formQuestion(int questionId) throws SQLException {
		TestTask task = null;
		try (Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			int answerNumber = 0;
			ResultSet rsNubberAnswers = stmt.executeQuery("select ANSWERS_NUMBER "
					+ "from QUESTIONS where QUESTION_ID = " + questionId + ";");
			if (rsNubberAnswers.next()) {
				answerNumber = rsNubberAnswers.getInt("ANSWERS_NUMBER");
			}
			else
				return null;
			if ((answerNumber < 2) || (answerNumber > 6)) return null;
			ResultSet rs = stmt.executeQuery("select QUESTION_TEXT, "
					+ answerNumberVariants[answerNumber] + ", CORRECT_ANSWER " +
					"from WORDINGS, QUESTIONS " +
					"where WORDINGS.QUESTION_ID = QUESTIONS.QUESTION_ID"
					+ " and QUESTIONS.QUESTION_ID = " + questionId + ";");
			while (rs.next()) {
				String question = rs.getString("QUESTION_TEXT");
				String answers[] = new String[answerNumber];
				for (int i = 0; i < answerNumber; i++) {
					answers[i] = rs.getString("ANSWER" + i);
				}
				int correctAnswer = rs.getInt("CORRECT_ANSWER");
				task = new TestTask(question, answers, correctAnswer);
			}

		} catch (SQLException e) {
			JDBCTutorialUtilities.printSQLException(e);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return task;
	}

	@Override
	public TestTask[] getTasks(int questionsNumber) throws NotEnoughQuestionsException {
		TestTask[] taskSet = new TestTask[questionsNumber];
		try (Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			List<Integer> list = new ArrayList<>();
			ResultSet rs = stmt.executeQuery("select question_id from questions;");
			while (rs.next()) {
				int question_id = rs.getInt("QUESTION_ID");
				list.add(question_id);
			}
			Collections.shuffle(list);
			for (int i = 0; i < questionsNumber; i++) {
				taskSet[i] = formQuestion(list.get(i));
			}
		} catch (SQLException e) {
			JDBCTutorialUtilities.printSQLException(e);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return taskSet;
	}

	public static void main(String[] args) throws SQLException, NotEnoughQuestionsException {
		String databaseConfigPath = "properties/mysql-sample-properties.xml";
		System.out.println("Start...");

		DaoInDatabase myWordings = new DaoInDatabase();
		myWordings.loadConfigFile(databaseConfigPath);
		myWordings.makeConnection();

		System.out.println("\nTrying Question.");

		for (TestTask task : myWordings.getTasks(5))
			myWordings.showFormedQuestion(task);

		myWordings.closeConnection();
	}
}
