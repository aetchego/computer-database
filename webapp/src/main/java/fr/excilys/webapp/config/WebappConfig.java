package fr.excilys.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fr.excilys.binding.config.BindingConfig;
import fr.excilys.service.config.ServiceConfig;

@Configuration
@Import({ BindingConfig.class, ServiceConfig.class, WebSecurityConfiguration.class })
public class WebappConfig {

}
