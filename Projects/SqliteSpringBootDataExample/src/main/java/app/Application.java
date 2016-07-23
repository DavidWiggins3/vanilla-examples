package app;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import app.service.PersistentDataService;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "app.repo" })
public class Application {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ApplicationContext ctx = SpringApplication.run(Application.class);
		PersistentDataService p = ctx.getBean(PersistentDataService.class);
		System.out.print("From the main method: ");
		p.printUserCount();
	}

	/**
	 * @return The DataSource object that defines the connection url and driver class used to connect to the app's DB
	 */
	@Bean
	public DataSource dataSource() {
		System.out.println("\n*** SYSTEM IS USING prod.db\n");
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.sqlite.JDBC");
		dataSourceBuilder.url("jdbc:sqlite:prod.db");
		return dataSourceBuilder.build();
	}
	
}