package fr.excilys.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import fr.excilys.client.UserException;

public class Validator {

	private static Validator instance = null;
	
	private Validator() {
		
	}
	
	public static Validator getInstance() {
		if (instance == null)
			instance = new Validator();
		return instance;
	}
	
	private void checkName(String name) throws UserException {
		if (name == null || name.trim().isEmpty())
			throw new UserException("[ERROR] Name cannot be empty.");
	}
	
	private Optional<LocalDate> checkDateParsing(String date) throws UserException {
		if (date == null || date.trim().isEmpty())
			return Optional.empty() ;
		try {
			LocalDate parsed = LocalDate.parse(date);
			if (parsed.isAfter(LocalDate.now()))
				throw new UserException("[ERROR] Date cannot be after today.");
			if (parsed.isBefore(LocalDate.parse("1970-01-01")))
				throw new UserException("[ERROR] Date cannot be before 01/01/1970.");
			return Optional.of(parsed);
		} catch (DateTimeParseException e) {
			throw new UserException("[ERROR] Date parsing exception.");
		}
	}
	
	private void compareDates(Optional<LocalDate> inDate, Optional<LocalDate> outDate) throws UserException {
		if (inDate.isPresent() && outDate.isPresent() && inDate.get().isAfter(outDate.get()))
			throw new UserException("[ERROR] Introduced date cannot be after discontinued date.");
	}
	
	public void check(String name, String inDate, String outDate) throws UserException {
		this.checkName(name);
		Optional<LocalDate> introduced = this.checkDateParsing(inDate);
		Optional<LocalDate> discontinued = this.checkDateParsing(outDate);
		this.compareDates(introduced, discontinued);
		
	}
}
