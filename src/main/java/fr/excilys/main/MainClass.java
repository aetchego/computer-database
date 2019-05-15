package fr.excilys.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.excilys.client.UserInterface;
import fr.excilys.config.AppConfig;

public class MainClass {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserInterface ui = context.getBean(UserInterface.class);
		ui.displayChoices();
		context.close();
	}
}
