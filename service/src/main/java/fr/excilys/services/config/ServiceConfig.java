package fr.excilys.services.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;

import fr.excilys.persistence.config.PersistenceConfig;

@Configuration
@ComponentScan(basePackages = { "fr.excilys.service.services" })
@Import(PersistenceConfig.class)
public class ServiceConfig {
	@Bean
	public MessageSource messageSource() {
		final ResourceBundleMessageSource bundleMessage = new ResourceBundleMessageSource();
		bundleMessage.addBasenames("bundle");
		return bundleMessage;
	}
}
