package fr.excilys.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = { "fr.excilys.persistence.dao",
		"fr.excilys.persistence.mapper" }, excludeFilters = @ComponentScan.Filter(Configuration.class))
@EnableTransactionManagement
public class AppConfig {

	@Bean(destroyMethod = "close")
	public DataSource dataSource(HikariConfig config) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		return new HikariDataSource(config);
	}

	@Bean
	public HikariConfig hikariConfig() {
		// This will reference one line at a time
		String line = null;
		String filename = "/hikari.properties";

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(filename);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + filename + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + filename + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		return new HikariConfig("/hikari.properties");
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public MessageSource messageSource() {
		final ResourceBundleMessageSource bundleMessage = new ResourceBundleMessageSource();
		bundleMessage.addBasenames("bundle");
		return bundleMessage;
	}

}