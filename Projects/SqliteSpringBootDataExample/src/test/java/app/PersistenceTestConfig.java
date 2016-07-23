package app;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "app.repo" })
public class PersistenceTestConfig {

	/**
	 * @return The DataSource object that defines the connection url and driver class used to connect to the app's DB
	 */
	@Bean
	public DataSource dataSource() {
		System.out.println("\n*** SYSTEM IS USING test.db\n");
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.sqlite.JDBC");
		dataSourceBuilder.url("jdbc:sqlite:test.db");
		return dataSourceBuilder.build();
	}

}
