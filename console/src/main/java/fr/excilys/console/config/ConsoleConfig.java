package fr.excilys.console.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fr.excilys.services.config.ServiceConfig;

@Configuration
@ComponentScan(basePackages = { "fr.excilys.console.controller",
		"fr.excilys.console.cli" }, excludeFilters = @ComponentScan.Filter(Configuration.class))
@Import(ServiceConfig.class)
public class ConsoleConfig {

}
