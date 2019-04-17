package controller;

import java.util.ArrayList;
import java.util.Date;

import dao.DaoUtilitaries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import service.ComputerService;

public class ComputerController {

	public static void listComputers() {
		ComputerService.listComputers();
	}
	
	public static void createComputer(String name, String inDate, String outDate, String tbrand) {
		
		java.sql.Date dateIn = null;
		java.sql.Date dateOut = null;
		String brand = null;
		
		if (name.isEmpty()) {
			System.out.println("[ERROR] You must specify computer's name.");
			return ;
		}
		try {
			if (!inDate.isEmpty()) {
				dateIn = new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(inDate).getTime());
			}
			if (!outDate.isEmpty()) {
				dateOut = new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(outDate).getTime());
			}
			if (!tbrand.isEmpty())
				brand = tbrand;
		} catch (ParseException e) {
			System.out.println("[ERROR] This is not a valid date.");
			return ;
		}
		ComputerService.createComputer(name, dateIn, dateOut, brand);
	}
	
	/*public static boolean isExist(int id) {
		ArrayList<Object> sql = new ArrayList<>();
		sql = DaoUtilitaries.databaseAccess("", 
		this.factory, 1, computer.getName(), computer.getIntroduced(), computer.getDiscontinued());
		DaoUtilitaries.closeConnexions((ResultSet) sql.get(0), (PreparedStatement)sql.get(1), (Connection)sql.get(2));
		return true;
	}*/
	
	public static void deleteComputer(int id) {
		//System.out.println(ComputerController.isExist(id));
		//System.out.println(id);
		ComputerService.deleteComputer(id);
	}
}