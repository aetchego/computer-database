package fr.excilys.client;

import java.util.Scanner;

import fr.excilys.controller.CompanyController;
import fr.excilys.controller.ComputerController;

public class UserInterface {

	private Scanner sc;
	private String name = null;
	private String inDate = null;
	private String outDate = null;
	private String brand = null;
	private int offset;
	private int limit;
	private int id;
	CompanyController companyController = CompanyController.getInstance();
	ComputerController computerController = ComputerController.getInstance();

	public void displayChoices() {
		int choice = 0;
		while (choice != 7) {
			do {
				System.out.println("\nPress 1 to list computers");
				System.out.println("Press 2 to list companies");
				System.out.println("Press 3 to show computer details");
				System.out.println("Press 4 to add a computer");
				System.out.println("Press 5 to update computer information");
				System.out.println("Press 6 to delete a computer");
				System.out.println("Press 8 to exit");
				sc = new Scanner(System.in);
				try {
					choice = Integer.parseInt(sc.nextLine());
				} catch (Exception e) {
					System.out.println("[ERROR] This is not a valid choice.");
				}
			} while (choice < 1 || choice > 8);
			this.operations(choice);
		}
	}

	public void askDetails() {
		System.out.println("\nInsert computer's name :");
		this.name = sc.nextLine();
		System.out.println("\nInsert computer's introduction date [dd/MM/yyyy] (optionnal) :");
		this.inDate = sc.nextLine();
		System.out.println("\nInsert computer's discontinuation date [dd/MM/yyyy] (optionnal) :");
		this.outDate = sc.nextLine();
		System.out.println("\nInsert computer brand's id (optionnal) :");
		this.brand = sc.nextLine();
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

	public void askPagination() throws UserException {
		this.offset = 0;
		this.limit = 0;
		try {
			System.out.println("\nPlease, give the page number you want to reach :");
			this.offset = Integer.parseInt(sc.nextLine());
			System.out.println("\nPlease, give the maximum results number you want to retrieve :");
			this.limit = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			throw new UserException("[ERROR] Offset et limit values must be positive numbers.");
		}
	}

	public void operations(int option) {

		try {
			switch (option) {
			case 1:
				this.askPagination();
				if (this.offset > -1 && this.limit > -1) {
					computerController.listComputers(this.offset, this.limit);
				}
				break;
			case 2:
				companyController.listCompanies();
				break;
			case 3:
				this.askId();
				if (this.id != 0) {
					computerController.showDetails(this.id);
				}
				break;
			case 4:
				this.askDetails();
				//computerController.createComputer(this.name, this.inDate, this.outDate, this.brand);
				break;
			case 5:
				this.askId();
				if (this.id != 0) {
					this.askDetails();
					computerController.updateComputer(this.name, this.inDate, this.outDate, this.brand, this.id);
				}
				break;
			case 6:
				this.askId();
				if (this.id != 0) {
					computerController.deleteComputer(this.id);
				}
				break;
			case 8:
				System.out.println("Bye !");
				sc.close();
				System.exit(1);
			}
		} catch (UserException e) {
			//Logger logger = LoggerFactory.getLogger(UserInterface.class);
			//logger.info(e.getMsg());
		}
	}
}
