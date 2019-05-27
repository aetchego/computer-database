package fr.excilys.client;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fr.excilys.controller.CompanyController;
import fr.excilys.exception.UserException;

@Component
public class UserInterface {

	private Scanner sc;
	private int id;
	private final CompanyController companyController;

	public UserInterface(CompanyController companyController) {
		super();
		this.companyController = companyController;
	}

	public void askId() throws UserException {
		this.id = 0;
		System.out.println("\nPlease give the computer's ID you wishes to apply operations on :");
		try {
			this.id = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			throw new UserException("[ERROR] This is not a valid ID.");
		}
	}

	public void displayChoices() {
		int choice = 0;
		while (choice != 2) {
			do {
				System.out.println("Press 1 to delete a company");
				System.out.println("Press 2 to exit");
				sc = new Scanner(System.in);
				try {
					choice = Integer.parseInt(sc.nextLine());
				} catch (Exception e) {
					System.out.println("[ERROR] This is not a valid choice.");
				}
			} while (choice < 1 || choice > 2);
			this.operations(choice);
		}
	}

	public void operations(int option) {

		try {
			switch (option) {
			case 1:
				this.askId();
				if (this.id > 0)
					companyController.delete(this.id);
				break;
			case 2:
				System.out.println("Bye !");
				sc.close();
				System.exit(1);
			}
		} catch (UserException e) {
			Logger logger = LoggerFactory.getLogger(UserInterface.class);
			logger.info(e.getMessage());
		}
	}
}
