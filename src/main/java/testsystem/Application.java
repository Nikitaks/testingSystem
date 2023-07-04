package testsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.SessionScope;

@SpringBootApplication
public class Application {

	@Bean
	@SessionScope
	public ModelTest modelTest() {
		return new ModelTest();
	}

	@Bean
	public Dao dao() {
		//Uncomment to use In-memory-database
		//return new DaoInMemory();

		//Uncomment to use MySQL-database
		DaoInDatabase dao = new DaoInDatabase();
		dao.loadConfigFile();
		dao.makeConnection();
		return dao;
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}