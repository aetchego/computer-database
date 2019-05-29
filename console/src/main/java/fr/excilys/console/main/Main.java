package fr.excilys.console.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.excilys.console.cli.UserInterface;
import fr.excilys.console.config.ConsoleConfig;
import fr.excilys.persistence.dao.CompanyDao;

public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsoleConfig.class);
		UserInterface ui = context.getBean(UserInterface.class);
		CompanyDao dao = context.getBean(CompanyDao.class);

		ui.displayChoices();
		dao.search();
		context.close();
	}
}
