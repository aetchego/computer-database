package client;

import java.util.Scanner;

import controller.Controller;

public class UserInterface {

	private Scanner sc;
	
	public void displayChoices() {
		int choice = 0;
		do {
			System.out.println("Press 1 to list computers");
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

		System.out.println(choice);
		this.operations(choice);	
	}
	
	public void operations(int option) {
		switch (option) {
			case 1:
				Controller.listComputers();
				break;
			case 2: 
				System.out.println("List companies");
				break;
			case 3: 
				System.out.println("Show computers details");
				break;
			case 4: 
				System.out.println("Create a computer");
				break;
			case 5: 
				System.out.println("Update a computer");
				break;
			case 6: 
				System.out.println("Delete a computer");
				break;
			case 7:
				System.out.println("Bye !");
				sc.close();
				System.exit(1);
				
		}
	}
}
