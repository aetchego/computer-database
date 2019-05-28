package fr.excilys.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fr.excilys.persistence.config.PersistenceConfig;

@Configuration
@ComponentScan(basePackages = { "fr.excilys.service.services" })
@Import(PersistenceConfig.class)
public class ServiceConfig {

}
