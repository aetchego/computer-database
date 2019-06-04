package fr.excilys.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ WebSecurityConfiguration.class })
public class WebappConfig {

}
