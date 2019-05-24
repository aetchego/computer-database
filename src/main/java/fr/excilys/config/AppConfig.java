package fr.excilys.config;

import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan("fr.excilys")
@EnableTransactionManagement
public class AppConfig {

	@Bean
	public DataSource dataSource(HikariConfig config) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		return new HikariDataSource(config);
	}

	@Bean
	public HikariConfig hikariConfig() {
		return new HikariConfig("/hikari.properties");
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