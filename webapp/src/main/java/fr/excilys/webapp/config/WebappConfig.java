package fr.excilys.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fr.excilys.services.config.ServiceConfig;

@Configuration
@Import({ ServiceConfig.class/* , WebSecurityConfiguration.class */ })
public class WebappConfig {

}
