package testsystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.context.annotation.SessionScope;

import testsystem.data.Dao;
import testsystem.data.inmemory.DaoInMemory;
import testsystem.data.indatabase.DaoInDatabase;

@SpringBootApplication
@PropertySource("file:src/main/resources/properties/app.properties")
public class Application {

	private String databaseConfigPath = "src/main/resources/properties/mysql-sample-properties.xml";

	@Value( "${dataBaseType:inMemoryDatabase}" )
	private String dataBaseType;

	@Bean
	@SessionScope
	public ModelTest modelTest() {
		return new ModelTest();
	}

	@Bean
	public Dao dao() {
		switch (dataBaseType) {
			case "inMemoryDatabase":
				return new DaoInMemory();
			case "SQLDatabase":
				DaoInDatabase dao = new DaoInDatabase();
				dao.loadConfigFile(databaseConfigPath);
				dao.makeConnection();
				return dao;
			default:
				return new DaoInMemory();
		}
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}