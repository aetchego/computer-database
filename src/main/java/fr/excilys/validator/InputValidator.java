package fr.excilys.validator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import fr.excilys.client.UserException;
import fr.excilys.dao.DaoConfigException;
import fr.excilys.dao.DaoException;
import fr.excilys.dao.DaoFactory;
import fr.excilys.dto.ComputerDTO;
import fr.excilys.model.Company;

public class InputValidator {

	private static InputValidator instance = null;
	
	private InputValidator() {
		
	}
	
	public static InputValidator getInstance() {
		if (instance == null)
			instance = new InputValidator();
		return instance;
	}
	
	private void checkName(String name) throws UserException {
		if (name == null || name.trim().isEmpty())
			throw new UserException("[ERROR] Name cannot be empty.");
	}
	
	private Optional<LocalDate> checkDateParsing(String date) throws UserException {
		if (date == null || date.trim().isEmpty())
			return Optional.empty();
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
	
	private void checkCompanyName(String brand) throws DaoException, DaoConfigException, SQLException, UserException {
		if (brand == null || brand.trim().isEmpty() || brand.equals("---"))
			return ;
		List<Company> comp = DaoFactory.getInstance().getCompany().read().getCompanies();
		for (Company e : comp)
			if (e.getName().equals(brand))
				return ;
		throw new UserException("[ERROR] Company name does not exist.");
	}
	
	private void compareDates(Optional<LocalDate> inDate, Optional<LocalDate> outDate) throws UserException {
		if (inDate.isPresent() && outDate.isPresent() && inDate.get().isAfter(outDate.get()))
			throw new UserException("[ERROR] Introduced date cannot be after discontinued date.");
	}
	
	public void check(ComputerDTO computer) throws UserException, DaoException, DaoConfigException, SQLException {
		this.checkName(computer.getName());
		Optional<LocalDate> introduced = this.checkDateParsing(computer.getIntroduced());
		Optional<LocalDate> discontinued = this.checkDateParsing(computer.getDiscontinued());
		this.compareDates(introduced, discontinued);
		this.checkCompanyName(computer.getBrand());
	}
}
