package fr.excilys.binding.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;

import fr.excilys.services.config.ServiceConfig;

@Configuration
@ComponentScan(basePackages = { "fr.excilys.binding.mapper" })
@Import(ServiceConfig.class)
public class BindingConfig {
	@Bean
	public MessageSource messageSource() {
		final ResourceBundleMessageSource bundleMessage = new ResourceBundleMessageSource();
		bundleMessage.addBasenames("bundle");
		return bundleMessage;
	}
}
