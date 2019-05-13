package fr.excilys.client;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.excilys.controller.CompanyController;
import fr.excilys.controller.ComputerController;

public class UserInterface {

	private Scanner sc;
	private int id;
	CompanyController companyController = CompanyController.getInstance();
	ComputerController computerController = ComputerController.getInstance();

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

	public void askId() throws UserException {
		this.id = 0;
		System.out.println("\nPlease give the computer's ID you wishes to apply operations on :");
		try {
			this.id = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			throw new UserException("[ERROR] This is not a valid ID.");
		}
	}

	public void operations(int option) {

		try {
			switch (option) {
			case 1:
				this.askId();
				if (this.id > 0)
					companyController.deleteCompany(this.id);
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
