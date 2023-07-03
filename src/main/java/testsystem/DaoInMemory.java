package testsystem;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DaoInMemory implements Dao {

	private TestTask[] taskList;

	@Override
	public TestTask[] getTasks(int questionsNumber) throws NotEnoughQuestionsException {
		if ((taskList == null) || (taskList.length < questionsNumber))
			throw new NotEnoughQuestionsException();

		TestTask[] shuffleTaskList = new TestTask[taskList.length];;
		List<TestTask> testTaskList = Arrays.asList(taskList);
		Collections.shuffle(testTaskList);
		testTaskList.toArray(shuffleTaskList);

		TestTask[] resultTaskList = new TestTask[questionsNumber];
		for (int i = 0; i < questionsNumber; i++) {
			resultTaskList[i] = shuffleTaskList[i];
		}
		return resultTaskList;
	}

	public DaoInMemory() {
		generateTasks();
	}

	private void generateTasks() {
		final int questionsNumber = 15;
		taskList = new TestTask[questionsNumber];
		int i = 0;

		String question = "Укажите утилитный класс, содержащий статические методы для работы с массивами.";
		String[] answers = {"Collections", "Paths", "Files", "Arrays"};
		int correctAnswer = 3;
		taskList[i++] = new TestTask(question, answers, correctAnswer);

		question = "Укажите утилитный класс, содержащий статические методы для работы с коллекциями.";
		answers = new String[]{"Collections", "Paths", "Files", "Arrays"};
		correctAnswer = 0;
		taskList[i++] = new TestTask(question, answers, correctAnswer);

		question = "Укажите утилитный класс, содержащий статические методы для работы с файлами.";
		answers = new String[]{"Collections", "Paths", "Files", "Arrays"};
		correctAnswer = 2;
		taskList[i++] = new TestTask(question, answers, correctAnswer);

		question = "Укажите утилитный класс, содержащий статические методы для работы с путями к файлам и директориям.";
		answers = new String[]{"Collections", "Paths", "Files", "Arrays"};
		correctAnswer = 1;
		taskList[i++] = new TestTask(question, answers, correctAnswer);

		question = "Укажите API для соединения с базами данных.";
		answers = new String[]{"DateTime API", "Stream API", "Servlet API", "JDBC API"};
		correctAnswer = 3;
		taskList[i++] = new TestTask(question, answers, correctAnswer);


		question = "Укажите нтерфейс Java, реализация которого расширяет функциональные "
				+ "возможности сервера, взаимодействует с клиентами посредством "
				+ "принципа запрос-ответ и могут обслуживать любые запросы.";
		answers = new String[]{"Сервлет", "Сессия", "BaseStream"};
		correctAnswer = 0;
		taskList[i++] = new TestTask(question, answers, correctAnswer);

		question = "Укажите типовые каталоги структуры веб-проекта.";
		answers = new String[]{"bin,lib,include", "src,main,java,resources,test"};
		correctAnswer = 1;
		taskList[i++] = new TestTask(question, answers, correctAnswer);

		question = "Как называется программа, представляющая собой сервер, который"
				+ " занимается системной поддержкой сервлетов и обеспечивает их жизненный "
				+ "цикл в соответствии с правилами, определёнными в спецификациях? Она"
				+ " является поставщиком страниц для другого веб-сервера, берёт на себя"
				+ " выполнение таких функций, как создание программной среды для "
				+ "функционирующего сервлета, идентификацию и "
				+ "авторизацию клиентов, организацию сессии для каждого из них.";
		answers = new String[]{"Веб-сервер", "Контейнер сервлетов", "СУБД", "Фреймворк"};
		correctAnswer = 1;
		taskList[i++] = new TestTask(question, answers, correctAnswer);

		question = "Укажите шаблон проектирования, который позволяет создавать различные объекты в "
				+ "зависимости от некоторых условий. ";
		answers = new String[]{"Singleton", "Strategy", "Factory", "Data Access Object"};
		correctAnswer = 2;
		taskList[i++] = new TestTask(question, answers, correctAnswer);

		question = "Укажите шаблон проектирования, который реализует гарантированно только "
				+ "один экземпляр класса и к этому"
				+ " объекту предоставляется глобальная точка доступа.";
		answers = new String[]{"Singleton", "Strategy", "Factory", "Data Access Object"};
		correctAnswer = 0;
		taskList[i++] = new TestTask(question, answers, correctAnswer);

		question = "Укажите шаблон проектирования, который абстрагирует доступ к данным от механизма"
				+ " их хранения.";
		answers = new String[]{"Singleton", "Strategy", "Factory", "Data Access Object"};
		correctAnswer = 3;
		taskList[i++] = new TestTask(question, answers, correctAnswer);

		question = "Укажите шаблон проектирования, предназначенный для определения семейства"
				+ " алгоритмов, инкапсуляции каждого из них и обеспечения их взаимозаменяемости."
				+ " Этот паттерн позволяет выбирать алгоритм путём определения соответствующего"
				+ " класса, а так же менять выбранный "
				+ "алгоритм независимо от объектов-клиентов, которые его используют.";
		answers = new String[]{"Singleton", "Strategy", "Factory", "Data Access Object"};
		correctAnswer = 1;
		taskList[i++] = new TestTask(question, answers, correctAnswer);

		question = "Укажите HTTP-метод, запрашивающий только заголовок ресурса.";
		answers = new String[]{"PUT", "HEAD", "PATCH", "TRACE"};
		correctAnswer = 1;
		taskList[i++] = new TestTask(question, answers, correctAnswer);

		question = "Укажите HTTP-метод, который является идемпотентной версией POST.";
		answers = new String[]{"PUT", "HEAD", "PATCH", "TRACE"};
		correctAnswer = 0;
		taskList[i++] = new TestTask(question, answers, correctAnswer);

		question = "Укажите HTTP-метод, функция которого только в модификации существующего ресурса.";
		answers = new String[]{"PUT", "HEAD", "PATCH", "TRACE"};
		correctAnswer = 2;
		taskList[i++] = new TestTask(question, answers, correctAnswer);
	}
	public static void main(String[] args) throws NotEnoughQuestionsException {
		final int questionsNumber = 5;
		DaoInMemory daoInMemory = new DaoInMemory();

		for (TestTask testTask : daoInMemory.getTasks(questionsNumber)) {
			testTask.setUserAnswer(0);
			System.out.format("%n>%s%nAnswers:%s%n(%s)",testTask.getQuestion(),
					Arrays.toString(testTask.getAnswers()) , ! testTask.ifUserAnswerWrong());
		}
	}
}
