package controller;

import service.ComputerService;

public class ComputerController {

	public static void listComputers() {
		ComputerService.listComputers();
	}
}