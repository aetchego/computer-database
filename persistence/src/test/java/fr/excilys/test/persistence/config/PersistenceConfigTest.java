package fr.excilys.test.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariConfig;

import fr.excilys.persistence.config.PersistenceConfig;

@Configuration
@ComponentScan(basePackages = { "fr.excilys.test.persistence.database" })
@Import(PersistenceConfig.class)
public class PersistenceConfigTest {
	@Bean
	@Primary
	public HikariConfig hikariConfig() {
		final HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setUsername("test");
		hikariConfig.setPassword("test");
		hikariConfig.setDriverClassName("org.h2.Driver");
		hikariConfig.setJdbcUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
		return hikariConfig;
	}
}