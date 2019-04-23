package client;

import java.util.Scanner;

import controller.CompanyController;
import controller.ComputerController;

public class UserInterface {

	private Scanner sc;
	private ComputerController controller = new ComputerController();
	private String name = null;
	private String inDate = null;
	private String outDate = null;
	private String brand = null;
	private int offset;
	private int limit;
	private int id = 0;
	
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
				System.out.println("Press 7 to exit");
				sc = new Scanner(System.in);
				try {
					choice = Integer.parseInt(sc.nextLine());
				} catch(Exception e) {
				}	
			} while (choice < 1 || choice > 7);
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
	
	public void askId() {
		this.id = 0;
		System.out.println("\nPlease give the computer's ID you wishes to apply operations on :");
		try {
			this.id = Integer.parseInt(sc.nextLine());
		} catch(Exception e) {
			System.out.println("[ERROR] This is not a valid ID.");
		}
	}
	
	public void askPagination() {
		this.offset = 0;
		this.limit = 0;
		try {
			System.out.println("\nPlease, give the page number you want to reach :");
			this.offset = Integer.parseInt(sc.nextLine());
			System.out.println("\nPlease, give the maximum results number you want to retrieve :");
			this.limit = Integer.parseInt(sc.nextLine());
		} catch(Exception e) {
			System.out.println("[ERROR] Offset et limit values must be positive numbers.");
		}
	}
	
	public void operations(int option) {
		
		switch (option) {
			case 1:
				this.askPagination();
				if (this.offset > -1 && this.limit > -1) {controller.listComputers(this.offset, this.limit);}
				break;
			case 2: 
				CompanyController.listCompanies();
				break;
			case 3: 
				this.askId();
				if (this.id != 0) {controller.showDetails(this.id);}
				break;
			case 4: 
				this.askDetails();
				controller.createComputer(this.name, this.inDate, this.outDate, this.brand);
				break;
			case 5: 
				this.askId();
				if (this.id != 0) {
					this.askDetails();
					controller.updateComputer(this.name, this.inDate, this.outDate, this.brand, this.id);
				}
				break;
			case 6: 
				this.askId();
				if (this.id != 0) {controller.deleteComputer(this.id);}
				break;
			case 7:
				System.out.println("Bye !");
				sc.close();
				System.exit(1);
				
		}
	}
}
