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
		Integer brand = null;
		
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
			if (!tbrand.isEmpty()) {
				try {
					brand = Integer.parseInt(tbrand);
				} catch(Exception e) {
					System.out.println("[ERROR] Invalid ID.");
					return;
				}	
			}
			if (!inDate.isEmpty() && !outDate.isEmpty() && (inDate.compareTo(outDate) > 0)) {
				System.out.println("[ERROR] Introduced date cannot be after discontinued date.");
				return ;
			}
			
		} catch (ParseException e) {
			System.out.println("[ERROR] This is not a valid date.");
			return ;
		}
		ComputerService.createComputer(name, dateIn, dateOut, brand);
	}
	
	public static void showDetails(int id) {
		ComputerService.showDetails(id);
	}
	
	public static void deleteComputer(int id) {
		ComputerService.deleteComputer(id);
	}
}