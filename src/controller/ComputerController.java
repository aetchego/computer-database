package controller;

import java.util.ArrayList;
import java.util.Date;

import client.UserException;
import dao.DaoUtilitaries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import service.ComputerService;

public class ComputerController {

	private java.sql.Date dateIn = null;
	private java.sql.Date dateOut = null;
	private Integer brand = null;
	
	public void listComputers(int offset, int limit) {
		ComputerService.listComputers(offset, limit);
	}
	
	public void checkDetails(String name, String inDate, String outDate, String tbrand) throws UserException, ParseException, Exception {
		if (name.isEmpty())
			throw new UserException();
		if (!inDate.isEmpty())
			dateIn = new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(inDate).getTime());
		if (!outDate.isEmpty())
			dateOut = new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(outDate).getTime());
		if (!tbrand.isEmpty())
			brand = Integer.parseInt(tbrand);
	}
	
	public void createComputer(String name, String inDate, String outDate, String brand) {
		try {
			this.checkDetails(name, inDate, outDate, brand);
			ComputerService.createComputer(name, dateIn, dateOut, this.brand);
		} catch (UserException e) {
			System.out.println("[ERROR] You must specify computer's name.");
			System.out.println("[ERROR] Introduced date cannot be after discontinued date.");
		} catch (ParseException e) {
			System.out.println("[ERROR] This is not a valid date.");
		} catch (Exception e) {
			System.out.println("[ERROR] ID must be a number.");
		}
	}
	
	public void showDetails(int id) {
		ComputerService.showDetails(id);
	}
	
	public void deleteComputer(int id) {
		ComputerService.deleteComputer(id);
	}
	
	public void updateComputer(String name, String inDate, String outDate, String brand, int id) {
		try {
			this.checkDetails(name, inDate, outDate, brand);
			ComputerService.updateComputer(name, this.dateIn, this.dateOut, this.brand, id);
		} catch (UserException e) {
			System.out.println("[ERROR] You must specify computer's name.");
			System.out.println("[ERROR] Introduced date cannot be after discontinued date.");
		} catch (ParseException e) {
			System.out.println("[ERROR] This is not a valid date.");
		} catch (Exception e) {
			System.out.println("[ERROR] ID must be a number.");
		}
	}
	
	
}