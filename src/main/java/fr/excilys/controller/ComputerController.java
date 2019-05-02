package fr.excilys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import fr.excilys.client.UserException;
import fr.excilys.model.Computer;
import fr.excilys.service.ComputerService;

public class ComputerController {

	private java.sql.Date dateIn = null;
	private java.sql.Date dateOut = null;
	private static ComputerController instance = null;
	private ComputerService computerService = ComputerService.getInstance();

	private ComputerController() {
	}

	public static ComputerController getInstance() {
		if (instance == null)
			instance = new ComputerController();
		return instance;
	}

	public List<Computer> listComputers(int offset, int limit) throws UserException {
		return (computerService.listComputers(offset, limit));
	}

	public void checkDetails(String name, String inDate, String outDate)
			throws UserException, ParseException, Exception {
		if (name.isEmpty())
			throw new UserException("[ERROR] Name cannot be empty.");
		if (!inDate.isEmpty())
			dateIn = new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(inDate).getTime());
		if (!outDate.isEmpty())
			dateOut = new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(outDate).getTime());
	}

	public void createComputer(String name, String inDate, String outDate, String brand) throws Exception, ParseException, UserException {
	//	try {
			this.checkDetails(name, inDate, outDate);
			computerService.createComputer(name, dateIn, dateOut, brand);
		/*} catch (UserException e) {
			System.out.println("[ERROR] You must specify computer's name.");
			System.out.println("[ERROR] Introduced date cannot be after discontinued date.");
		} catch (ParseException e) {
			System.out.println("[ERROR] This is not a valid date.");
		} catch (Exception e) {
			System.out.println("[ERROR] ID must be a number.");
		}*/
	}

	public void showDetails(int id) throws UserException {
		computerService.showDetails(id);
	}

	public void deleteComputer(int id) throws UserException {
		computerService.deleteComputer(id);
	}

	public void updateComputer(String name, String inDate, String outDate, String brand, int id) {
		try {
			this.checkDetails(name, inDate, outDate);
		} catch (UserException e) {
			System.out.println("[ERROR] You must specify computer's name.");
			System.out.println("[ERROR] Introduced date cannot be after discontinued date.");
		} catch (ParseException e) {
			System.out.println("[ERROR] This is not a valid date.");
		} catch (Exception e) {
			System.out.println("[ERROR] ID must be a number.");
		}
	}
	
	public int countComputers() throws UserException {
		int res = 0;
		res = computerService.countComputers();
		return res;
	}

}