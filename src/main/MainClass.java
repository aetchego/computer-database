package main;

import client.UserException;
import client.UserInterface;

public class MainClass {

	public static void main(String[] args) {
		
		UserInterface ui = new UserInterface();
		try {
			ui.displayChoices();
		} catch (UserException e) {
		}
	}

}
